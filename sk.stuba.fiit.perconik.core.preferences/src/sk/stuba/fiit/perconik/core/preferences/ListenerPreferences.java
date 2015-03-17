package sk.stuba.fiit.perconik.core.preferences;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;
import sk.stuba.fiit.perconik.preferences.AbstractPreferences;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.core.preferences.ListenerPreferences.Keys.configuration;
import static sk.stuba.fiit.perconik.core.preferences.ListenerPreferences.Keys.persistence;
import static sk.stuba.fiit.perconik.core.preferences.plugin.Activator.PLUGIN_ID;
import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;

/**
 * Listener preferences. Supports both <i>default</i>
 * and <i>configuration</i> (actually used and stored) scopes.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenerPreferences extends RegistrationWithOptionPreferences<ListenerPersistenceData, Class<? extends Listener>> {
  static final String qualifier = join(PLUGIN_ID, "listeners");

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
      ListenerPreferences preferences = ListenerPreferences.getDefault();

      preferences.setListenerPersistenceData(preferences.getDefaultRegistrations());
      preferences.setListenerConfigurationData(preferences.getDefaultOptions());
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String persistence = join(qualifier, "persistence");

    public static final String configuration = join(qualifier, "configuration");
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

  @Override
  Set<ListenerPersistenceData> castRegistrations(final Object object) {
    Set<ListenerPersistenceData> registrations = Set.class.cast(requireNonNull(object));

    for (Object element: registrations) {
      ListenerPersistenceData.class.cast(requireNonNull(element));
    }

    return registrations;
  }

  @Override
  Map<Class<? extends Listener>, Options> castOptions(final Object object) {
    Map<Class<? extends Listener>, Options> options = Map.class.cast(requireNonNull(object));

    for (Entry<Class<? extends Listener>, Options> entry: options.entrySet()) {
      Class.class.cast(requireNonNull(entry.getKey())).asSubclass(Listener.class);
      Options.class.cast(requireNonNull(entry.getValue()));
    }

    return options;
  }

  /**
   * Sets listener persistence data.
   * @param data listener persistence data
   * @throws ClassCastException if {@code data} contain invalid types
   * @throws NullPointerException if {@code data} contain {@code null} references
   */
  public void setListenerPersistenceData(final Set<ListenerPersistenceData> data) {
    this.setRegistrations(persistence, data);
  }

  /**
   * Sets listener configuration data.
   * @param data listener configuration data
   * @throws ClassCastException if {@code data} contain invalid types
   * @throws NullPointerException if {@code data} contain {@code null} references
   */
  public void setListenerConfigurationData(final Map<Class<? extends Listener>, Options> data) {
    this.setOptions(configuration, data);
  }

  /**
   * Gets listener persistence data.
   * @throws ClassCastException if data contain invalid types
   * @throws IllegalStateException if data contain {@code null} references
   */
  public Set<ListenerPersistenceData> getListenerPersistenceData() {
    return this.getRegistrations(persistence);
  }

  /**
   * Gets listener configuration data.
   * @throws ClassCastException if data contain invalid types
   * @throws IllegalStateException if data contain {@code null} references
   */
  public Map<Class<? extends Listener>, Options> getListenerConfigurationData() {
    return this.getOptions(configuration);
  }

  @Override
  Set<ListenerPersistenceData> getDefaultRegistrations() {
    return ListenerPersistenceData.defaults();
  }

  @Override
  Map<Class<? extends Listener>, Options> getDefaultOptions() {
    Map<Class<? extends Listener>, Options> options = newHashMap();

    for (ListenerPersistenceData data: this.getDefaultRegistrations()) {
      this.putDefaultOptionsIfPresent(options, data.getListenerClass(), data.getListener());
    }

    return options;
  }
}
