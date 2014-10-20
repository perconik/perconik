package sk.stuba.fiit.perconik.eclipse.core.runtime;

import java.io.Closeable;
import java.io.Flushable;

import javax.annotation.Nullable;

/**
 * Plug-in console capable of printing various kinds of messages.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface PluginConsole extends Appendable, Closeable, Flushable {
  @Override
  public PluginConsole append(@Nullable CharSequence s);

  @Override
  public PluginConsole append(@Nullable CharSequence s, int from, int to);

  @Override
  public PluginConsole append(char c);

  @Override
  public void close();

  @Override
  public void flush();

  public void put(@Nullable String message);

  public void put(String format, Object ... args);

  public void print(@Nullable String message);

  public void print(String format, Object ... args);

  public void notice(String message);

  public void notice(String format, Object ... args);

  public void warning(String message);

  public void warning(String format, Object ... args);

  public void error(String message);

  public void error(String format, Object ... args);

  public void error(Throwable failure, String message);

  public void error(Throwable failure, String format, Object ... args);
}
