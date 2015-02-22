package sk.stuba.fiit.perconik.eclipse.core.runtime;

import javax.annotation.Nullable;

enum SilentPluginConsole implements PluginConsole {
  INSTANCE;

  public PluginConsole append(@Nullable final CharSequence s) {
    return this;
  }

  public PluginConsole append(@Nullable final CharSequence s, final int from, final int to) {
    return this;
  }

  public PluginConsole append(final char c) {
    return this;
  }

  public void close() {}

  public void flush() {}

  public void put(@Nullable final String message) {}

  public void put(final String format, final Object ... args) {}

  public void print(@Nullable final String message) {}

  public void print(final String format, final Object ... args) {}

  public void notice(@Nullable final String message) {}

  public void notice(final String format, final Object ... args) {}

  public void warning(@Nullable final String message) {}

  public void warning(final String format, final Object ... args) {}

  public void error(@Nullable final String message) {}

  public void error(final String format, final Object ... args) {}

  public void error(final Throwable failure, @Nullable final String message) {}

  public void error(final Throwable failure, final String format, final Object ... args) {}
}
