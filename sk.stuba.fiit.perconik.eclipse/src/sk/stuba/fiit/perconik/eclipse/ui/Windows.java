package sk.stuba.fiit.perconik.eclipse.ui;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.getWorkbench;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForWorkbench;

/**
 * Static utility methods pertaining to Eclipse windows.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Windows {
  private Windows() {}

  public static Supplier<IWorkbenchWindow> activeWindowSupplier() {
    return new Supplier<IWorkbenchWindow>() {
      public IWorkbenchWindow get() {
        return getActiveWindow();
      }
    };
  }

  public static Supplier<IWorkbenchWindow> activeWindowSupplier(@Nullable final IWorkbench workbench) {
    return new Supplier<IWorkbenchWindow>() {
      public IWorkbenchWindow get() {
        return getActiveWindow(workbench);
      }
    };
  }

  /**
   * Gets the currently active window.
   * @return the active window or {@code null} if
   *         the workbench has not been created yet
   */
  public static IWorkbenchWindow getActiveWindow() {
    return getActiveWindow(getWorkbench());
  }

  /**
   * Gets the currently active window.
   * @param workbench the workbench, may be {@code null}
   * @return the active window or {@code null} if the workbench
   *         is {@code null} or there is no active window
   */
  public static IWorkbenchWindow getActiveWindow(@Nullable final IWorkbench workbench) {
    if (workbench == null) {
      return null;
    }

    return workbench.getActiveWorkbenchWindow();
  }

  /**
   * Waits for the currently active window.
   * This method blocks until there is an active window.
   * @see #getActiveWindow()
   */
  public static IWorkbenchWindow waitForActiveWindow() {
    return waitForActiveWindow(waitForWorkbench());
  }

  /**
   * Waits for the currently active window.
   * This method blocks until there is an active window.
   * @param workbench the workbench, can not be {@code null}
   * @see #getActiveWindow(IWorkbench)
   */
  public static IWorkbenchWindow waitForActiveWindow(final IWorkbench workbench) {
    requireNonNull(workbench);

    IWorkbenchWindow window;

    while ((window = getActiveWindow(workbench)) == null) {}

    return window;
  }
}
