package sk.stuba.fiit.perconik.core.persistence;

import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

public final class Registrations
{
	private Registrations()
	{
		throw new AssertionError();
	}
	
	public static final <R extends MarkableRegistration> Set<R> registered(final Set<R> registrations)
	{
		return selectByRegisteredStatus(registrations, true);
	}
	
	public static final <R extends MarkableRegistration> Set<R> unregistered(final Set<R> registrations)
	{
		return selectByRegisteredStatus(registrations, false);
	}

	public static final <R extends MarkableRegistration> Set<R> marked(final Set<R> registrations)
	{
		return selectByRegisteredMark(registrations, true);
	}
	
	public static final <R extends MarkableRegistration> Set<R> unmarked(final Set<R> registrations)
	{
		return selectByRegisteredMark(registrations, false);
	}

	public static final <R extends Registration> Set<R> selectByRegisteredStatus(final Set<R> registrations, final boolean status)
	{
		Set<R> result = Sets.newHashSetWithExpectedSize(registrations.size());
		
		for (R registration: registrations)
		{
			if (registration.isRegistered() == status)
			{
				result.add(registration);
			}
		}
		
		return result;
	}

	public static final <R extends MarkableRegistration> Set<R> selectByRegisteredMark(final Set<R> registrations, final boolean status)
	{
		Set<R> result = Sets.newHashSetWithExpectedSize(registrations.size());
		
		for (R registration: registrations)
		{
			if (registration.hasRegistredMark() == status)
			{
				result.add(registration);
			}
		}
		
		return result;		
	}
	
	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> applyRegisteredMark(final Set<R> registrations)
	{
		Set<R> result = Sets.newHashSetWithExpectedSize(registrations.size());
		
		for (R registration: registrations)
		{
			result.add(registration.applyRegisteredMark());
		}
		
		return result;
	}

	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> updateRegisteredMark(final Set<R> registrations)
	{
		Set<R> result = Sets.newHashSetWithExpectedSize(registrations.size());
		
		for (R registration: registrations)
		{
			result.add(registration.updateRegisteredMark());
		}
		
		return result;
	}

	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> markRegistered(final Set<R> registrations, final boolean status)
	{
		Set<R> result = Sets.newHashSetWithExpectedSize(registrations.size());
		
		for (R registration: registrations)
		{
			result.add(registration.markRegistered(status));
		}
		
		return result;
	}

	public static final <R extends ListenerRegistration & MarkableRegistration> Set<Class<? extends Listener>> toListenerClasses(final Set<R> registrations)
	{
		Set<Class<? extends Listener>> result = Sets.newHashSetWithExpectedSize(registrations.size());
		
		for (R registration: registrations)
		{
			result.add(registration.getListenerClass());
		}
		
		return result;
	}
	
	public static final <R extends ResourceRegistration & MarkableRegistration> SetMultimap<Class<? extends Listener>, String> toResourceNames(final Set<R> registrations)
	{
		SetMultimap<Class<? extends Listener>, String> result = HashMultimap.create(registrations.size(), 4);
		
		for (R registration: registrations)
		{
			result.put(registration.getListenerType(), registration.getResourceName());
		}
		
		return result;
	}
}
