package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.utilities.MoreSets;

final class StandardResourceProvider extends AbstractResourceProvider
{
	private final BiMap<String, Resource<?>> map;

	private final SetMultimap<Class<? extends Listener>, Resource<?>> multimap;
	
	private final ResourceProvider parent;
	
	StandardResourceProvider(final Builder builder)
	{
		this.map      = ImmutableBiMap.copyOf(builder.map);
		this.multimap = ImmutableSetMultimap.copyOf(builder.multimap);
		this.parent   = builder.parent.or(ResourceProviders.getSystemProvider());
	}
	
	public static final class Builder implements ResourceProvider.Builder
	{
		BiMap<String, Resource<?>> map;
		
		SetMultimap<Class<? extends Listener>, Resource<?>> multimap;
		
		Optional<ResourceProvider> parent;

		public Builder()
		{
			this.map      = HashBiMap.create();
			this.multimap = HashMultimap.create();
			this.parent   = Optional.absent();
		}
		
		public final <L extends Listener> Builder add(final Class<L> type, final Resource<? super L> resource)
		{
			Preconditions.checkNotNull(type);
			
			this.map.put(resource.getName(), resource);
			this.multimap.put(type, resource);
			
			return this;
		}
		
		public final Builder parent(final ResourceProvider parent)
		{
			Preconditions.checkState(!this.parent.isPresent());

			this.parent = Optional.of(Preconditions.checkNotNull(parent));
			
			return this;
		}
		
		public final StandardResourceProvider build()
		{
			return new StandardResourceProvider(this);
		}
	}
	
	public static final Builder builder()
	{
		return new Builder();
	}
	
	public final Resource<?> forName(final String name)
	{
		Resource<?> resource = this.map.get(name);
		
		if (resource != null)
		{
			return resource;
		}
		
		return this.parentForName(name, null);
	}
	
	public final <L extends Listener> Set<Resource<L>> forType(final Class<L> type)
	{
		Set<Resource<L>> resources = Sets.newHashSet();
		
		for (Resource<?> resource: this.multimap.get(type))
		{
			resources.add(Unsafe.cast(type, resource));
		}
		
		return MoreSets.newHashSet(resources, this.parentForType(type, null));
	}
	
	public final Set<String> names()
	{
		return MoreSets.newHashSet(this.map.keySet(), this.parent.names());
	}

	public final Set<Class<? extends Listener>> types()
	{
		return MoreSets.newHashSet(this.multimap.keySet(), this.parent.types());
	}

	public final ResourceProvider parent()
	{
		return this.parent;
	}
}
