package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Map;
import java.util.Set;
import sk.stuba.fiit.perconik.core.IllegalListenerClassException;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.ListenerInstantiationException;
import sk.stuba.fiit.perconik.utilities.MoreSets;
import sk.stuba.fiit.perconik.utilities.reflect.ReflectionException;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.AccessorConstructionException;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

final class StandardListenerProvider extends AbstractListenerProvider
{
	private final BiMap<String, Class<? extends Listener>> map;
	
	private final Map<Class<? extends Listener>, Listener> cache;
	
	private final ListenerProvider parent;
	
	StandardListenerProvider(final Builder builder)
	{
		this.map    = HashBiMap.create(builder.map);
		this.cache  = Maps.newConcurrentMap();
		this.parent = builder.parent.or(ListenerProviders.getSystemProvider());
	}
	
	public static final class Builder implements ListenerProvider.Builder
	{
		BiMap<String, Class<? extends Listener>> map;
		
		Optional<ListenerProvider> parent;

		public Builder()
		{
			this.map    = HashBiMap.create();
			this.parent = Optional.absent();
		}
		
		public final Builder add(final Class<? extends Listener> implementation)
		{
			Preconditions.checkNotNull(implementation);
			
			this.map.put(implementation.getName(), implementation.asSubclass(Listener.class));
			
			return this;
		}

		public final Builder addAll(final Iterable<Class<? extends Listener>> implementations)
		{
			for (Class<? extends Listener> type: implementations)
			{
				this.add(type);
			}
			
			return this;
		}
		
		public final Builder parent(final ListenerProvider parent)
		{
			Preconditions.checkState(!this.parent.isPresent());

			this.parent = Optional.of(Preconditions.checkNotNull(parent));
			
			return this;
		}
		
		public final StandardListenerProvider build()
		{
			return new StandardListenerProvider(this);
		}
	}
	
	public static final Builder builder()
	{
		return new Builder();
	}

	@Override
	protected final ClassLoader loader()
	{
		return this.getClass().getClassLoader();
	}
	
	public final <L extends Listener> L forClass(final Class<L> type)
	{
		Listener listener = this.cache.get(cast(type));
		
		if (listener != null)
		{
			return type.cast(listener);
		}
		
		L instance;
		
		try
		{
			instance = StaticListenerLookup.forClass(type).get();
		}
		catch (ReflectionException e)
		{
			Throwable[] suppressions = e.getSuppressed();
			
			Exception cause; 
			
			if (suppressions.length == 1 && suppressions[0] instanceof AccessorConstructionException)
			{
				cause = new IllegalListenerClassException(suppressions[0]);
			}
			else
			{
				cause = new ListenerInstantiationException(e);
			}
			
			return this.parentForClass(type, cause);
		}

		if (!this.map.containsValue(type))
		{
			this.map.put(type.getName(), type);
		}

		this.cache.put(type, instance);
		
		return instance;
	}

	public final Class<? extends Listener> loadClass(final String name) throws ClassNotFoundException
	{
		Class<? extends Listener> type = this.map.get(name);

		if (type != null)
		{
			return type;
		}

		try
		{
			return cast(this.load(name));
		}
		catch (Exception cause)
		{
			return this.parentLoadClass(name, cause);
		}
	}
	
	public final Set<Class<? extends Listener>> classes()
	{
		return MoreSets.newHashSet(this.map.values(), this.parent.classes());
	}

	public final ListenerProvider parent()
	{
		return this.parent;
	}
}
