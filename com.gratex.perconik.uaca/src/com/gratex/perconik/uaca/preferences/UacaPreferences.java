package com.gratex.perconik.uaca.preferences;

import java.net.URL;
import java.util.Map;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.gratex.perconik.uaca.plugin.Activator;

import sk.stuba.fiit.perconik.preferences.AbstractPreferences;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static com.google.common.collect.Maps.newLinkedHashMap;

import static com.gratex.perconik.uaca.plugin.Activator.PLUGIN_ID;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.applicationUrl;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.checkConnection;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.displayErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logEvents;

import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public final class UacaPreferences implements UacaOptions {
  static final String qualifier = join(PLUGIN_ID, "preferences");

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

  public static UacaPreferences getShared() {
    return shared;
  }

  private enum ReadOnlyOptionsView implements Options {
    instance;

    public void fromMap(final Map<String, Object> map) {
      throw new UnsupportedOperationException();
    }

    public Map<String, Object> toMap() {
      Map<String, Object> map = newLinkedHashMap();

      map.put(applicationUrl, shared.getApplicationUrl());
      map.put(checkConnection, shared.isConnectionCheckEnabled());
      map.put(displayErrors, shared.isErrorDialogEnabled());
      map.put(logErrors, shared.isErrorLogEnabled());
      map.put(logEvents, shared.isEventLogEnabled());

      return map;
    }

    @Override
    public String toString() {
      return this.toMap().toString();
    }
  }

  @SuppressWarnings("static-method")
  final Options asReadOnlyOptionsView() {
    return ReadOnlyOptionsView.instance;
  }

  /**
   * Returns a read only {@code Options} view of UACA preferences.
   */
  public Options asOptions() {
    return this.asReadOnlyOptionsView();
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
    return this.asReadOnlyOptionsView().toMap();
  }

  @Override
  public String toString() {
    return this.asReadOnlyOptionsView().toString();
  }

  public IPreferenceStore getPreferenceStore() {
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
