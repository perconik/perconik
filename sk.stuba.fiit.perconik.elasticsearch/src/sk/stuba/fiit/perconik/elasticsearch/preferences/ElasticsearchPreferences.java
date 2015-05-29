package sk.stuba.fiit.perconik.elasticsearch.preferences;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import org.elasticsearch.common.settings.Settings;

import sk.stuba.fiit.perconik.eclipse.jface.preference.AbstractPreferenceStoreOptionsReader;
import sk.stuba.fiit.perconik.eclipse.jface.preference.PreferenceStoreOptions;
import sk.stuba.fiit.perconik.preferences.AbstractPreferences;
import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.OptionsReader;

import static sk.stuba.fiit.perconik.eclipse.jface.preference.PreferenceStores.setDefault;
import static sk.stuba.fiit.perconik.elasticsearch.plugin.Activator.PLUGIN_ID;
import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeCause;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.defaults;

public final class ElasticsearchPreferences extends AbstractPreferences implements ElasticsearchOptions, PreferenceStoreOptions, Serializable {
  private static final long serialVersionUID = -3684186056051078088L;

  private static final ElasticsearchPreferences shared = new ElasticsearchPreferences(Scope.CONFIGURATION);

  private final ScopedPreferenceStore store;

  private final OptionsReader reader;

  private ElasticsearchPreferences(final Scope scope) {
    super(scope, Schema.qualifier);

    this.store = new ScopedPreferenceStore(scope.context(), PLUGIN_ID);

    this.reader = new AbstractPreferenceStoreOptionsReader() {
      @Override
      protected PreferenceStoreOptions options() {
        return ElasticsearchPreferences.this;
      }

      @Override
      protected Object fromStringToRaw(final String value) {
        return value;
      }
    };
  }

  public static final class Initializer extends AbstractPreferenceInitializer {
    public Initializer() {}

    @Override
    public void initializeDefaultPreferences() {
      ElasticsearchPreferences preferences = ElasticsearchPreferences.getDefault();
      IPreferenceStore store = preferences.getPreferenceStore();

      setDefault(store, defaults(Schema.accessors()), ElasticsearchOptions.Schema.CustomOptionsConverter.INSTANCE);
    }
  }

  /**
   * Gets default scoped core preferences.
   */
  public static ElasticsearchPreferences getDefault() {
    return new ElasticsearchPreferences(Scope.DEFAULT);
  }

  /**
   * Gets configuration scoped core preferences.
   */
  public static ElasticsearchPreferences getShared() {
    return shared;
  }

  private static final class SerializationProxy implements Serializable {
    private static final long serialVersionUID = -8939558618419864707L;

    private final Scope scope;

    SerializationProxy(final ElasticsearchPreferences preferences) {
      this.scope = preferences.scope();
    }

    private Object readResolve() throws InvalidObjectException {
      try {
        switch (this.scope) {
          case CONFIGURATION:
            return getShared();

          case DEFAULT:
            return getDefault();

          case INSTANCE:
            throw new IllegalStateException("Instance scope not supported");

          default:
            return new IllegalStateException("Unknow scope " + this.scope);
        }
      } catch (Exception e) {
        throw initializeCause(new InvalidObjectException("Unknown deserialization error"), e);
      }
    }
  }

  @SuppressWarnings({"static-method", "unused"})
  private void readObject(final ObjectInputStream in) throws InvalidObjectException {
    throw new InvalidObjectException("Serialization proxy required");
  }

  private Object writeReplace() {
    return new SerializationProxy(this);
  }

  /**
   * Throws {@code UnsupportedOperationException}.
   */
  public void fromMap(final Map<String, Object> map) {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns a read only snapshot of preferences.
   */
  public Map<String, Object> toMap() {
    return Schema.toMap(this);
  }

  public Settings toSettings() {
    return Schema.toSettings(this);
  }

  @Override
  public String toString() {
    return this.toMap().toString();
  }

  /**
   * Throws {@code UnsupportedOperationException}.
   */
  public Object put(final String key, final Object value) {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns preference value for specified key.
   */
  public Object get(final String key) {
    OptionAccessor<?> accessor = Schema.accessors.get(key);

    if (accessor == null) {
      return null;
    }

    return accessor.getValue(this.reader);
  }

  /**
   * Returns the backing scoped preference store.
   */
  public ScopedPreferenceStore getPreferenceStore() {
    return this.store;
  }
}
