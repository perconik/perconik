package sk.stuba.fiit.perconik.core.debug.plugin;

import java.util.concurrent.TimeoutException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;

import org.osgi.framework.BundleContext;

import sk.stuba.fiit.perconik.core.debug.DebugListeners;
import sk.stuba.fiit.perconik.core.debug.DebugResources;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;
import sk.stuba.fiit.perconik.environment.Environment;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.util.concurrent.TimeUnit.SECONDS;

import static sk.stuba.fiit.perconik.core.plugin.Activator.awaitServices;

/**
 * The <code>Activator</code> class controls the plug-in life cycle.
 *
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Activator extends ExtendedPlugin {
  /**
   * The plug-in identifier.
   */
  public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.core.debug";

  /**
   * The shared instance.
   */
  private static volatile Activator plugin;

  /**
   * The constructor.
   */
  public Activator() {}

  /**
   * Gets the shared instance.
   * @return the shared instance or {@code null}
   */
  public static Activator defaultInstance() {
    return plugin;
  }

  /**
   * Plug-in early startup.
   *
   * <p><b>Warning:</b> Users should not explicitly instantiate this class.
   *
   * @author Pavol Zbell
   * @since 1.0
   */
  public static final class Startup implements IStartup {
    static final TimeValue timeout = TimeValue.of(12, SECONDS);

    /**
     * The constructor.
     */
    public Startup() {}

    /**
     * Waits until core processes all extensions and
     * then prints registration maps on the debug console.
     */
    public void earlyStartup() {
      final Runnable wait = new Runnable() {
        public final void run() {
          try {
            awaitServices(timeout);

            DebugResources.printRegistrations();
            DebugListeners.printRegistrations();
          } catch (TimeoutException failure) {
            defaultInstance().getConsole().error(failure, "Unable to print registrations, await services timed out");
          }
        }
      };

      Display.getDefault().asyncExec(wait);
    }
  }

  /**
   * Starts this plug-in.
   *
   * <p><b>Warning:</b> Users must never explicitly call this method.
   */
  @Override
  public void start(final BundleContext context) throws Exception {
    if (Environment.debug) {
      this.console.put("Starting %s ... ", PLUGIN_ID);
    }

    super.start(context);

    plugin = this;

    if (Environment.debug) {
      this.console.print("done");
    }
  }

  /**
   * Stops this plug-in.
   *
   * <p><b>Warning:</b> Users must never explicitly call this method.
   */
  @Override
  public void stop(final BundleContext context) throws Exception {
    if (Environment.debug) {
      this.console.put("Stopping %s ... ", PLUGIN_ID);
    }

    plugin = null;

    super.stop(context);

    if (Environment.debug) {
      this.console.print("done");
    }
  }
}
