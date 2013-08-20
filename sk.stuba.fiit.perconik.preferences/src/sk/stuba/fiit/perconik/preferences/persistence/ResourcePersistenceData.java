package sk.stuba.fiit.perconik.preferences.persistence;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import com.google.common.base.Preconditions;

public final class ResourcePersistenceData implements Serializable
{
	private static final long serialVersionUID = 1238234807869876219L;

	private final transient Class<? extends Listener> type;

	private final transient String name;
	
	private final transient Resource<?> resource;
	
	ResourcePersistenceData(final Class<? extends Listener> type, final String name, final Resource<?> resource)
	{
		this.type     = checkType(type);
		this.name     = checkName(name);
		this.resource = resource instanceof Serializable ? resource : null;
		
		Preconditions.checkArgument(resource == null || name.equals(resource.getName()));
	}
	
	public static final <L extends Listener> ResourcePersistenceData of(final Class<L> type, final Resource<? super L> resource)
	{
		return new ResourcePersistenceData(type, resource.getName(), resource);
	}
	
	static final Class<? extends Listener> checkType(final Class<? extends Listener> type)
	{
		Preconditions.checkArgument(Listener.class.isAssignableFrom(type));
		
		return type;
	}

	static final String checkName(final String name)
	{
		Preconditions.checkArgument(!name.isEmpty());
		
		return name;
	}

	private static final class SerializationProxy implements Serializable
	{
		private static final long serialVersionUID = 952540731365953815L;

		private final Class<? extends Listener> type;
		
		private final String name;

		private final Resource<?> resource;

		private SerializationProxy(final ResourcePersistenceData data)
		{
			this.type     = data.getListenerType();
			this.name     = data.getResourceName();
			this.resource = data.getResource();
		}
		
		static final SerializationProxy of(final ResourcePersistenceData data)
		{
			return new SerializationProxy(data);
		}

		private final Object readResolve() throws InvalidObjectException
		{
			try
			{
				return new ResourcePersistenceData(this.type, this.name, this.resource);
			}
			catch (RuntimeException e)
			{
				throw new InvalidObjectException("Unknown deserialization error");
			}
		}
	}

	@SuppressWarnings({"static-method", "unused"})
	private final void readObject(final ObjectInputStream in) throws InvalidObjectException
	{
		throw new InvalidObjectException("Serialization proxy required");
	}

	private final Object writeReplace()
	{
		return SerializationProxy.of(this);
	}
	
	@Override
	public final boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof ResourcePersistenceData))
		{
			return false;
		}

		ResourcePersistenceData other = (ResourcePersistenceData) o;

		return this.type == other.type && this.name.equals(other.name);
	}

	@Override
	public final int hashCode()
	{
		return 31 * (31 + this.type.hashCode()) + this.name.hashCode();
	}

	public final boolean hasResource()
	{
		return this.resource != null;
	}
	
	public final Class<? extends Listener> getListenerType()
	{
		return this.type;
	}

	public final String getResourceName()
	{
		return this.name;
	}

	public final Resource<?> getResource()
	{
		return this.resource;
	}
}
