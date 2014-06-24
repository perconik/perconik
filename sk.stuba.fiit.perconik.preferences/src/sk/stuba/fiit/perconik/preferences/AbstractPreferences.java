package sk.stuba.fiit.perconik.preferences;

import static com.google.common.base.Preconditions.checkNotNull;
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
		this.scope = checkNotNull(scope);
		this.store = checkNotNull(scope.store());
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

	public final Scope getScope()
	{
		return this.scope;
	}
	
	public final IPreferenceStore getStore()
	{
		return this.store;
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
