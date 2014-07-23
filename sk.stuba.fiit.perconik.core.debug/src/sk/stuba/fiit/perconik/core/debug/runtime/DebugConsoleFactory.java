package sk.stuba.fiit.perconik.core.debug.runtime;

import org.eclipse.core.runtime.Plugin;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoleFactory;

public interface DebugConsoleFactory extends PluginConsoleFactory {
  @Override
  public DebugConsole create(Plugin plugin);
}
