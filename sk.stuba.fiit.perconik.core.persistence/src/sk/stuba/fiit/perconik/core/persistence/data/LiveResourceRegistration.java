package sk.stuba.fiit.perconik.core.persistence.data;

import java.util.Set;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.persistence.ResourceRegistration;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import com.google.common.collect.Sets;

public class LiveResourceRegistration implements ResourceRegistration
{
	private final Class<? extends Listener> type;

	private final String name;
	
	private final Resource<?> resource;
	
	private LiveResourceRegistration(final Class<? extends Listener> type, final String name, final Resource<?> resource)
	{
		this.type     = type;
		this.name     = name;
		this.resource = resource;
	}
	
	static final LiveResourceRegistration construct(final Class<? extends Listener> type, final String name, final Resource<?> resource)
	{
		Utilities.checkListenerType(type);
		Utilities.checkResourceName(name);
		Utilities.checkResourceImplementation(name, resource);
		
		return copy(type, name, resource);
	}

	static final LiveResourceRegistration copy(final Class<? extends Listener> type, final String name, final Resource<?> resource)
	{
		return new LiveResourceRegistration(type, name, resource);
	}
	
	public static final <L extends Listener> LiveResourceRegistration of(final Class<L> type, final String name)
	{
		 return of(type, Unsafe.cast(type, Services.getResourceService().getResourceProvider().forName(name)));
	}

	public static final <L extends Listener> LiveResourceRegistration of(final Class<L> type, final Resource<? super L> resource)
	{
		 return construct(type, resource.getName(), resource);
	}
	
	public static final Set<LiveResourceRegistration> snapshot()
	{
		ResourceProvider provider = Services.getResourceService().getResourceProvider();
		
		Set<LiveResourceRegistration> data = Sets.newHashSet();
		
		for (Class<? extends Listener> type: provider.types())
		{
			for (Resource<?> resource: provider.forType(type))
			{
				data.add(construct(type, resource.getName(), resource));
			}
		}
		
		return data;
	}

	@Override
	public final boolean equals(@Nullable final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof ResourceRegistration))
		{
			return false;
		}

		ResourceRegistration other = (ResourceRegistration) o;

		return this.type == other.getListenerType() && this.name.equals(other.getResourceName());
	}

	@Override
	public final int hashCode()
	{
		return 31 * (31 + this.type.hashCode()) + this.name.hashCode();
	}
	
	public final ResourcePersistenceData toPersistenceData()
	{
		return ResourcePersistenceData.copy(this.isRegistered(), this.type, this.name, Utilities.serializableOrNull(this.resource));
	}

	@Override
	public final String toString()
	{
		return Utilities.toString(this);
	}
	
	public final boolean isRegistered()
	{
		return Resources.isRegistered(this.type, this.getResource());
	}

	public final Class<? extends Listener> getListenerType()
	{
		return this.type;
	}

	public final Resource<?> getResource()
	{
		return this.resource;
	}
	
	public final String getResourceName()
	{
		return this.name;
	}
}
