package sk.stuba.fiit.perconik.core.persistence.data;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.persistence.InvalidResourceException;
import sk.stuba.fiit.perconik.core.persistence.MarkableRegistration;
import sk.stuba.fiit.perconik.core.persistence.RegistrationMarker;
import sk.stuba.fiit.perconik.core.persistence.serialization.SerializedResourceData;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;

/**
 * Markable resource registration with lively updated registration status.
 * 
 * <p><b>Note:</b> This implementation is truly serializable if and only
 * if the underlying resource is serializable. Otherwise this implementation
 * serializes resource's data necessary to obtain the resource from the core
 * resource provider after deserialization at runtime.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourcePersistenceData extends AbstractResourceRegistration implements MarkableRegistration, RegistrationMarker<ResourcePersistenceData>, Serializable, SerializedResourceData
{
	private static final long serialVersionUID = 6677144113746518278L;

	private final transient boolean registered;
	
	private final transient Class<? extends Listener> type;

	private final transient String name;
	
	private final transient Optional<Resource<?>> resource;
	
	private ResourcePersistenceData(final boolean registered, final Class<? extends Listener> type, final String name, final Optional<Resource<?>> resource)
	{
		this.registered = registered;
		this.type       = type;
		this.name       = name;
		this.resource   = resource;
	}
	
	static final ResourcePersistenceData construct(final boolean registered, final Class<? extends Listener> type, final String name, @Nullable final Resource<?> resource)
	{
		Utilities.checkListenerType(type);
		Utilities.checkResourceName(name);
		Utilities.checkResourceImplementation(name, resource);
		
		return copy(registered, type, name, resource);
	}
	
	static final ResourcePersistenceData copy(final boolean registered, final Class<? extends Listener> type, final String name, @Nullable final Resource<?> resource)
	{
		return new ResourcePersistenceData(registered, type, name, Utilities.<Resource<?>>serializableOrNullAsOptional(resource));
	}

	public static final <L extends Listener> ResourcePersistenceData of(final Class<L> type, final String name)
	{
		 return of(type, Unsafe.cast(type, Resources.forName(name)));
	}

	public static final <L extends Listener> ResourcePersistenceData of(final Class<L> type, final Resource<? super L> resource)
	{
		 return construct(Resources.isRegistered(type, resource), type, resource.getName(), resource);
	}
	
	public static final Set<ResourcePersistenceData> defaults()
	{
		ResourceProvider provider = Services.getResourceService().getResourceProvider();
		
		Set<ResourcePersistenceData> data = Sets.newHashSet();
		
		for (Class<? extends Listener> type: provider.types())
		{
			for (Resource<?> resource: provider.forType(type))
			{
				data.add(construct(Utilities.registeredByDefault(resource.getClass()), type, resource.getName(), resource));
			}
		}
		
		return data;
	}

	public static final Set<ResourcePersistenceData> snapshot()
	{
		ResourceProvider provider = Services.getResourceService().getResourceProvider();
		
		Set<ResourcePersistenceData> data = Sets.newHashSet();
		
		for (Class<? extends Listener> type: provider.types())
		{
			for (Resource<?> resource: provider.forType(type))
			{
				data.add(construct(Resources.isRegistered(type, resource), type, resource.getName(), resource));
			}
		}
		
		return data;
	}

	private static final class SerializationProxy implements Serializable
	{
		private static final long serialVersionUID = 4583906053454610999L;

		private final boolean registered;
		
		private final String type;
		
		private final String name;

		@Nullable
		private final Optional<Resource<?>> resource;

		private SerializationProxy(final ResourcePersistenceData data)
		{
			this.registered = data.hasRegistredMark();
			this.type       = data.getListenerType().getName();
			this.name       = data.getResourceName();
			this.resource   = data.getSerializedResource();
		}
		
		static final SerializationProxy of(final ResourcePersistenceData data)
		{
			return new SerializationProxy(data);
		}

		private final Object readResolve() throws InvalidObjectException
		{
			try
			{
				return construct(this.registered, Utilities.resolveClassAsSubclass(this.type, Listener.class), this.name, this.resource.orNull());
			}
			catch (Exception e)
			{
				throw new InvalidResourceException("Unknown deserialization error", e);
			}
		}
	}

	@SuppressWarnings({"static-method", "unused"})
	private final void readObject(final ObjectInputStream in) throws InvalidObjectException
	{
		throw new InvalidResourceException("Serialization proxy required");
	}

	private final Object writeReplace()
	{
		return SerializationProxy.of(this);
	}
	
	public final ResourcePersistenceData applyRegisteredMark()
	{
		Resource<?> resource = this.getResource();
		
		if (resource == null)
		{
			return this;
		}
		
		boolean status = Resources.isRegistered(this.type, resource);
		
		if (this.registered == status)
		{
			return this;
		}

		if (this.registered)
		{
			Unsafe.register(this.type, resource);
		}
		else
		{
			Unsafe.unregister(this.type, resource);
		}
		
		return new ResourcePersistenceData(status, this.type, this.name, this.resource);
	}
	
	public final ResourcePersistenceData updateRegisteredMark()
	{
		return this.markRegistered(this.isRegistered());
	}

	public final ResourcePersistenceData markRegistered(final boolean status)
	{
		if (this.registered == status)
		{
			return this;
		}
		
		return new ResourcePersistenceData(status, this.type, this.name, this.resource);
	}

	public final boolean hasRegistredMark()
	{
		return this.registered;
	}
	
	public final boolean hasSerializedResource()
	{
		return this.resource.isPresent();
	}
	
	public final Class<? extends Listener> getListenerType()
	{
		return this.type;
	}

	public final Resource<?> getResource()
	{
		if (this.hasSerializedResource())
		{
			return this.resource.get();
		}
		
		return Resources.forName(this.name);
	}
	
	public final String getResourceName()
	{
		return this.name;
	}

	public final Optional<Resource<?>> getSerializedResource()
	{
		return this.resource;
	}
}
