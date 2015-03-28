package sk.stuba.fiit.perconik.core.preferences;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;
import sk.stuba.fiit.perconik.preferences.AbstractPreferences;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.core.preferences.ResourcePreferences.Keys.configuration;
import static sk.stuba.fiit.perconik.core.preferences.ResourcePreferences.Keys.persistence;
import static sk.stuba.fiit.perconik.core.preferences.plugin.Activator.PLUGIN_ID;
import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;

/**
 * Resource preferences. Supports both <i>default</i>
 * and <i>configuration</i> (actually used and stored) scopes.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourcePreferences extends RegistrationWithOptionPreferences<ResourcePersistenceData, String> {
  static final String qualifier = join(PLUGIN_ID, "resources");

  private ResourcePreferences(final Scope scope) {
    super(scope, qualifier);
  }

  /**
   * Used to aid in default resource preferences initialization.
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
     * initialize default resource preferences.
     *
     * <p><b>Warning:</b> Clients should not call this method.
     * It will be called automatically by the preference initializer
     * when the appropriate default preference node is accessed.
     */
    @Override
    public void initializeDefaultPreferences() {
      ResourcePreferences preferences = ResourcePreferences.getDefault();

      preferences.setResourcePersistenceData(preferences.getDefaultRegistrations());
      preferences.setResourceConfigurationData(preferences.getDefaultOptions());
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String persistence = join(qualifier, "persistence");

    public static final String configuration = join(qualifier, "configuration");
  }

  /**
   * Gets default scoped core preferences.
   */
  public static ResourcePreferences getDefault() {
    return new ResourcePreferences(Scope.DEFAULT);
  }

  /**
   * Gets configuration scoped core preferences.
   */
  public static ResourcePreferences getShared() {
    return new ResourcePreferences(Scope.CONFIGURATION);
  }

  @Override
  Set<ResourcePersistenceData> castRegistrations(final Object object) {
    Set<ResourcePersistenceData> registrations = Set.class.cast(checkNotNull(object));

    for (Object element: registrations) {
      ResourcePersistenceData.class.cast(checkNotNull(element));
    }

    return registrations;
  }

  @Override
  Map<String, Options> castOptions(final Object object) {
    Map<String, Options> options = Map.class.cast(checkNotNull(object));

    for (Entry<String, Options> entry: options.entrySet()) {
      String.class.cast(checkNotNull(entry.getKey()));
      Options.class.cast(checkNotNull(entry.getValue()));
    }

    return options;
  }

  /**
   * Sets resource persistence data.
   * @param data resource persistence data
   * @throws ClassCastException if {@code data} contain invalid types
   * @throws NullPointerException if {@code data} contain {@code null} references
   */
  public void setResourcePersistenceData(final Set<ResourcePersistenceData> data) {
    this.setRegistrations(persistence, data);
  }

  /**
   * Sets resource configuration data.
   * @param data resource configuration data
   * @throws ClassCastException if {@code data} contain invalid types
   * @throws NullPointerException if {@code data} contain {@code null} references
   */
  public void setResourceConfigurationData(final Map<String, Options> data) {
    this.setOptions(configuration, data);
  }

  /**
   * Gets resource persistence data.
   * @throws ClassCastException if data contain invalid types
   * @throws IllegalStateException if data contain {@code null} references
   */
  public Set<ResourcePersistenceData> getResourcePersistenceData() {
    return this.getRegistrations(persistence);
  }

  /**
   * Gets resource configuration data.
   * @throws ClassCastException if data contain invalid types
   * @throws IllegalStateException if data contain {@code null} references
   */
  public Map<String, Options> getResourceConfigurationData() {
    return this.getOptions(configuration);
  }

  @Override
  Set<ResourcePersistenceData> getDefaultRegistrations() {
    return ResourcePersistenceData.defaults();
  }

  @Override
  Map<String, Options> getDefaultOptions() {
    Map<String, Options> options = newHashMap();

    for (ResourcePersistenceData data: this.getDefaultRegistrations()) {
      this.putDefaultOptionsIfPresent(options, data.getResourceName(), data.getResource());
    }

    return options;
  }
}
