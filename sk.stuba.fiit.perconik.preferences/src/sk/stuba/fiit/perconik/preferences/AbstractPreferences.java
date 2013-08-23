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
	
	static final String toString(final Object o)
	{
		try
		{
			return Serialization.writeToString(o);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Unable to write object to string", e);
		}
	}

	static final Object fromString(final String s)
	{
		try
		{
			return Serialization.readFromString(s);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Unable to read object from string", e);
		}
	}

	final String key(final String name)
	{
		return this.key + "." + name;
	}

	protected final void setDefault(final String key, final Object value)
	{
		this.store.setDefault(key, toString(value));
	}

	protected final Object getDefaultObject(final String key)
	{
		return fromString(this.store.getDefaultString(key));
	}

	protected final void setValue(final String key, final Object value)
	{
		this.store.setValue(key, toString(value));
	}

	protected final Object getObject(final String key)
	{
		return fromString(this.store.getString(key));
	}
	
	public final void save() throws IOException
	{
		if (this.store instanceof IPersistentPreferenceStore)
		{
			((IPersistentPreferenceStore) this.store).save();
		}
	}
}
