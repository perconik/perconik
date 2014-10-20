package sk.stuba.fiit.perconik.core.preferences;

import java.util.Set;

import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;
import sk.stuba.fiit.perconik.core.preferences.plugin.Activator;
import sk.stuba.fiit.perconik.preferences.AbstractPreferences;

import static sk.stuba.fiit.perconik.core.preferences.ListenerPreferences.Keys.persistence;

/**
 * Listener preferences. Supports both <i>default</i>
 * and <i>instance</i> (actually used and stored) scopes.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenerPreferences extends AbstractRegistrationPreferences<ListenerPersistenceData> {
  static final String qualifier = Activator.PLUGIN_ID + ".listeners";

  private ListenerPreferences(final Scope scope) {
    super(scope, qualifier);
  }

  /**
   * Used to aid in default listener preferences initialization.
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
     * initialize default listener preferences.
     *
     * <p><b>Warning:</b> Clients should not call this method.
     * It will be called automatically by the preference initializer
     * when the appropriate default preference node is accessed.
     */
    @Override
    public void initializeDefaultPreferences() {
      Set<ListenerPersistenceData> data = ListenerPersistenceData.defaults();

      ListenerPreferences.getDefault().setListenerPersistenceData(data);
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String persistence = qualifier + ".persistence";
  }

  /**
   * Gets default scoped core preferences.
   */
  public static ListenerPreferences getDefault() {
    return new ListenerPreferences(Scope.DEFAULT);
  }

  /**
   * Gets configuration scoped core preferences.
   */
  public static ListenerPreferences getShared() {
    return new ListenerPreferences(Scope.CONFIGURATION);
  }

  /**
   * Sets listener persistence data.
   * @param data listener persistence data
   * @throws NullPointerException if {@code data} is {@code null}
   */
  public void setListenerPersistenceData(final Set<ListenerPersistenceData> data) {
    this.setRegistrations(persistence, data);
  }

  /**
   * Gets listener persistence data.
   */
  public Set<ListenerPersistenceData> getListenerPersistenceData() {
    return this.getRegistrations(persistence);
  }

  @Override
  Set<ListenerPersistenceData> getDefaultRegistrations() {
    return ListenerPersistenceData.defaults();
  }
}
