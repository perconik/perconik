package sk.stuba.fiit.perconik.core.persistence.data;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.ResourceNotFoundException;
import sk.stuba.fiit.perconik.core.persistence.InvalidListenerException;
import sk.stuba.fiit.perconik.core.persistence.MarkableRegistration;
import sk.stuba.fiit.perconik.core.persistence.RegistrationMarker;
import sk.stuba.fiit.perconik.core.persistence.serialization.SerializedListenerData;
import sk.stuba.fiit.perconik.core.plugin.Activator;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

/**
 * Markable listener registration with lively updated registration status.
 * 
 * <p><b>Note:</b> This implementation is truly serializable if and only
 * if the underlying listener is serializable. Otherwise this implementation
 * serializes listener's data necessary to obtain the listener from the core
 * listener provider after deserialization at runtime.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenerPersistenceData extends AbstractListenerRegistration implements MarkableRegistration, RegistrationMarker<ListenerPersistenceData>, Serializable, SerializedListenerData
{
	private static final long serialVersionUID = -1672202405264953995L;

	private final transient boolean registered;
	
	private final transient Class<? extends Listener> type;
	
	private final transient Optional<Listener> listener;

	private ListenerPersistenceData(final boolean registered, final Class<? extends Listener> type, final Optional<Listener> listener)
	{
		this.registered = registered;
		this.type       = type;
		this.listener   = listener;
	}
	
	static final ListenerPersistenceData construct(final boolean registered, final Class<? extends Listener> type, @Nullable final Listener listener)
	{
		Utilities.checkListenerClass(type);
		Utilities.checkListenerImplementation(type, listener);
		
		return copy(registered, type, listener);
	}
	
	static final ListenerPersistenceData copy(final boolean registered, final Class<? extends Listener> type, @Nullable final Listener listener)
	{
		return new ListenerPersistenceData(registered, type, Utilities.serializableOrNullAsOptional(listener));
	}
	
	public static final ListenerPersistenceData of(final Class<? extends Listener> type)
	{
		return construct(Listeners.isRegistered(type), type, null);
	}
	
	public static final ListenerPersistenceData of(final Listener listener)
	{
		return construct(Listeners.isRegistered(listener), listener.getClass(), listener);
	}
	
	public static final Set<ListenerPersistenceData> defaults()
	{
		ListenerProvider provider = Services.getListenerService().getListenerProvider();
		
		Set<ListenerPersistenceData> data = Sets.newHashSet();
		
		for (Class<? extends Listener> type: provider.classes())
		{
			data.add(construct(true, type, null));
		}
		
		return data;
	}

	public static final Set<ListenerPersistenceData> snapshot()
	{
		ListenerProvider provider = Services.getListenerService().getListenerProvider();

		Set<ListenerPersistenceData> data = Sets.newHashSet();
		
		Collection<Listener> listeners = Listeners.registrations().values();
		
		for (Class<? extends Listener> type: provider.classes())
		{
			for (Listener listener: listeners)
			{
				data.add(construct(type == listener.getClass(), type, listener));
			}
		}
		
		return data;
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
				return construct(this.registered, this.type, this.listener.orNull());
			}
			catch (RuntimeException e)
			{
				throw new InvalidListenerException("Unknown deserialization error", e);
			}
		}
	}

	@SuppressWarnings({"static-method", "unused"})
	private final void readObject(final ObjectInputStream in) throws InvalidObjectException
	{
		throw new InvalidListenerException("Serialization proxy required");
	}

	private final Object writeReplace()
	{
		return SerializationProxy.of(this);
	}
	
	public final ListenerPersistenceData applyRegisteredMark()
	{
		Listener listener = this.getListener();
		
		boolean status = Listeners.isRegistered(listener);
		
		if (this.registered == status)
		{
			return this;
		}

		try
		{
			if (this.registered)
			{
				Listeners.register(listener);
			}
			else
			{
				Listeners.unregister(listener);
			}
		}
		catch (ResourceNotFoundException e)
		{
			Activator.getDefault().getConsole().notice("Trying to register or unregister listener of type " + this.type.getName() + " but no resources found");
			
			return this;
		}
		
		return new ListenerPersistenceData(status, this.type, this.listener);
	}
	
	public final ListenerPersistenceData updateRegisteredMark()
	{
		return this.markRegistered(this.isRegistered());
	}

	public final ListenerPersistenceData markRegistered(final boolean status)
	{
		if (this.registered == status)
		{
			return this;
		}
		
		return new ListenerPersistenceData(status, this.type, this.listener);
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
		
		return Listeners.forClass(this.type);
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
