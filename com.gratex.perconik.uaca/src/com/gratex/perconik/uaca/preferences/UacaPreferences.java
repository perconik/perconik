package com.gratex.perconik.uaca.preferences;

import java.net.URL;
import java.util.Map;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import sk.stuba.fiit.perconik.preferences.AbstractPreferences;

import static com.google.common.collect.Maps.newLinkedHashMap;

import static com.gratex.perconik.uaca.plugin.Activator.PLUGIN_ID;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.applicationUrl;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.checkConnection;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.displayErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logEvents;

import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public final class UacaPreferences extends AbstractPreferences implements UacaOptions {
  static final String qualifier = join(PLUGIN_ID, "preferences");

  private static final UacaPreferences shared = new UacaPreferences(Scope.CONFIGURATION);

  private final ScopedPreferenceStore store;

  private UacaPreferences(final Scope scope) {
    super(scope, qualifier);

    this.store = new ScopedPreferenceStore(scope.context(), PLUGIN_ID);
  }

  public static final class Initializer extends AbstractPreferenceInitializer {
    public Initializer() {}

    @Override
    public void initializeDefaultPreferences() {
      UacaPreferences preferences = UacaPreferences.getDefault();
      IPreferenceStore store = preferences.getPreferenceStore();

      store.setDefault(applicationUrl, "http://localhost:16375");
      store.setDefault(checkConnection, true);
      store.setDefault(displayErrors, true);
      store.setDefault(logErrors, true);
      store.setDefault(logEvents, false);
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String applicationUrl = join(qualifier, "applicationUrl");

    public static final String checkConnection = join(qualifier, "checkConnection");

    public static final String displayErrors = join(qualifier, "displayErrors");

    public static final String logErrors = join(qualifier, "logErrors");

    public static final String logEvents = join(qualifier, "logEvents");
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
    Map<String, Object> map = newLinkedHashMap();

    map.put(applicationUrl, this.getApplicationUrl());
    map.put(checkConnection, this.isConnectionCheckEnabled());
    map.put(displayErrors, this.isErrorDialogEnabled());
    map.put(logErrors, this.isErrorLogEnabled());
    map.put(logEvents, this.isEventLogEnabled());

    return map;
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
