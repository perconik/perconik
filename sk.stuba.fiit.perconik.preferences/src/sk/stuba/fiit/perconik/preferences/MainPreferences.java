package sk.stuba.fiit.perconik.preferences;

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
		super(scope, "main");
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

	// TODO add
//	public final void setAdmin(final boolean value)
//	{
//		this.store.setValue(this.key(admin), value);
//	}
//	
//	public final boolean isAdmin()
//	{
//		return this.store.getBoolean(this.key(admin));
//	}
}
