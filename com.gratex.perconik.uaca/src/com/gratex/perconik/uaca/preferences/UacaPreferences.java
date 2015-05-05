package com.gratex.perconik.uaca.preferences;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Map;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import sk.stuba.fiit.perconik.preferences.AbstractPreferences;

import static com.gratex.perconik.uaca.plugin.Activator.PLUGIN_ID;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.applicationUrl;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.checkConnection;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.displayErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logEvents;

import static sk.stuba.fiit.perconik.eclipse.jface.preference.PreferenceStores.setDefault;
import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeCause;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.defaults;
import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public final class UacaPreferences extends AbstractPreferences implements Serializable, UacaOptions {
  private static final long serialVersionUID = -9108586744508719837L;

  private static final UacaPreferences shared = new UacaPreferences(Scope.CONFIGURATION);

  private final ScopedPreferenceStore store;

  private UacaPreferences(final Scope scope) {
    super(scope, Schema.qualifier);

    this.store = new ScopedPreferenceStore(scope.context(), PLUGIN_ID);
  }

  public static final class Initializer extends AbstractPreferenceInitializer {
    public Initializer() {}

    @Override
    public void initializeDefaultPreferences() {
      UacaPreferences preferences = UacaPreferences.getDefault();
      IPreferenceStore store = preferences.getPreferenceStore();

      setDefault(store, defaults(Schema.accessors()));
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String applicationUrl = Schema.applicationUrl.getKey();

    public static final String checkConnection = Schema.checkConnection.getKey();

    public static final String displayErrors = Schema.displayErrors.getKey();

    public static final String logErrors = Schema.logErrors.getKey();

    public static final String logEvents = Schema.logEvents.getKey();
  }

  /**
   * Gets default scoped core preferences.
   */
  public static UacaPreferences getDefault() {
    return new UacaPreferences(Scope.DEFAULT);
  }

  /**
   * Gets configuration scoped core preferences.
   */
  public static UacaPreferences getShared() {
    return shared;
  }

  private static final class SerializationProxy implements Serializable {
    private static final long serialVersionUID = -2729263083624118286L;

    private final Scope scope;

    SerializationProxy(final UacaPreferences preferences) {
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
    return this.toMap().get(key);
  }

  /**
   * Returns the backing scoped preference store.
   */
  public ScopedPreferenceStore getPreferenceStore() {
    return this.store;
  }

  public URL getApplicationUrl() {
    IPreferenceStore store = this.getPreferenceStore();

    return newUrl(store.getString(applicationUrl));
  }

  public boolean isConnectionCheckEnabled() {
    return this.getPreferenceStore().getBoolean(checkConnection);
  }

  public boolean isErrorDialogEnabled() {
    return this.getPreferenceStore().getBoolean(displayErrors);
  }

  public boolean isErrorLogEnabled() {
    return this.getPreferenceStore().getBoolean(logErrors);
  }

  public boolean isEventLogEnabled() {
    return this.getPreferenceStore().getBoolean(logEvents);
  }
}
