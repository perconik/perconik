package sk.stuba.fiit.perconik.eclipse.core.runtime;

import javax.annotation.Nullable;

public interface PluginConsole
{
	public void put(@Nullable String message);

	public void put(String format, Object ... args);

	public void print(@Nullable String message);

	public void print(String format, Object ... args);

	public void notice(String message);

	public void notice(String format, Object ... args);

	public void warning(String message);

	public void warning(String format, Object ... args);

	public void error(String message, Throwable failure);
}