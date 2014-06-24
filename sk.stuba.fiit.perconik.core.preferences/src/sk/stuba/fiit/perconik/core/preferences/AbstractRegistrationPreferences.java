package sk.stuba.fiit.perconik.core.preferences;

import java.util.Set;
import sk.stuba.fiit.perconik.core.persistence.Registration;
import sk.stuba.fiit.perconik.core.preferences.plugin.Activator;
import sk.stuba.fiit.perconik.osgi.framework.Bundles;
import sk.stuba.fiit.perconik.preferences.AbstractObjectPreferences;

abstract class AbstractRegistrationPreferences<R extends Registration> extends AbstractObjectPreferences
{
	AbstractRegistrationPreferences(final Scope scope)
	{
		super(scope, Bundles.newClassResolver(Activator.getDefault().getBundle()));
	}
	
	final void setRegistrations(final String key, final Set<R> registrations)
	{
		try
		{
			this.setValue(key, registrations);
		}
		catch (RuntimeException e)
		{
			Activator.getDefault().getConsole().error(e, "Unable to write registrations under key %s", key);
		}
	}

	final Set<R> getRegistrations(final String key)
	{		
		try
		{
			return (Set<R>) this.getObject(key);
		}
		catch (RuntimeException e)
		{
			Activator.getDefault().getConsole().error(e, "Unable to read registrations under key %s", key);
			
			if (this.getScope() != Scope.DEFAULT)
			{
				return this.getDefaultRegistrations();
			}
			
			throw e;
		}
	}

	abstract Set<R> getDefaultRegistrations();
}
