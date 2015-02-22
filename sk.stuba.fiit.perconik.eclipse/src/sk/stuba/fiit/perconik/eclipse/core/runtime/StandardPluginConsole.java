package sk.stuba.fiit.perconik.eclipse.core.runtime;

import java.io.PrintStream;

import javax.annotation.Nullable;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

import static java.util.Objects.requireNonNull;

final class StandardPluginConsole implements PluginConsole {
  private final Plugin plugin;

  private final PrintStream out;

  StandardPluginConsole(final Plugin plugin, final PrintStream out) {
    this.plugin = requireNonNull(plugin);
    this.out = requireNonNull(out);
  }

  private void log(final Status status) {
    this.plugin.getLog().log(status);
  }

  public PluginConsole append(@Nullable final CharSequence s) {
    this.out.append(s);

    return this;
  }

  public PluginConsole append(@Nullable final CharSequence s, final int from, final int to) {
    this.out.append(s, from, to);

    return this;
  }

  public PluginConsole append(final char c) {
    this.out.append(c);

    return this;
  }

  public void close() {
    this.out.close();
  }

  public void flush() {
    this.out.flush();
  }

  public void put(@Nullable final String message) {
    this.out.print(String.format(String.valueOf(message)));
  }

  public void put(final String format, final Object ... args) {
    this.out.print(String.format(format, args));
  }

  public void print(@Nullable final String message) {
    this.out.println(String.format(String.valueOf(message)));
  }

  public void print(final String format, final Object ... args) {
    this.out.println(String.format(format, args));
  }

  public void notice(@Nullable final String message) {
    this.log(new Status(IStatus.INFO, this.getPluginId(), String.format(String.valueOf(message))));
  }

  public void notice(final String format, final Object ... args) {
    this.log(new Status(IStatus.INFO, this.getPluginId(), String.format(format, args)));
  }

  public void warning(@Nullable final String message) {
    this.log(new Status(IStatus.WARNING, this.getPluginId(), String.format(String.valueOf(message))));
  }

  public void warning(final String format, final Object ... args) {
    this.log(new Status(IStatus.WARNING, this.getPluginId(), String.format(format, args)));
  }

  public void error(@Nullable final String message) {
    this.log(new Status(IStatus.ERROR, this.getPluginId(), String.format(String.valueOf(message))));
  }

  public void error(final String format, final Object ... args) {
    this.log(new Status(IStatus.ERROR, this.getPluginId(), String.format(format, args)));
  }

  public void error(final Throwable failure, @Nullable final String message) {
    this.log(new Status(IStatus.ERROR, this.getPluginId(), String.format(String.valueOf(message)), failure));
  }

  public void error(final Throwable failure, final String format, final Object ... args) {
    this.log(new Status(IStatus.ERROR, this.getPluginId(), String.format(format, args), failure));
  }

  private String getPluginId() {
    return this.plugin.getBundle().getSymbolicName();
  }
}
