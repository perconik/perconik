package com.gratex.perconik.uaca;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nullable;

import com.gratex.perconik.uaca.plugin.Activator;
import com.gratex.perconik.uaca.preferences.UacaOptions;
import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.eclipse.core.runtime.ForwardingPluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.configuration.Configurable;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.utilities.SmartStringBuilder.builder;

public final class UacaConsole extends ForwardingPluginConsole implements Configurable {
  private static final UacaConsole shared = new UacaConsole(UacaPreferences.getShared());

  private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  private final PluginConsole delegate;

  private final UacaOptions options;

  private UacaConsole(final UacaOptions options) {
    this.delegate = Activator.defaultInstance().getConsole();
    this.options = requireNonNull(options);
  }

  public static UacaConsole create(final UacaOptions options) {
    return new UacaConsole(options);
  }

  public static UacaConsole getShared() {
    return shared;
  }

  @Override
  protected PluginConsole delegate() {
    return this.delegate;
  }

  private String hook(final String message) {
    if (UacaPreferences.getShared().isEventLogEnabled()) {
      this.notice(message);
    }

    return builder().format(format, new Date()).appendln().lines(message).toString();
  }

  @Override
  public PluginConsole append(@Nullable final CharSequence s) {
    return super.append(this.hook(String.valueOf(s)));
  }

  @Override
  public PluginConsole append(@Nullable final CharSequence s, final int from, final int to) {
    return super.append(this.hook(String.valueOf(s).substring(from, to)));
  }

  @Override
  public PluginConsole append(final char c) {
    return super.append(this.hook(String.valueOf(c)));
  }

  @Override
  public void put(final @Nullable String message) {
    super.put(this.hook(String.format(String.valueOf(message))));
  }

  @Override
  public void put(final String format, final Object ... args) {
    super.put(this.hook(String.format(format, args)));
  }

  @Override
  public void print(final @Nullable String message) {
    super.print(this.hook(String.format(String.valueOf(message))));
  }

  @Override
  public void print(final String format, final Object ... args) {
    super.print(this.hook(String.format(format, args)));
  }

  public Options getOptions() {
    return this.options;
  }
}
