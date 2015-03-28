package com.gratex.perconik.uaca.preferences;

import java.net.URL;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import sk.stuba.fiit.perconik.utilities.configuration.AbstractOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Configurables;
import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static com.google.common.base.Preconditions.checkNotNull;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Maps.newLinkedHashMap;

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

    static final ImmutableList<OptionAccessor<?>> accessors = copyOf(Configurables.accessors(Schema.class));

    private Schema() {}

    static Map<String, Object> toMap(final UacaOptions options) {
      Map<String, Object> map = newLinkedHashMap();

      map.put(applicationUrl.getKey(), options.getApplicationUrl());
      map.put(checkConnection.getKey(), options.isConnectionCheckEnabled());
      map.put(displayErrors.getKey(), options.isErrorDialogEnabled());
      map.put(logErrors.getKey(), options.isErrorLogEnabled());
      map.put(logEvents.getKey(), options.isEventLogEnabled());

      return map;
    }

    public static ImmutableList<OptionAccessor<?>> accessors() {
      return accessors;
    }
  }

  public static final class View extends AbstractOptions implements UacaOptions {
    private final Options options;

    private View(final Options options) {
      this.options = checkNotNull(options);
    }

    public static UacaOptions of(final Options options) {
      return new View(options);
    }

    public Map<String, Object> toMap() {
      return Schema.toMap(this);
    }

    public URL getApplicationUrl() {
      return Schema.applicationUrl.getValue(this.options);
    }

    public boolean isConnectionCheckEnabled() {
      return Schema.checkConnection.getValue(this.options);
    }

    public boolean isErrorDialogEnabled() {
      return Schema.displayErrors.getValue(this.options);
    }

    public boolean isErrorLogEnabled() {
      return Schema.logErrors.getValue(this.options);
    }

    public boolean isEventLogEnabled() {
      return Schema.logEvents.getValue(this.options);
    }
  }

  public URL getApplicationUrl();

  public boolean isConnectionCheckEnabled();

  public boolean isErrorDialogEnabled();

  public boolean isErrorLogEnabled();

  public boolean isEventLogEnabled();
}
