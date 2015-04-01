package sk.stuba.fiit.perconik.eclipse.ui;

import com.google.common.base.Supplier;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.environment.plugin.Activator;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

/**
 * Static utility methods pertaining to Eclipse workbench.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Workbenches {
  private Workbenches() {}

  public static Supplier<IWorkbench> workbenchSupplier() {
    return new Supplier<IWorkbench>() {
      public IWorkbench get() {
        return getWorkbench();
      }
    };
  }

  /**
   * Gets the workbench.
   * @return the workbench or {@code null} if it has not been created yet
   */
  public static IWorkbench getWorkbench() {
    try {
      return PlatformUI.getWorkbench();
    } catch (IllegalStateException e) {
      PluginConsoles.create(Activator.defaultInstance()).error("Workbench has not been created yet", e);

      return null;
    }
  }

  /**
   * Waits for the workbench.
   * This method blocks until there is an available workbench.
   * @see #getWorkbench()
   */
  public static IWorkbench waitForWorkbench() {
    IWorkbench workbench;

    while ((workbench = getWorkbench()) == null) {
      sleepUninterruptibly(20, MILLISECONDS);
    }

    return workbench;
  }
}
