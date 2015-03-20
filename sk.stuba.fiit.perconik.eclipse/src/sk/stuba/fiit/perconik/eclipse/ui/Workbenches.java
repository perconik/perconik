package sk.stuba.fiit.perconik.eclipse.ui;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsoles;
import sk.stuba.fiit.perconik.environment.plugin.Activator;

import static java.util.Objects.requireNonNull;

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

  public static Supplier<IWorkbenchPart> activePartSupplier() {
    return new Supplier<IWorkbenchPart>() {
      public IWorkbenchPart get() {
        return getActivePart();
      }
    };
  }

  public static Supplier<IWorkbenchPart> activePartSupplier(@Nullable final IWorkbenchPage page) {
    return new Supplier<IWorkbenchPart>() {
      public IWorkbenchPart get() {
        return getActivePart(page);
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
      PluginConsoles.create(Activator.defaultInstance()).error("Workbench has not been created yet.", e);

      return null;
    }
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
   *         is {@code null} or if called from a non-UI thread
   */
  public static IWorkbenchWindow getActiveWindow(@Nullable final IWorkbench workbench) {
    if (workbench == null) {
      return null;
    }

    return workbench.getActiveWorkbenchWindow();
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
   * Gets the currently active part.
   * @return the active part or {@code null} if there is no active page
   */
  public static IWorkbenchPart getActivePart() {
    return getActivePart(getActivePage());
  }

  /**
   * Gets the currently active part.
   * @param page the page, may be {@code null}
   * @return the active part or {@code null} if the page
   *         is {@code null} or there is no active part
   */
  public static IWorkbenchPart getActivePart(@Nullable final IWorkbenchPage page) {
    if (page == null) {
      return null;
    }

    return page.getActivePart();
  }

  /**
   * Gets the currently active part.
   * @param window the window, may be {@code null}
   * @return the active part or {@code null} if the window
   *         is {@code null} or there is no active part
   */
  public static IWorkbenchPart getActivePart(@Nullable final IWorkbenchWindow window) {
    return getActivePart(getActivePage(window));
  }

  /**
   * Gets the currently active part.
   * @param workbench the workbench, may be {@code null}
   * @return the active part or {@code null} if the workbench
   *         is {@code null} or there is no active part
   */
  public static IWorkbenchPart getActivePart(@Nullable final IWorkbench workbench) {
    return getActivePart(getActiveWindow(workbench));
  }

  /**
   * Waits for the workbench.
   * This method blocks until there is an available workbench.
   * @see #getWorkbench()
   */
  public static IWorkbench waitForWorkbench() {
    IWorkbench workbench;

    while ((workbench = getWorkbench()) == null) {}

    return workbench;
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
    requireNonNull(window);

    IWorkbenchPage page;

    while ((page = getActivePage(window)) == null) {}

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

  /**
   * Waits for the currently active part.
   * This method blocks until there is an active part.
   * @see #getActivePart()
   */
  public static IWorkbenchPart waitForActivePart() {
    return waitForActivePart(waitForActivePage());
  }

  /**
   * Waits for the currently active part.
   * This method blocks until there is an active part.
   * @param page the page, can not be {@code null}
   * @see #getActivePart(IWorkbenchPage)
   */
  public static IWorkbenchPart waitForActivePart(final IWorkbenchPage page) {
    requireNonNull(page);

    IWorkbenchPart part;

    while ((part = getActivePart(page)) == null) {}

    return part;
  }

  /**
   * Waits for the currently active part.
   * This method blocks until there is an active part.
   * @param window the window, can not be {@code null}
   * @see #getActivePart(IWorkbenchWindow)
   */
  public static IWorkbenchPart waitForActivePart(final IWorkbenchWindow window) {
    return waitForActivePart(waitForActivePage(window));
  }

  /**
   * Waits for the currently active part.
   * This method blocks until there is an active part.
   * @param workbench the workbench, can not be {@code null}
   * @see #getActivePart(IWorkbench)
   */
  public static IWorkbenchPart waitForActivePart(final IWorkbench workbench) {
    return waitForActivePart(waitForActivePage(workbench));
  }
}
