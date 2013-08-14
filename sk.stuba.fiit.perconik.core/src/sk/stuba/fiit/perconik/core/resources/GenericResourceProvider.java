package sk.stuba.fiit.perconik.core.resources;

import java.util.Collection;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.services.AbstractResourceProvider;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

final class GenericResourceProvider extends AbstractResourceProvider
{
	private final BiMap<String, Resource<?>> map;
	
	GenericResourceProvider(final Builder builder)
	{
		this.map = ImmutableBiMap.copyOf(builder.map);
	}
	
	public static final class Builder
	{
		BiMap<String, Resource<?>> map;

		public Builder()
		{
			this.map = HashBiMap.create();
		}
		
		public final Builder add(final Resource<?> resource)
		{
			this.map.put(Resources.getName(resource), resource);
			
			return this;
		}
		
		public final Builder addAll(final Collection<Resource<?>> resources)
		{
			for (Resource<?> resource: resources)
			{
				this.add(resource);
			}
			
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
}
