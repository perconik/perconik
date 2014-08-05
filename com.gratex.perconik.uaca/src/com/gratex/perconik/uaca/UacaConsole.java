package com.gratex.perconik.uaca;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nullable;

import com.gratex.perconik.uaca.plugin.Activator;
import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.eclipse.core.runtime.ForwardingPluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

import static sk.stuba.fiit.perconik.utilities.SmartStringBuilder.builder;

public final class UacaConsole extends ForwardingPluginConsole {
  private static final UacaConsole instance = new UacaConsole();

  private final PluginConsole delegate;

  private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  private UacaConsole() {
    this.delegate = Activator.getDefault().getConsole();
  }

  public static final UacaConsole getInstance() {
    return instance;
  }

  @Override
  protected final PluginConsole delegate() {
    return this.delegate;
  }

  private final String hook(final String message) {
    if (UacaPreferences.getShared().isEventLoggerEnabled()) {
      this.notice(message);
    }

    return builder().format(format, new Date()).appendln().lines(message).toString();
  }

  @Override
  public final PluginConsole append(@Nullable final CharSequence s) {
    return super.append(this.hook(String.valueOf(s)));
  }

  @Override
  public final PluginConsole append(@Nullable final CharSequence s, final int from, final int to) {
    return super.append(this.hook(String.valueOf(s).substring(from, to)));
  }

  @Override
  public final PluginConsole append(final char c) {
    return super.append(this.hook(String.valueOf(c)));
  }

  @Override
  public final void put(final String message) {
    super.put(this.hook(message));
  }

  @Override
  public final void put(final String format, final Object ... args) {
    super.put(this.hook(String.format(format, args)));
  }

  @Override
  public final void print(final String message) {
    super.print(this.hook(message));
  }

  @Override
  public final void print(final String format, final Object ... args) {
    super.print(this.hook(String.format(format, args)));
  }
}
