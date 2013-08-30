package sk.stuba.fiit.perconik.core.persistence.data;

import java.util.Set;
import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.persistence.ListenerRegistration;
import com.google.common.collect.Sets;

public class LiveListenerRegistration implements ListenerRegistration
{
	private final Class<? extends Listener> type;
	
	private final Listener listener;

	private LiveListenerRegistration(final Class<? extends Listener> type, final Listener listener)
	{
		this.type     = type;
		this.listener = listener;
	}
	
	static final LiveListenerRegistration construct(final Class<? extends Listener> type, final Listener listener)
	{
		Utilities.checkListenerClass(type);
		Utilities.checkListenerImplementation(type, listener);
		
		return copy(type, listener);
	}
	
	static final LiveListenerRegistration copy(final Class<? extends Listener> type, final Listener listener)
	{
		return new LiveListenerRegistration(type, listener);
	}

	public static final LiveListenerRegistration of(final Listener listener)
	{
		return construct(listener.getClass(), listener);
	}
	
	public static final Set<LiveListenerRegistration> snapshot()
	{
		Set<LiveListenerRegistration> data = Sets.newHashSet();
		
		for (Listener listener: Listeners.registrations().values())
		{
			data.add(of(listener));
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
		
		if (!(o instanceof ListenerRegistration))
		{
			return false;
		}

		ListenerRegistration other = (ListenerRegistration) o;

		return this.type == other.getListenerClass();
	}

	@Override
	public final int hashCode()
	{
		return this.type.hashCode();
	}
	
	public final ListenerPersistenceData toPersistenceData()
	{
		return ListenerPersistenceData.copy(this.isRegistered(), this.type, Utilities.serializableOrNull(this.listener));
	}
	
	@Override
	public final String toString()
	{
		return Utilities.toString(this);
	}
	
	public final boolean isRegistered()
	{
		return Listeners.isRegistered(this.listener);
	}

	public final Listener getListener()
	{
		return this.listener;
	}

	public final Class<? extends Listener> getListenerClass()
	{
		return this.type;
	}
}
