package sk.stuba.fiit.perconik.eclipse.ui;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.getActivePage;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.getActivePart;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.getActivePartReference;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.getActiveWindow;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForActivePage;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForActiveWindow;

/**
 * Static utility methods pertaining to Eclipse views.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Views {
  private Views() {}

  public static Supplier<IViewPart> activeViewSupplier() {
    return new Supplier<IViewPart>() {
      public IViewPart get() {
        return getActiveView();
      }
    };
  }

  public static Supplier<IViewPart> activeViewSupplier(@Nullable final IWorkbenchPage page) {
    return new Supplier<IViewPart>() {
      public IViewPart get() {
        return getActiveView(page);
      }
    };
  }

  public static Supplier<IViewPart> activeViewSupplier(@Nullable final IWorkbenchWindow window) {
    return new Supplier<IViewPart>() {
      public IViewPart get() {
        return getActiveView(window);
      }
    };
  }

  public static Supplier<IViewPart> activeViewSupplier(@Nullable final IWorkbench workbench) {
    return new Supplier<IViewPart>() {
      public IViewPart get() {
        return getActiveView(workbench);
      }
    };
  }

  public static Supplier<IViewReference> activeViewReferenceSupplier() {
    return new Supplier<IViewReference>() {
      public IViewReference get() {
        return getActiveViewReference();
      }
    };
  }

  public static Supplier<IViewReference> activeViewReferenceSupplier(@Nullable final IWorkbenchPage page) {
    return new Supplier<IViewReference>() {
      public IViewReference get() {
        return getActiveViewReference(page);
      }
    };
  }

  public static Supplier<IViewReference> activeViewReferenceSupplier(@Nullable final IWorkbenchWindow window) {
    return new Supplier<IViewReference>() {
      public IViewReference get() {
        return getActiveViewReference(window);
      }
    };
  }

  public static Supplier<IViewReference> activeViewReferenceSupplier(@Nullable final IWorkbench workbench) {
    return new Supplier<IViewReference>() {
      public IViewReference get() {
        return getActiveViewReference(workbench);
      }
    };
  }

  /**
   * Gets the active view.
   * @return the active view or {@code null} if there is no active view
   */
  public static IViewPart getActiveView() {
    return getActiveView(getActivePage());
  }

  /**
   * Gets the currently active view.
   * @param page the page, may be {@code null}
   * @return the active view or {@code null} if the page
   *         is {@code null} or there is no active view
   */
  public static IViewPart getActiveView(@Nullable final IWorkbenchPage page) {
    IWorkbenchPart part = getActivePart(page);

    return part instanceof IViewPart ? (IViewPart) part : null;
  }

  /**
   * Gets the currently active view.
   * @param window the window, may be {@code null}
   * @return the active view or {@code null} if the window
   *         is {@code null} or there is no active view
   */
  public static IViewPart getActiveView(@Nullable final IWorkbenchWindow window) {
    return getActiveView(getActivePage(window));
  }

  /**
   * Gets the currently active view.
   * @param workbench the workbench, may be {@code null}
   * @return the active view or {@code null} if the workbench
   *         is {@code null} or there is no active view
   */
  public static IViewPart getActiveView(@Nullable final IWorkbench workbench) {
    return getActiveView(getActiveWindow(workbench));
  }

  /**
   * Gets the active view reference.
   * @return the active view reference or {@code null} if there is no active view reference
   */
  public static IViewReference getActiveViewReference() {
    return getActiveViewReference(getActivePage());
  }

  /**
   * Gets the currently active view reference.
   * @param page the page, may be {@code null}
   * @return the active view reference or {@code null} if the page
   *         is {@code null} or there is no active view reference
   */
  public static IViewReference getActiveViewReference(@Nullable final IWorkbenchPage page) {
    IWorkbenchPartReference reference = getActivePartReference(page);

    return reference instanceof IViewReference ? (IViewReference) reference : null;
  }

  /**
   * Gets the currently active view reference.
   * @param window the window, may be {@code null}
   * @return the active view reference or {@code null} if the window
   *         is {@code null} or there is no active view reference
   */
  public static IViewReference getActiveViewReference(@Nullable final IWorkbenchWindow window) {
    return getActiveViewReference(getActivePage(window));
  }

  /**
   * Gets the currently active view reference.
   * @param workbench the workbench, may be {@code null}
   * @return the active view reference or {@code null} if the workbench
   *         is {@code null} or there is no active view reference
   */
  public static IViewReference getActiveViewReference(@Nullable final IWorkbench workbench) {
    return getActiveViewReference(getActiveWindow(workbench));
  }

  /**
   * Waits for the currently active view.
   * This method blocks until there is an active view.
   * @see #getActiveView()
   */
  public static IViewPart waitForActiveView() {
    return waitForActiveView(waitForActivePage());
  }

  /**
   * Waits for the currently active view.
   * This method blocks until there is an active view.
   * @param page the page, can not be {@code null}
   * @see #getActiveView(IWorkbenchPage)
   */
  public static IViewPart waitForActiveView(final IWorkbenchPage page) {
    requireNonNull(page);

    IViewPart view;

    while ((view = getActiveView(page)) == null) {}

    return view;
  }

  /**
   * Waits for the currently active view.
   * This method blocks until there is an active view.
   * @param window the window, can not be {@code null}
   * @see #getActiveView(IWorkbenchWindow)
   */
  public static IViewPart waitForActiveView(final IWorkbenchWindow window) {
    return waitForActiveView(waitForActivePage(window));
  }

  /**
   * Waits for the currently active view.
   * This method blocks until there is an active view.
   * @param workbench the workbench, can not be {@code null}
   * @see #getActiveView(IWorkbench)
   */
  public static IViewPart waitForActiveView(final IWorkbench workbench) {
    return waitForActiveView(waitForActiveWindow(workbench));
  }

  /**
   * Waits for the currently active view reference.
   * This method blocks until there is an active view reference.
   * @see #getActiveView()
   */
  public static IViewReference waitForActiveViewReference() {
    return waitForActiveViewReference(waitForActivePage());
  }

  /**
   * Waits for the currently active view reference.
   * This method blocks until there is an active view reference.
   * @param page the page, can not be {@code null}
   * @see #getActiveView(IWorkbenchPage)
   */
  public static IViewReference waitForActiveViewReference(final IWorkbenchPage page) {
    requireNonNull(page);

    IViewReference view;

    while ((view = getActiveViewReference(page)) == null) {}

    return view;
  }

  /**
   * Waits for the currently active view reference.
   * This method blocks until there is an active view reference.
   * @param window the window, can not be {@code null}
   * @see #getActiveView(IWorkbenchWindow)
   */
  public static IViewReference waitForActiveViewReference(final IWorkbenchWindow window) {
    return waitForActiveViewReference(waitForActivePage(window));
  }

  /**
   * Waits for the currently active view reference.
   * This method blocks until there is an active view reference.
   * @param workbench the workbench, can not be {@code null}
   * @see #getActiveView(IWorkbench)
   */
  public static IViewReference waitForActiveViewReference(final IWorkbench workbench) {
    return waitForActiveViewReference(waitForActiveWindow(workbench));
  }
}
