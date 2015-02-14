package com.gratex.perconik.uaca.preferences;

import java.net.URL;

import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static com.gratex.perconik.uaca.plugin.Activator.PLUGIN_ID;

import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.option;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.booleanParser;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.urlParser;
import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public interface UacaOptions extends Options {
  public static final class Schema {
    static final String qualifier = join(PLUGIN_ID, "preferences");

    public static final OptionAccessor<URL> applicationUrl = option(urlParser(), join(qualifier, "applicationUrl"), newUrl("http://localhost:16375"));

    public static final OptionAccessor<Boolean> checkConnection = option(booleanParser(), join(qualifier, "checkConnection"), true);

    public static final OptionAccessor<Boolean> displayErrors = option(booleanParser(), join(qualifier, "displayErrors"), true);

    public static final OptionAccessor<Boolean> logErrors = option(booleanParser(), join(qualifier, "logErrors"), true);

    public static final OptionAccessor<Boolean> logEvents = option(booleanParser(), join(qualifier, "logEvents"), false);

    private Schema() {}
  }

  public URL getApplicationUrl();

  public boolean isConnectionCheckEnabled();

  public boolean isErrorDialogEnabled();

  public boolean isErrorLogEnabled();

  public boolean isEventLogEnabled();
}
