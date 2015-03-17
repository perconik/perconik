package sk.stuba.fiit.perconik.activity.preferences;

import sk.stuba.fiit.perconik.preferences.AbstractObjectPreferences;
import sk.stuba.fiit.perconik.preferences.AbstractPreferences;

import static sk.stuba.fiit.perconik.core.plugin.Activator.classResolver;
import static sk.stuba.fiit.perconik.core.preferences.plugin.Activator.PLUGIN_ID;
import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;

/**
 * Activity preferences. Supports both <i>default</i>
 * and <i>configuration</i> (actually used and stored) scopes.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ActivityPreferences extends AbstractObjectPreferences {
  static final String qualifier = join(PLUGIN_ID, "preferences");

  private ActivityPreferences(final Scope scope) {
    super(scope, qualifier, classResolver());
  }

  /**
   * Used to aid in default activity preferences initialization.
   *
   * <p><b>Warning:</b> Users should not explicitly instantiate this class.
   *
   * @author Pavol Zbell
   * @since 1.0
   */
  public static final class Initializer extends AbstractPreferences.Initializer {
    /**
     * The constructor.
     */
    public Initializer() {}

    /**
     * Called by the preference initializer to
     * initialize default activity preferences.
     *
     * <p><b>Warning:</b> Clients should not call this method.
     * It will be called automatically by the preference initializer
     * when the appropriate default preference node is accessed.
     */
    @Override
    public void initializeDefaultPreferences() {
      ActivityPreferences preferences = ActivityPreferences.getDefault();

      // TODO
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String configuration = join(qualifier, "configuration");
  }

  /**
   * Gets default scoped core preferences.
   */
  public static ActivityPreferences getDefault() {
    return new ActivityPreferences(Scope.DEFAULT);
  }

  /**
   * Gets configuration scoped core preferences.
   */
  public static ActivityPreferences getShared() {
    return new ActivityPreferences(Scope.CONFIGURATION);
  }

  // TODO
}
