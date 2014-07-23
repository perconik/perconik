package sk.stuba.fiit.perconik.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

import org.osgi.service.prefs.BackingStoreException;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractPreferences
{
	final Scope scope;

	final IEclipsePreferences data;

	public AbstractPreferences(final Scope scope, final String qualifier)
	{
		this.scope = checkNotNull(scope);
		this.data  = checkNotNull(scope.preferences(qualifier));
	}

	public static enum Scope
	{
		DEFAULT
		{
			@Override
			final IScopeContext context()
			{
				return DefaultScope.INSTANCE;
			}
		},

		CONFIGURATION
		{
			@Override
			final IScopeContext context()
			{
				return ConfigurationScope.INSTANCE;
			}
		},

		INSTANCE
		{
			@Override
			final IScopeContext context()
			{
				return InstanceScope.INSTANCE;
			}
		};

		abstract IScopeContext context();

		final IEclipsePreferences preferences(final String qualifier)
		{
			return context().getNode(qualifier);
		}
	}

	public static abstract class Initializer extends AbstractPreferenceInitializer
	{
		protected Initializer()
		{
		}
	}

	public static abstract class Keys
	{
		protected Keys()
		{
			throw new AssertionError();
		}
	}

	protected final Scope scope()
	{
		return this.scope;
	}

	protected final IEclipsePreferences data()
	{
		return this.data;
	}

	public final void clear() throws BackingStoreException
	{
		this.data.clear();
	}

	public final void flush() throws BackingStoreException
	{
		this.data.flush();
	}

	public final void synchronize() throws BackingStoreException
	{
		this.data.sync();
	}
}
