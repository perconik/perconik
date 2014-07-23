package sk.stuba.fiit.perconik.eclipse.ui.plugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoleFactory;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;

/**
 * An abstract UI plug-in with extended basic capabilities.
 * 
 * @see AbstractUIPlugin
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class UserInterfacePlugin extends AbstractUIPlugin {
  /**
   * The plug-in console. 
   */
  protected final PluginConsole console;

  /**
   * Constructs an UI plug-in with default plug-in console.
   */
  protected UserInterfacePlugin() {
    this.console = PluginConsoles.create(this);
  }

  /**
   * Constructs an UI plug-in with a plug-in
   * console obtained from the given factory.
   * @param factory plug-in console factory
   */
  protected UserInterfacePlugin(final PluginConsoleFactory factory) {
    this.console = factory.create(this);
  }

  /**
   * Gets plug-in console.
   */
  public final PluginConsole getConsole() {
    return this.console;
  }
}
