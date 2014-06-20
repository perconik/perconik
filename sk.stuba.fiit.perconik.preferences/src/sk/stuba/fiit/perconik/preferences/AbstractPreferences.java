package sk.stuba.fiit.perconik.preferences;

import java.io.IOException;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.eclipse.jface.preference.DefaultPreferenceStore;
import sk.stuba.fiit.perconik.preferences.plugin.Activator;

public abstract class AbstractPreferences
{
	final Scope scope;

	final IPreferenceStore store;
	
	public AbstractPreferences(final Scope scope)
	{
		this.scope = scope;
		this.store = scope.store();
	}
	
	public static enum Scope
	{
		DEFAULT
		{
			@Override
			final IPreferenceStore store()
			{
				return DefaultPreferenceStore.of(INSTANCE.store());
			}
		},
		
		INSTANCE
		{
			@Override
			final IPreferenceStore store()
			{
				return Activator.getDefault().getPreferenceStore();
			}
		};
		
		abstract IPreferenceStore store();
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

	static final String toStringOrFailure(final String key, final Object value)
	{
		try
		{
			return Serialization.writeToString(value);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Unable to write object under key " + key + " to string", e);
		}
	}

	static final Object fromStringOrFailure(final String key, final String value)
	{
		try
		{
			return Serialization.readFromString(value);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Unable to read object under key " + key + " from string", e);
		}
	}

	public final Scope getScope()
	{
		return this.scope;
	}
	
	public final IPreferenceStore getStore()
	{
		return this.store;
	}
	
	protected final void setDefault(final String key, final Object value)
	{
		this.store.setDefault(key, toStringOrFailure(key, value));
	}

	protected final Object getDefaultObject(final String key)
	{
		return fromStringOrFailure(key, this.store.getDefaultString(key));
	}

	protected final void setValue(final String key, final Object value)
	{
		this.store.setValue(key, toStringOrFailure(key, value));
	}

	protected final Object getObject(final String key)
	{
		return fromStringOrFailure(key, this.store.getString(key));
	}
	
	protected final boolean canSave()
	{
		return this.store instanceof IPersistentPreferenceStore;
	}

	public final void save() throws IOException
	{
		if (this.canSave())
		{
			((IPersistentPreferenceStore) this.store).save();
		}
	}
}
