package sk.stuba.fiit.perconik.preferences;

import java.io.IOException;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import sk.stuba.fiit.perconik.eclipse.jface.preference.DefaultPreferenceStore;
import sk.stuba.fiit.perconik.preferences.plugin.Activator;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

abstract class AbstractPreferences
{
	final IPreferenceStore store;
	
	final String key;
	
	AbstractPreferences(final Scope scope, final String key)
	{
		Preconditions.checkArgument(!Strings.isNullOrEmpty(key));
		
		this.store = scope.store();
		this.key   = Activator.PLUGIN_ID + "." + key;
	}
	
	static enum Scope
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
	
	final String toStringOrFailure(final String key, final Object value)
	{
		try
		{
			return Serialization.writeToString(value);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Unable to write object under key " + key + " to string", e);
		}
	}

	final Object fromStringOrFailure(final String key, final String value)
	{
		try
		{
			return Serialization.readFromString(value);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Unable to read object under key " + key + " from string", e);
		}
	}

	final String key(final String name)
	{
		return this.key + "." + name;
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
	
	public final void save() throws IOException
	{
		if (this.store instanceof IPersistentPreferenceStore)
		{
			((IPersistentPreferenceStore) this.store).save();
		}
	}
}
