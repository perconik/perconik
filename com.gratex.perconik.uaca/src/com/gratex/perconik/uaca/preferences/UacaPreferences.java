package com.gratex.perconik.uaca.preferences;

import java.net.URL;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.gratex.perconik.uaca.plugin.Activator;

import sk.stuba.fiit.perconik.preferences.AbstractPreferences;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;

import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.checkConnection;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.displayErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logEvents;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.uacaUrl;

public final class UacaPreferences {
  static final String qualifier = Activator.PLUGIN_ID + ".preferences";

  static final UacaPreferences shared = new UacaPreferences();

  private final IPreferenceStore store;

  private UacaPreferences() {
    this.store = Activator.defaultInstance().getPreferenceStore();
  }

  public static final class Initializer extends AbstractPreferenceInitializer {
    public Initializer() {}

    @Override
    public void initializeDefaultPreferences() {
      IPreferenceStore store = shared.getPreferenceStore();

      store.setDefault(checkConnection, true);
      store.setDefault(displayErrors, true);

      store.setDefault(logErrors, true);
      store.setDefault(logEvents, false);

      store.setDefault(uacaUrl, "http://localhost:16375");
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String checkConnection = qualifier + ".checkConnection";

    public static final String displayErrors = qualifier + ".displayErrors";

    public static final String logErrors = qualifier + ".logErrors";

    public static final String logEvents = qualifier + ".logEvents";

    public static final String uacaUrl = qualifier + ".uacaUrl";
  }

  public static UacaPreferences getShared() {
    return shared;
  }

  public IPreferenceStore getPreferenceStore() {
    return this.store;
  }

  public URL getUacaUrl() {
    IPreferenceStore store = getPreferenceStore();

    return UniformResources.newUrl(store.getString(uacaUrl));
  }

  public boolean isErrorLoggerEnabled() {
    return getPreferenceStore().getBoolean(logErrors);
  }

  public boolean isEventLoggerEnabled() {
    return getPreferenceStore().getBoolean(logEvents);
  }
}
