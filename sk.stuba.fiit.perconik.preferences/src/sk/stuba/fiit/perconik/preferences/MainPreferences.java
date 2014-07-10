package sk.stuba.fiit.perconik.preferences;

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
	static final String qualifier = Activator.PLUGIN_ID + ".main";

	private MainPreferences(final Scope scope)
	{
		super(scope, qualifier);
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
		}
	}

	public static final class Keys extends AbstractPreferences.Keys
	{
	}

	/**
	 * Gets default scoped core preferences.
	 */
	public static final MainPreferences getDefault()
	{
		return new MainPreferences(Scope.DEFAULT);
	}

	/**
	 * Gets configuration scoped core preferences.
	 */
	public static final MainPreferences getConfiguration()
	{
		return new MainPreferences(Scope.CONFIGURATION);
	}
}
