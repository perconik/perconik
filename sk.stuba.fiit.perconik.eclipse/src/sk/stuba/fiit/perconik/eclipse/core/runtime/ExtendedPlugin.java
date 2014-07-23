package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.Plugin;

/**
 * An abstract plug-in with extended basic capabilities.
 * 
 * @see Plugin
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ExtendedPlugin extends Plugin {
  /**
   * The plug-in console. 
   */
  protected final PluginConsole console;

  /**
   * Constructs an extended plug-in with default plug-in console.
   */
  protected ExtendedPlugin() {
    this.console = PluginConsoles.create(this);
  }

  /**
   * Constructs an extended plug-in with a plug-in
   * console obtained from the given factory.
   * @param factory plug-in console factory
   */
  protected ExtendedPlugin(final PluginConsoleFactory factory) {
    this.console = factory.create(this);
  }

  /**
   * Gets plug-in console.
   */
  public final PluginConsole getConsole() {
    return this.console;
  }
}
