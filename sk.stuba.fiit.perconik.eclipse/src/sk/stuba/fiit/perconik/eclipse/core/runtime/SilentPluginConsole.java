package sk.stuba.fiit.perconik.eclipse.core.runtime;

enum SilentPluginConsole implements PluginConsole {
  INSTANCE;

  public PluginConsole append(final CharSequence s) {
    return this;
  }

  public PluginConsole append(final CharSequence s, final int from, final int to) {
    return this;
  }

  public PluginConsole append(final char c) {
    return this;
  }

  public void close() {}

  public void flush() {}

  public void put(final String message) {}

  public void put(final String format, final Object ... args) {}

  public void print(final String message) {}

  public void print(final String format, final Object ... args) {}

  public void notice(final String message) {}

  public void notice(final String format, final Object ... args) {}

  public void warning(final String message) {}

  public void warning(final String format, final Object ... args) {}

  public void error(final String message) {}

  public void error(final String format, final Object ... args) {}

  public void error(final Throwable failure, final String message) {}

  public void error(final Throwable failure, final String format, final Object ... args) {}
}
