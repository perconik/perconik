package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.Plugin;

/**
 * Static utility methods pertaining to {@code PluginConsole} instances.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class PluginConsoles {
  private PluginConsoles() {
    throw new AssertionError();
  }

  /**
   * Creates new standard plug-in console.
   * @param plugin the plug-in
   * @return new plug-in console
   */
  public static final PluginConsole create(final Plugin plugin) {
    return new StandardPluginConsole(plugin, System.out);
  }
}
