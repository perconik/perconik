package sk.stuba.fiit.perconik.core.listeners;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.AbstractListenerProvider;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Sets;

final class GenericListenerProvider extends AbstractListenerProvider
{
	private final BiMap<String, Class<? extends Listener>> map;
	
	GenericListenerProvider(final Builder builder)
	{
		this.map = HashBiMap.create(builder.map);
		
		for (Class<? extends Listener> type: this.map.values())
		{
			this.validate(type);
		}
	}
	
	public static final class Builder
	{
		BiMap<String, Class<? extends Listener>> map;

		public Builder()
		{
			this.map = HashBiMap.create();
		}
		
		public final <L extends Listener> Builder add(final Class<L> type)
		{
			Preconditions.checkNotNull(type);
			
			this.map.put(type.getName(), type);
			
			return this;
		}

		public final Builder addAll(final Collection<Class<? extends Listener>> types)
		{
			for (Class<? extends Listener> type: types)
			{
				this.add(type);
			}
			
			return this;
		}
		
		public final GenericListenerProvider build()
		{
			return new GenericListenerProvider(this);
		}
	}
	
	public static final Builder builder()
	{
		return new Builder();
	}
	
	@Override
	protected final BiMap<String, Class<? extends Listener>> map()
	{
		return this.map;
	}

	@Override
	protected final Class<? extends Listener> load(String name) throws ClassNotFoundException
	{
		Class<?> c = ClassLoader.getSystemClassLoader().loadClass(name);
		
		if (!Listener.class.isAssignableFrom(c))
		{
			throw new ClassCastException("Class " + c + " is not assignable to " + Listener.class);
		}
		
		@SuppressWarnings("unchecked")
		Class<? extends Listener> type = (Class<? extends Listener>) c;
		
		return type;
	}
	
	@Override
	protected final void validate(final Class<? extends Listener> type)
	{
		if (type.isInterface() || type.isAnnotation() || type.isEnum())
		{
			throw new IllegalStateException("Type " + type + " can not be an interface or an enum");
		}

		try
		{
			type.getConstructor();
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			throw new IllegalStateException("Class " + type + " must have public constructor with no parameters");
		}
	}
	
	public final Iterable<Class<? extends Listener>> classes()
	{
		return Sets.newHashSet(this.map.values());
	}
}
