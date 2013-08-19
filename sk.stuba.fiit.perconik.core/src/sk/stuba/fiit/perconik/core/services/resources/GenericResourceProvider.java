package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

final class GenericResourceProvider extends AbstractResourceProvider
{
	private final BiMap<String, Resource<?>> map;

	private final SetMultimap<Class<? extends Listener>, Resource<?>> multimap;
	
	GenericResourceProvider(final Builder builder)
	{
		this.map      = ImmutableBiMap.copyOf(builder.map);
		this.multimap = ImmutableSetMultimap.copyOf(builder.multimap); 
	}
	
	public static final class Builder implements ResourceProvider.Builder
	{
		BiMap<String, Resource<?>> map;
		
		SetMultimap<Class<? extends Listener>, Resource<?>> multimap;

		public Builder()
		{
			this.map      = HashBiMap.create();
			this.multimap = HashMultimap.create();
		}
		
		public final <L extends Listener> Builder add(final Class<L> type, final Resource<L> resource)
		{
			Preconditions.checkNotNull(type);
			
			this.map.put(Resources.getName(resource), resource);
			this.multimap.put(type, resource);
			
			return this;
		}
		
		public final GenericResourceProvider build()
		{
			return new GenericResourceProvider(this);
		}
	}
	
	public static final Builder builder()
	{
		return new Builder();
	}

	@Override
	protected final BiMap<String, Resource<?>> map()
	{
		return this.map;
	}
	
	public final <L extends Listener> Set<Resource<? super L>> forClass(final Class<L> type)
	{
		Set<Resource<? super L>> resources = Sets.newHashSet();
		
		for (Resource<?> resource: this.multimap.get(type))
		{
			resources.add(Unsafe.cast(type, resource));
		}
		
		return resources;
	}
	
	public final Iterable<String> names()
	{
		return Sets.newHashSet(this.map.keySet());
	}

	public final Iterable<Class<? extends Listener>> classes()
	{
		return Sets.newHashSet(this.multimap.keySet());
	}

	public final Set<Resource<?>> resources()
	{
		return Sets.newHashSet(this.map.values());
	}
}
