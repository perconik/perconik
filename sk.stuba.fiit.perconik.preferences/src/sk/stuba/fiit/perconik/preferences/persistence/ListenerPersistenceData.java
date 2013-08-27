package sk.stuba.fiit.perconik.preferences.persistence;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Set;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

public final class ListenerPersistenceData implements MarkableRegistration, RegistrationMarker<ListenerPersistenceData>, Serializable
{
	private static final long serialVersionUID = -1672202405264953995L;

	private final transient boolean registered;
	
	private final transient Class<? extends Listener> type;
	
	private final transient Optional<Listener> listener;

	ListenerPersistenceData(final boolean registered, final Class<? extends Listener> type, @Nullable final Listener listener)
	{
		this.registered = registered;
		this.type       = checkType(type);
		this.listener   = Optional.fromNullable(listener instanceof Serializable ? listener : null);
		
		Preconditions.checkArgument(listener == null || type == this.listener.getClass());
	}
	
	public static final ListenerPersistenceData of(final Class<? extends Listener> type)
	{
		return new ListenerPersistenceData(Listeners.isRegistred(type), type, null);
	}
	
	public static final ListenerPersistenceData of(final Listener listener)
	{
		return new ListenerPersistenceData(Listeners.isRegistred(listener), listener.getClass(), listener);
	}
	
	public static final Set<ListenerPersistenceData> defaults()
	{
		return Registrations.markRegistered(snapshot(), true);
	}

	public static final Set<ListenerPersistenceData> snapshot()
	{
		ListenerProvider provider = Services.getListenerService().getListenerProvider();
		
		Set<ListenerPersistenceData> data = Sets.newHashSet();
		
		for (Class<? extends Listener> type: provider.classes())
		{
			for (Listener listener: Listeners.registrations().values())
			{
				data.add(new ListenerPersistenceData(type.isInstance(listener), type, listener));
			}
		}
		
		return data;
	}
	
	static final Class<? extends Listener> checkType(final Class<? extends Listener> type)
	{
		try
		{
			return Services.getListenerService().getListenerProvider().loadClass(type.getName());
		}
		catch (ClassNotFoundException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	private static final class SerializationProxy implements Serializable
	{
		private static final long serialVersionUID = -6638506142325802066L;

		private final boolean registered;
		
		private final Class<? extends Listener> type;
		
		private final Optional<Listener> listener;
		
		private SerializationProxy(final ListenerPersistenceData data)
		{
			this.registered = data.hasRegistredMark();
			this.type       = data.getListenerClass();
			this.listener   = data.getSerializedListener();
		}
		
		static final SerializationProxy of(final ListenerPersistenceData data)
		{
			return new SerializationProxy(data);
		}
		
		private final Object readResolve() throws InvalidObjectException
		{
			try
			{
				return new ListenerPersistenceData(this.registered, this.type, this.listener.orNull());
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
	public final boolean equals(@Nullable final Object o)
	{
		if (this == o)
		{
			return true;
		}
		
		if (!(o instanceof ListenerPersistenceData))
		{
			return false;
		}

		ListenerPersistenceData other = (ListenerPersistenceData) o;

		return this.type == other.type;
	}

	@Override
	public final int hashCode()
	{
		return this.type.hashCode();
	}
	
	public final ListenerPersistenceData applyRegisteredMark()
	{
		Listener listener = this.getListener();
		
		boolean status = Listeners.isRegistred(listener);
		
		if (this.registered == status)
		{
			return this;
		}

		if (this.registered)
		{
			Listeners.register(listener);
		}
		else
		{
			Listeners.unregister(listener);
		}
		
		return new ListenerPersistenceData(status, this.type, this.listener.orNull());
	}
	
	public final ListenerPersistenceData updateRegisteredMark()
	{
		return this.markRegistered(this.isRegistred());
	}

	public final ListenerPersistenceData markRegistered(final boolean status)
	{
		if (this.registered == status)
		{
			return this;
		}
		
		return new ListenerPersistenceData(status, this.type, this.listener.orNull());
	}

	public final boolean isRegistred()
	{
		return Listeners.isRegistred(this.type);
	}

	public final boolean hasRegistredMark()
	{
		return this.registered;
	}
	
	public final boolean hasSerializedListener()
	{
		return this.listener.isPresent();
	}
	
	public final Listener getListener()
	{
		if (this.hasSerializedListener())
		{
			return this.listener.get();
		}
		
		return Services.getListenerService().getListenerProvider().forClass(this.type);
	}

	public final Class<? extends Listener> getListenerClass()
	{
		return this.type;
	}

	public final Optional<Listener> getSerializedListener()
	{
		return this.listener;
	}
}
