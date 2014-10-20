package sk.stuba.fiit.perconik.core.debug.runtime;

import javax.annotation.Nullable;

import org.eclipse.core.runtime.Plugin;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.environment.Environment;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

public final class DebugConsole implements PluginConsole {
  private static final int MAX_BUFFER_SIZE = 8192;

  private final boolean enabled;

  private final PluginConsole console;

  private final SmartStringBuilder builder;

  private DebugConsole(final PluginConsole console) {
    this.enabled = Environment.debug;
    this.console = checkNotNull(console);
    this.builder = new SmartStringBuilder();
  }

  private static enum Factory implements DebugConsoleFactory {
    INSTANCE;

    public DebugConsole create(final Plugin plugin) {
      return of(plugin);
    }
  }

  public static DebugConsoleFactory factory() {
    return Factory.INSTANCE;
  }

  public static DebugConsole of(final Plugin plugin) {
    return of(PluginConsoles.create(plugin));
  }

  public static DebugConsole of(final PluginConsole console) {
    if (console instanceof DebugConsole) {
      return (DebugConsole) console;
    }

    return new DebugConsole(console);
  }

  private boolean isEnabled() {
    return this.enabled;
  }

  public PluginConsole append(@Nullable final CharSequence s) {
    this.builder.append(s);

    return this;
  }

  public PluginConsole append(@Nullable final CharSequence s, final int from, final int to) {
    this.builder.append(s, from, to);

    return this;
  }

  public PluginConsole append(final char c) {
    this.builder.append(c);

    return this;
  }

  public void close() {}

  public void flush() {}

  public void tab() {
    this.builder.tab();
  }

  public void untab() {
    this.builder.untab();
  }

  private void trim() {
    if (this.builder.capacity() >= MAX_BUFFER_SIZE) {
      this.builder.trimToSize();
    }
  }

  private String indent(@Nullable final String message) {
    return this.builder.lines(message).toString();
  }

  private String indent(final String format, final Object ... args) {
    return this.indent(String.format(format, args));
  }

  @Override
  public void put(@Nullable final String message) {
    if (this.isEnabled()) {
      this.console.put(indent(message));
      this.putHook();
    }
  }

  @Override
  public void put(final String format, final Object ... args) {
    if (this.isEnabled()) {
      this.console.put(indent(format, args));
      this.putHook();
    }
  }

  private void putHook() {
    this.builder.setLength(0);
    this.trim();
  }

  @Override
  public void print(@Nullable final String message) {
    if (this.isEnabled()) {
      this.console.print(indent(message));
      this.printHook();
    }
  }

  @Override
  public void print(final String format, final Object ... args) {
    if (this.isEnabled()) {
      this.console.print(indent(format, args));
      this.printHook();
    }
  }

  private void printHook() {
    this.builder.truncate();
    this.trim();
  }

  @Override
  public void notice(final String message) {
    if (this.isEnabled()) {
      this.console.notice(message);
    }
  }

  @Override
  public void notice(final String format, final Object ... args) {
    if (this.isEnabled()) {
      this.console.notice(format, args);
    }
  }

  @Override
  public void warning(final String message) {
    if (this.isEnabled()) {
      this.console.warning(message);
    }
  }

  @Override
  public void warning(final String format, final Object ... args) {
    if (this.isEnabled()) {
      this.console.warning(format, args);
    }
  }

  public void error(final String message) {
    if (this.isEnabled()) {
      this.console.error(message);
    }
  }

  public void error(final String format, final Object ... args) {
    if (this.isEnabled()) {
      this.console.error(format, args);
    }
  }

  public void error(final Throwable failure, final String message) {
    if (this.isEnabled()) {
      this.console.error(failure, message);
    }
  }

  public void error(final Throwable failure, final String format, final Object ... args) {
    if (this.isEnabled()) {
      this.console.error(failure, format, args);
    }
  }
}
