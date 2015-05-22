package sk.stuba.fiit.perconik.eclipse.core.runtime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nullable;

import static java.lang.System.currentTimeMillis;

import static sk.stuba.fiit.perconik.utilities.SmartStringBuilder.builder;

public abstract class TimeHookConsole extends ForwardingPluginConsole {
  private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  protected TimeHookConsole() {}

  @SuppressWarnings("static-method")
  protected String hook(final long time, final String message) {
    return builder().format(format, new Date(time)).append(" ").append(message).toString();
  }

  @Override
  public PluginConsole append(@Nullable final CharSequence s) {
    return super.append(this.hook(currentTimeMillis(), String.valueOf(s)));
  }

  @Override
  public PluginConsole append(@Nullable final CharSequence s, final int from, final int to) {
    return super.append(this.hook(currentTimeMillis(), String.valueOf(s).substring(from, to)));
  }

  @Override
  public PluginConsole append(final char c) {
    return super.append(this.hook(currentTimeMillis(), String.valueOf(c)));
  }

  @Override
  public void put(final @Nullable String message) {
    super.put(this.hook(currentTimeMillis(), String.format(String.valueOf(message))));
  }

  @Override
  public void put(final String format, final Object ... args) {
    super.put(this.hook(currentTimeMillis(), String.format(format, args)));
  }

  @Override
  public void print(final @Nullable String message) {
    super.print(this.hook(currentTimeMillis(), String.format(String.valueOf(message))));
  }

  @Override
  public void print(final String format, final Object ... args) {
    super.print(this.hook(currentTimeMillis(), String.format(format, args)));
  }
}
