package com.gratex.perconik.uaca.preferences;

import java.net.URL;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.gratex.perconik.uaca.plugin.Activator;

import sk.stuba.fiit.perconik.preferences.AbstractPreferences;
import sk.stuba.fiit.perconik.utilities.net.UniformResources;

import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.checkConnection;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.displayErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logEvents;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.uacaUrl;

public final class UacaPreferences extends AbstractPreferences {
  private static final UacaPreferences shared = new UacaPreferences(Scope.CONFIGURATION);

  static final String qualifier = Activator.PLUGIN_ID + ".preferences";

  private final IPreferenceStore store;

  private UacaPreferences(final Scope scope) {
    super(scope, qualifier);

    this.store = new ScopedPreferenceStore(scope.context(), qualifier);
  }

  /**
   * Used to aid in default UACA preferences initialization.
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
     * initialize default main preferences.
     *
     * <p><b>Warning:</b> Clients should not call this method.
     * It will be called automatically by the preference initializer
     * when the appropriate default preference node is accessed.
     */
    @Override
    public final void initializeDefaultPreferences() {
      IEclipsePreferences preferences = UacaPreferences.getDefault().asEclipsePreferences();

      preferences.putBoolean(checkConnection, true);
      preferences.putBoolean(displayErrors, true);

      preferences.putBoolean(logErrors, true);
      preferences.putBoolean(logEvents, false);

      preferences.put(uacaUrl, "http://localhost:16375");
    }
  }

  public static final class Keys extends AbstractPreferences.Keys {
    public static final String checkConnection = qualifier + ".checkConnection";

    public static final String displayErrors = qualifier + ".displayErrors";

    public static final String logErrors = qualifier + ".logErrors";

    public static final String logEvents = qualifier + ".logEvents";

    public static final String uacaUrl = qualifier + ".uacaUrl";
  }

  /**
   * Gets default scoped core preferences.
   */
  public static final UacaPreferences getDefault() {
    return new UacaPreferences(Scope.DEFAULT);
  }

  /**
   * Gets configuration scoped core preferences.
   */
  public static final UacaPreferences getShared() {
    return shared;
  }

  public final IEclipsePreferences asEclipsePreferences() {
    return this.data();
  }

  public final IPreferenceStore asPreferenceStore() {
    return this.store;
  }

  public final URL getUacaUrl() {
    return UniformResources.newUrl(this.store.getString(uacaUrl));
  }

  public final boolean isErrorLoggerEnabled() {
    return this.store.getBoolean(logErrors);
  }

  public final boolean isEventLoggerEnabled() {
    return this.store.getBoolean(logEvents);
  }
}
