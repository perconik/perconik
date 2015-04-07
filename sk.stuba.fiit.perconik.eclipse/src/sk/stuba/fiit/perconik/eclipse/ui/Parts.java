package sk.stuba.fiit.perconik.eclipse.ui;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

import static sk.stuba.fiit.perconik.eclipse.ui.Pages.getActivePage;
import static sk.stuba.fiit.perconik.eclipse.ui.Pages.waitForActivePage;
import static sk.stuba.fiit.perconik.eclipse.ui.Windows.getActiveWindow;

/**
 * Static utility methods pertaining to Eclipse parts.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Parts {
  private Parts() {}

  public static IWorkbenchPart forTextViewer(final ITextViewer viewer) {
    return forTextViewer(viewer, false);
  }

  public static IWorkbenchPart forTextViewer(final ITextViewer viewer, final boolean restore) {
    for (IWorkbenchWindow window: Workbenches.getWorkbench().getWorkbenchWindows()) {
      for (IWorkbenchPage page: window.getPages()) {
        IWorkbenchPart editor = Editors.forTextViewer(page, viewer, restore);

        if (editor != null) {
          return editor;
        }

        IWorkbenchPart view = Views.forTextViewer(page, viewer, restore);

        if (view != null) {
          return view;
        }
      }
    }

    return null;
  }

  public static IWorkbenchPart forDocument(final IDocument document) {
    return forDocument(document, false);
  }

  public static IWorkbenchPart forDocument(final IDocument document, final boolean restore) {
    for (IWorkbenchWindow window: Workbenches.getWorkbench().getWorkbenchWindows()) {
      for (IWorkbenchPage page: window.getPages()) {
        IWorkbenchPart editor = Editors.forDocument(page, document, restore);

        if (editor != null) {
          return editor;
        }

        IWorkbenchPart view = Views.forDocument(page, document, restore);

        if (view != null) {
          return view;
        }
      }
    }

    return null;
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

  public static Supplier<IWorkbenchPart> activePartSupplier(@Nullable final IWorkbenchWindow window) {
    return new Supplier<IWorkbenchPart>() {
      public IWorkbenchPart get() {
        return getActivePart(window);
      }
    };
  }

  public static Supplier<IWorkbenchPart> activePartSupplier(@Nullable final IWorkbench workbench) {
    return new Supplier<IWorkbenchPart>() {
      public IWorkbenchPart get() {
        return getActivePart(workbench);
      }
    };
  }

  public static Supplier<IWorkbenchPartReference> activePartReferenceSupplier() {
    return new Supplier<IWorkbenchPartReference>() {
      public IWorkbenchPartReference get() {
        return getActivePartReference();
      }
    };
  }

  public static Supplier<IWorkbenchPartReference> activePartReferenceSupplier(@Nullable final IWorkbenchPage page) {
    return new Supplier<IWorkbenchPartReference>() {
      public IWorkbenchPartReference get() {
        return getActivePartReference(page);
      }
    };
  }

  public static Supplier<IWorkbenchPartReference> activePartReferenceSupplier(@Nullable final IWorkbenchWindow window) {
    return new Supplier<IWorkbenchPartReference>() {
      public IWorkbenchPartReference get() {
        return getActivePartReference(window);
      }
    };
  }

  public static Supplier<IWorkbenchPartReference> activePartReferenceSupplier(@Nullable final IWorkbench workbench) {
    return new Supplier<IWorkbenchPartReference>() {
      public IWorkbenchPartReference get() {
        return getActivePartReference(workbench);
      }
    };
  }

  /**
   * Gets the text viewer from given part.
   * @param part the part, may be {@code null}
   * @return the text viewer or {@code null} if the part
   *         is {@code null} or there is no text viewer
   */
  public static ITextViewer getTextViewer(@Nullable final IWorkbenchPart part) {
    if (part == null) {
      return null;
    }

    Object viewer = part.getAdapter(ITextOperationTarget.class);

    return viewer instanceof ITextViewer ? (ITextViewer) viewer : null;
  }

  /**
   * Gets the source viewer from given part.
   * @param part the part, may be {@code null}
   * @return the source viewer or {@code null} if the part
   *         is {@code null} or there is no source viewer
   */
  public static ISourceViewer getSourceViewer(@Nullable final IWorkbenchPart part) {
    ITextViewer viewer = getTextViewer(part);

    return viewer instanceof ISourceViewer ? (ISourceViewer) viewer : null;
  }

  /**
   * Gets the text widget from given part.
   * @param part the part, may be {@code null}
   * @return the text widget or {@code null} if the part
   *         is {@code null} or there is no text widget
   */
  public static StyledText getStyledText(@Nullable final IWorkbenchPart part) {
    return getStyledText(getTextViewer(part));
  }

  public static StyledText getStyledText(@Nullable final ITextViewer viewer) {
    return viewer != null ? viewer.getTextWidget() : null;
  }

  /**
   * Gets the input document from given part.
   * @param part the part, may be {@code null}
   * @return the document or {@code null} if the part
   *         is {@code null} or there is no document
   */
  public static IDocument getDocument(@Nullable final IWorkbenchPart part) {
    return getDocument(getTextViewer(part));
  }

  public static IDocument getDocument(@Nullable final ITextViewer viewer) {
    return viewer != null ? viewer.getDocument() : null;
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
   * Gets the currently active part reference.
   * @return the active part reference or {@code null} if there is no active page
   */
  public static IWorkbenchPartReference getActivePartReference() {
    return getActivePartReference(getActivePage());
  }

  /**
   * Gets the currently active part reference.
   * @param page the page, may be {@code null}
   * @return the active part reference or {@code null} if the page
   *         is {@code null} or there is no active part reference
   */
  public static IWorkbenchPartReference getActivePartReference(@Nullable final IWorkbenchPage page) {
    if (page == null) {
      return null;
    }

    return page.getActivePartReference();
  }

  /**
   * Gets the currently active part reference.
   * @param window the window, may be {@code null}
   * @return the active part reference or {@code null} if the window
   *         is {@code null} or there is no active part reference
   */
  public static IWorkbenchPartReference getActivePartReference(@Nullable final IWorkbenchWindow window) {
    return getActivePartReference(getActivePage(window));
  }

  /**
   * Gets the currently active part reference.
   * @param workbench the workbench, may be {@code null}
   * @return the active part reference or {@code null} if the workbench
   *         is {@code null} or there is no active part reference
   */
  public static IWorkbenchPartReference getActivePartReference(@Nullable final IWorkbench workbench) {
    return getActivePartReference(getActiveWindow(workbench));
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
    checkNotNull(page);

    IWorkbenchPart part;

    while ((part = getActivePart(page)) == null) {
      sleepUninterruptibly(20, MILLISECONDS);
    }

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

  /**
   * Waits for the currently active part reference.
   * This method blocks until there is an active part reference.
   * @see #getActivePart()
   */
  public static IWorkbenchPartReference waitForActivePartReference() {
    return waitForActivePartReference(waitForActivePage());
  }

  /**
   * Waits for the currently active part reference.
   * This method blocks until there is an active part reference.
   * @param page the page, can not be {@code null}
   * @see #getActivePartReference(IWorkbenchPage)
   */
  public static IWorkbenchPartReference waitForActivePartReference(final IWorkbenchPage page) {
    checkNotNull(page);

    IWorkbenchPartReference part;

    while ((part = getActivePartReference(page)) == null) {
      sleepUninterruptibly(20, MILLISECONDS);
    }

    return part;
  }

  /**
   * Waits for the currently active part reference.
   * This method blocks until there is an active part reference.
   * @param window the window, can not be {@code null}
   * @see #getActivePartReference(IWorkbenchWindow)
   */
  public static IWorkbenchPartReference waitForActivePartReference(final IWorkbenchWindow window) {
    return waitForActivePartReference(waitForActivePage(window));
  }

  /**
   * Waits for the currently active part reference.
   * This method blocks until there is an active part reference.
   * @param workbench the workbench, can not be {@code null}
   * @see #getActivePartReference(IWorkbench)
   */
  public static IWorkbenchPartReference waitForActivePartReference(final IWorkbench workbench) {
    return waitForActivePartReference(waitForActivePage(workbench));
  }

}
