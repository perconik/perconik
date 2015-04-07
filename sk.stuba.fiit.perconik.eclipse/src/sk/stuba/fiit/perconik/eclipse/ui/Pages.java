package sk.stuba.fiit.perconik.eclipse.ui;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

import static sk.stuba.fiit.perconik.eclipse.ui.Windows.getActiveWindow;
import static sk.stuba.fiit.perconik.eclipse.ui.Windows.waitForActiveWindow;

/**
 * Static utility methods pertaining to Eclipse pages.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Pages {
  private Pages() {}

  public static Supplier<IWorkbenchPage> activePageSupplier() {
    return new Supplier<IWorkbenchPage>() {
      public IWorkbenchPage get() {
        return getActivePage();
      }
    };
  }

  public static Supplier<IWorkbenchPage> activePageSupplier(@Nullable final IWorkbenchWindow window) {
    return new Supplier<IWorkbenchPage>() {
      public IWorkbenchPage get() {
        return getActivePage(window);
      }
    };
  }

  public static Supplier<IWorkbenchPage> activePageSupplier(@Nullable final IWorkbench workbench) {
    return new Supplier<IWorkbenchPage>() {
      public IWorkbenchPage get() {
        return getActivePage(workbench);
      }
    };
  }

  /**
   * Gets the currently active page.
   * @return the active page or {@code null} if there is no active window
   */
  public static IWorkbenchPage getActivePage() {
    return getActivePage(getActiveWindow());
  }

  /**
   * Gets the currently active page.
   * @param window the window, may be {@code null}
   * @return the active page or {@code null} if the window
   *         is {@code null} or there is no active page
   */
  public static IWorkbenchPage getActivePage(@Nullable final IWorkbenchWindow window) {
    if (window == null) {
      return null;
    }

    return window.getActivePage();
  }

  /**
   * Gets the currently active page.
   * @param workbench the workbench, may be {@code null}
   * @return the active page or {@code null} if the workbench
   *         is {@code null} or there is no active page
   */
  public static IWorkbenchPage getActivePage(@Nullable final IWorkbench workbench) {
    return getActivePage(getActiveWindow(workbench));
  }

  /**
   * Waits for the currently active page.
   * This method blocks until there is an active page.
   * @see #getActivePage()
   */
  public static IWorkbenchPage waitForActivePage() {
    return waitForActivePage(waitForActiveWindow());
  }

  /**
   * Waits for the currently active page.
   * This method blocks until there is an active page.
   * @param window the window, can not be {@code null}
   * @see #getActivePage(IWorkbenchWindow)
   */
  public static IWorkbenchPage waitForActivePage(final IWorkbenchWindow window) {
    checkNotNull(window);

    IWorkbenchPage page;

    while ((page = getActivePage(window)) == null) {
      sleepUninterruptibly(20, MILLISECONDS);
    }

    return page;
  }

  /**
   * Waits for the currently active page.
   * This method blocks until there is an active page.
   * @param workbench the workbench, can not be {@code null}
   * @see #getActivePage(IWorkbench)
   */
  public static IWorkbenchPage waitForActivePage(final IWorkbench workbench) {
    return waitForActivePage(waitForActiveWindow(workbench));
  }
}
