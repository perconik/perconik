package com.gratex.perconik.uaca.preferences;

import java.io.Serializable;
import java.net.URL;
import java.util.Map;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import sk.stuba.fiit.perconik.utilities.configuration.AbstractOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Configurables;
import sk.stuba.fiit.perconik.utilities.configuration.OptionAccessor;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Maps.newLinkedHashMap;

import static com.gratex.perconik.uaca.GenericUacaProxyConstants.DEFAULT_APPLICATION_URL;
import static com.gratex.perconik.uaca.plugin.Activator.PLUGIN_ID;

import static sk.stuba.fiit.perconik.preferences.AbstractPreferences.Keys.join;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.option;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.booleanParser;
import static sk.stuba.fiit.perconik.utilities.configuration.OptionParsers.urlParser;

public interface UacaOptions extends Options {
  public static final class Schema {
    static final String qualifier = join(PLUGIN_ID, "preferences");

    public static final OptionAccessor<URL> applicationUrl = option(urlParser(), join(qualifier, "applicationUrl"), DEFAULT_APPLICATION_URL);

    public static final OptionAccessor<Boolean> checkConnection = option(booleanParser(), join(qualifier, "checkConnection"), true);

    public static final OptionAccessor<Boolean> displayErrors = option(booleanParser(), join(qualifier, "displayErrors"), true);

    public static final OptionAccessor<Boolean> logErrors = option(booleanParser(), join(qualifier, "logErrors"), true);

    public static final OptionAccessor<Boolean> logEvents = option(booleanParser(), join(qualifier, "logEvents"), false);

    static final ImmutableList<OptionAccessor<?>> accessors = copyOf(Configurables.accessors(Schema.class));

    private Schema() {}

    public static ImmutableCollection<OptionAccessor<?>> accessors() {
      return accessors;
    }

    static Map<String, Object> toMap(final UacaOptions options) {
      Map<String, Object> map = newLinkedHashMap();

      map.put(applicationUrl.getKey(), options.getApplicationUrl());
      map.put(checkConnection.getKey(), options.isConnectionCheckEnabled());
      map.put(displayErrors.getKey(), options.isErrorDialogEnabled());
      map.put(logErrors.getKey(), options.isErrorLogEnabled());
      map.put(logEvents.getKey(), options.isEventLogEnabled());

      return map;
    }
  }

  public static final class View extends AbstractOptions implements Serializable, UacaOptions {
    private static final long serialVersionUID = 0L;

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
