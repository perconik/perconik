package sk.stuba.fiit.perconik.preferences.persistence;

import java.util.Set;
import com.google.common.collect.Sets;

public final class Registrations
{
	private Registrations()
	{
		throw new AssertionError();
	}
	
	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> applyRegisteredMark(final Set<R> data)
	{
		Set<R> result = Sets.newHashSetWithExpectedSize(data.size());
		
		for (R registration: data)
		{
			result.add(registration.applyRegisteredMark());
		}
		
		return result;
	}

	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> updateRegisteredMark(final Set<R> data)
	{
		Set<R> result = Sets.newHashSetWithExpectedSize(data.size());
		
		for (R registration: data)
		{
			result.add(registration.updateRegisteredMark());
		}
		
		return result;
	}

	public static final <R extends MarkableRegistration & RegistrationMarker<R>> Set<R> markRegistered(final Set<R> data, final boolean status)
	{
		Set<R> result = Sets.newHashSetWithExpectedSize(data.size());
		
		for (R registration: data)
		{
			result.add(registration.markRegistered(status));
		}
		
		return result;
	}
}
