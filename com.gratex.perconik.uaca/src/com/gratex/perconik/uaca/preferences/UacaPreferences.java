package com.gratex.perconik.uaca.preferences;

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

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public final class UacaPreferences extends AbstractPreferences implements UacaOptions {
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

      store.setDefault(applicationUrl, Schema.applicationUrl.getDefaultValue().toString());
      store.setDefault(checkConnection, Schema.checkConnection.getDefaultValue());
      store.setDefault(displayErrors, Schema.displayErrors.getDefaultValue());
      store.setDefault(logErrors, Schema.logErrors.getDefaultValue());
      store.setDefault(logEvents, Schema.logEvents.getDefaultValue());
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

  /**
   * Throws {@code UnsupportedOperationException}.
   */
  public void fromMap(final Map<String, Object> map) {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns a read only snapshot of UACA preferences.
   */
  public Map<String, Object> toMap() {
    return Schema.toMap(this);
  }

  @Override
  public String toString() {
    return this.toMap().toString();
  }

  public Object put(final String key, final Object value) {
    throw new UnsupportedOperationException();
  }

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
