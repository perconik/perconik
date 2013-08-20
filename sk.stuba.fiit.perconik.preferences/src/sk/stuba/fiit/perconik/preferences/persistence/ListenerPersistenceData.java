package sk.stuba.fiit.perconik.preferences.persistence;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Preconditions;

public final class ListenerPersistenceData implements Serializable
{
	private static final long serialVersionUID = -481400648314855976L;

	private final transient Class<? extends Listener> type;
	
	private final transient Listener listener;

	ListenerPersistenceData(final Class<? extends Listener> type, final Listener listener)
	{
		this.type     = checkType(type);
		this.listener = listener instanceof Serializable ? listener : null;
		
		Preconditions.checkArgument(listener == null || type == this.listener.getClass());
	}
	
	public static final ListenerPersistenceData of(final Class<? extends Listener> type)
	{
		return new ListenerPersistenceData(type, null);
	}
	
	public static final ListenerPersistenceData of(final Listener listener)
	{
		return new ListenerPersistenceData(listener.getClass(), listener);
	}
	
	static final Class<? extends Listener> checkType(final Class<? extends Listener> type)
	{
		Preconditions.checkArgument(Listener.class.isAssignableFrom(type));
		Preconditions.checkArgument(!type.isInterface() && !type.isAnnotation() && !type.isEnum());
		
		try
		{
			type.getConstructor();
		}
		catch (NoSuchMethodException | SecurityException e)
		{
			throw new IllegalArgumentException(e);
		}
		
		return type;
	}

	private static final class SerializationProxy implements Serializable
	{
		private static final long serialVersionUID = 952540731365953815L;

		private final Class<? extends Listener> type;
		
		private final Listener listener;
		
		private SerializationProxy(final ListenerPersistenceData data)
		{
			this.type     = data.getListenerClass();
			this.listener = data.getListener();
		}
		
		static final SerializationProxy of(final ListenerPersistenceData data)
		{
			return new SerializationProxy(data);
		}
		
		private final Object readResolve() throws InvalidObjectException
		{
			try
			{
				return new ListenerPersistenceData(this.type, this.listener);
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
	
	public final boolean hasListener()
	{
		return this.listener != null;
	}
	
	public final Class<? extends Listener> getListenerClass()
	{
		return this.type;
	}

	public final Listener getListener()
	{
		return this.listener;
	}
}
