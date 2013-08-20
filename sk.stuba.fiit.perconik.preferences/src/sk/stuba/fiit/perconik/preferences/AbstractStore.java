package sk.stuba.fiit.perconik.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import com.google.common.base.Preconditions;

class AbstractStore<T>
{
	final IPreferenceStore store;
	
	final String key;
	
	AbstractStore(final IPreferenceStore store, final String key)
	{
		Preconditions.checkArgument(!key.isEmpty());
		
		this.store = Preconditions.checkNotNull(store);
		this.key   = key;
	}
	
	final String toString(final T o)
	{
		try
		{
			return Serialization.writeToString(o);
		}
		catch (Exception e)
		{
			throw new IllegalStateException("Unable to write object to string", e);
		}
	}

	final T fromString(final String string)
	{
		try
		{
			return (T) Serialization.readFromString(string);
		}
		catch (Exception e)
		{
			throw new IllegalStateException("Unable to read object from string", e);
		}
	}
}
