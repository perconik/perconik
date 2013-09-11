package sk.stuba.fiit.perconik.core.persistence;

import java.util.Collection;
import java.util.Set;
import sk.stuba.fiit.perconik.core.Listener;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

/**
 * Static utility methods pertaining to {@code Registration} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Registrations
{
	private Registrations()
	{
		throw new AssertionError();
	}

	private static final <E> Set<E> newSet(final Iterable<?> iterable)
	{
		if (iterable instanceof Collection)
		{
			Sets.newHashSetWithExpectedSize(((Collection<?>) iterable).size());
		}
		
		return Sets.newHashSet();
	}
	
	public static final <R extends MarkableRegistration> Set<R> registered(final Iterable<R> registrations)
	{
		return selectByRegisteredStatus(registrations, true);
	}
	
	public static final <R extends MarkableRegistration> Set<R> unregistered(final Iterable<R> registrations)
	{
		return selectByRegisteredStatus(registrations, false);
	}

	public static final <R extends MarkableRegistration> Set<R> marked(final Iterable<R> registrations)
	{
		return selectByRegisteredMark(registrations, true);
	}
	
	public static final <R extends MarkableRegistration> Set<R> unmarked(final Iterable<R> registrations)
	{
		return selectByRegisteredMark(registrations, false);
	}

	public static final <R extends Registration> Set<R> selectByRegisteredStatus(final Iterable<R> registrations, final boolean status)
	{
		Set<R> result = newSet(registrations);
		
		for (R registration: registrations)
		{
			if (registration.isRegistered() == status)
			{
				result.add(registration);
			}
		}
		
		return result;
	}

	public static final <R extends MarkableRegistration> Set<R> selectByRegisteredMark(final Iterable<R> registrations, final boolean status)
	{
		Set<R> result = newSet(registrations);
		
		for (R registration: registrations)
		{
			if (registration.hasRegistredMark() == status)
			{
				result.add(registration);
			}
		}
		
		return result;		
	}
	
	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> applyRegisteredMark(final Iterable<R> registrations)
	{
		Set<R> result = newSet(registrations);
		
		for (R registration: registrations)
		{
			result.add(registration.applyRegisteredMark());
		}
		
		return result;
	}

	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> updateRegisteredMark(final Iterable<R> registrations)
	{
		Set<R> result = newSet(registrations);
		
		for (R registration: registrations)
		{
			result.add(registration.updateRegisteredMark());
		}
		
		return result;
	}

	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> markRegistered(final Iterable<R> registrations, final boolean status)
	{
		return markRegistered(registrations, Functions.constant(status));
	}
	
	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> markRegistered(final Iterable<R> registrations, final Function<? super R, Boolean> function)
	{
		Set<R> result = newSet(registrations);
		
		for (R registration: registrations)
		{
			result.add(registration.markRegistered(function.apply(registration)));
		}
		
		return result;
	}
	
	public static final <R extends Registration> Set<R> supplement(final Set<? extends R> original, final Iterable<? extends R> supplements)
	{
		Set<R> result = Sets.newHashSet(original);
		
		for (R registration: supplements)
		{
			if (!result.contains(registration))
			{
				result.add(registration);
			}
		}
		
		return result;
	}
	
	public static final <R extends ResourceRegistration & MarkableRegistration> SetMultimap<Class<? extends Listener>, String> toResourceNames(final Iterable<R> registrations)
	{
		SetMultimap<Class<? extends Listener>, String> result = HashMultimap.create();
		
		for (R registration: registrations)
		{
			result.put(registration.getListenerType(), registration.getResourceName());
		}
		
		return result;
	}

	public static final <R extends ListenerRegistration & MarkableRegistration> Set<Class<? extends Listener>> toListenerClasses(final Iterable<R> registrations)
	{
		Set<Class<? extends Listener>> result = newSet(registrations);
		
		for (R registration: registrations)
		{
			result.add(registration.getListenerClass());
		}
		
		return result;
	}
}
