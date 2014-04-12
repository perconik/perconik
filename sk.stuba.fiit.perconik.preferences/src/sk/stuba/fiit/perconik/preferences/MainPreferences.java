package sk.stuba.fiit.perconik.preferences;

import static sk.stuba.fiit.perconik.preferences.MainPreferences.Keys.validate;
import sk.stuba.fiit.perconik.preferences.plugin.Activator;

/**
 * Main preferences. Supports both <i>default</i>
 * and <i>instance</i> (actually used and stored) scopes.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MainPreferences extends AbstractPreferences
{
	private MainPreferences(final Scope scope)
	{
		super(scope);
	}
	
	/**
	 * Used to aid in default main preferences initialization.
	 * 
	 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
	 * 
	 * @author Pavol Zbell
	 * @since 1.0
	 */
	public static final class Initializer extends AbstractPreferences.Initializer
	{
		/**
		 * The constructor.
		 */
		public Initializer()
		{
		}

		/**
		 * Called by the preference initializer to
		 * initialize default main preferences.
		 * 
		 * <p><b>Warning:</b> Clients should not call this method.
		 * It will be called automatically by the preference initializer
		 * when the appropriate default preference node is accessed.
		 */
		@Override
		public final void initializeDefaultPreferences()
		{
			MainPreferences.getDefault().setValidateJava(true);
		}
	}
	
	public static final class Keys extends AbstractPreferences.Keys
	{
		static final String prefix = Activator.PLUGIN_ID + ".main.";
		
		public static final String validate = prefix + "validate.java";
	}

	/**
	 * Gets default core preferences.
	 */
	public static final MainPreferences getDefault()
	{
		return new MainPreferences(Scope.DEFAULT);
	}
	
	/**
	 * Gets core preferences.
	 */
	public static final MainPreferences getInstance()
	{
		return new MainPreferences(Scope.INSTANCE);
	}
	
	public final void setValidateJava(final boolean value)
	{
		this.store.setValue(validate, value);
	}

	public final boolean getValidateJava()
	{
		return this.store.getBoolean(validate);
	}
}
