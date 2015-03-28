package sk.stuba.fiit.perconik.eclipse.ui;

import javax.annotation.Nullable;

import com.google.common.base.Supplier;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.eclipse.ui.Pages.getActivePage;
import static sk.stuba.fiit.perconik.eclipse.ui.Pages.waitForActivePage;
import static sk.stuba.fiit.perconik.eclipse.ui.Parts.getActivePartReference;
import static sk.stuba.fiit.perconik.eclipse.ui.Windows.getActiveWindow;
import static sk.stuba.fiit.perconik.eclipse.ui.Windows.waitForActiveWindow;

/**
 * Static utility methods pertaining to Eclipse editors.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Editors {
  private Editors() {}

  public static IEditorPart forTextViewer(final ITextViewer viewer) {
    return forTextViewer(viewer, false);
  }

  public static IEditorPart forTextViewer(final ITextViewer viewer, final boolean restore) {
    for (IWorkbenchWindow window: Workbenches.getWorkbench().getWorkbenchWindows()) {
      for (IWorkbenchPage page: window.getPages()) {
        IEditorPart editor = page.getActiveEditor();

        if (viewer.equals(getTextViewer(editor))) {
          return editor;
        }

        for (IEditorReference reference: page.getEditorReferences()) {
          editor = reference.getEditor(restore);

          if (viewer.equals(getTextViewer(editor))) {
            return editor;
          }
        }
      }
    }

    return null;
  }

  public static IEditorPart forDocument(final IDocument document) {
    return forDocument(document, false);
  }

  public static IEditorPart forDocument(final IDocument document, final boolean restore) {
    for (IWorkbenchWindow window: Workbenches.getWorkbench().getWorkbenchWindows()) {
      for (IWorkbenchPage page: window.getPages()) {
        IEditorPart editor = page.getActiveEditor();

        if (document.equals(getDocument(editor))) {
          return editor;
        }

        for (IEditorReference reference: page.getEditorReferences()) {
          editor = reference.getEditor(restore);

          if (document.equals(getDocument(editor))) {
            return editor;
          }
        }
      }
    }

    return null;
  }

  public static Supplier<IEditorPart> activeEditorSupplier() {
    return new Supplier<IEditorPart>() {
      public IEditorPart get() {
        return getActiveEditor();
      }
    };
  }

  public static Supplier<IEditorPart> activeEditorSupplier(@Nullable final IWorkbenchPage page) {
    return new Supplier<IEditorPart>() {
      public IEditorPart get() {
        return getActiveEditor(page);
      }
    };
  }

  public static Supplier<IEditorPart> activeEditorSupplier(@Nullable final IWorkbenchWindow window) {
    return new Supplier<IEditorPart>() {
      public IEditorPart get() {
        return getActiveEditor(window);
      }
    };
  }

  public static Supplier<IEditorPart> activeEditorSupplier(@Nullable final IWorkbench workbench) {
    return new Supplier<IEditorPart>() {
      public IEditorPart get() {
        return getActiveEditor(workbench);
      }
    };
  }

  public static Supplier<IEditorReference> activeEditorReferenceSupplier() {
    return new Supplier<IEditorReference>() {
      public IEditorReference get() {
        return getActiveEditorReference();
      }
    };
  }

  public static Supplier<IEditorReference> activeEditorReferenceSupplier(@Nullable final IWorkbenchPage page) {
    return new Supplier<IEditorReference>() {
      public IEditorReference get() {
        return getActiveEditorReference(page);
      }
    };
  }

  public static Supplier<IEditorReference> activeEditorReferenceSupplier(@Nullable final IWorkbenchWindow window) {
    return new Supplier<IEditorReference>() {
      public IEditorReference get() {
        return getActiveEditorReference(window);
      }
    };
  }

  public static Supplier<IEditorReference> activeEditorReferenceSupplier(@Nullable final IWorkbench workbench) {
    return new Supplier<IEditorReference>() {
      public IEditorReference get() {
        return getActiveEditorReference(workbench);
      }
    };
  }

  /**
   * Gets the active editor.
   * @return the active editor or {@code null} if there is no active editor
   */
  public static IEditorPart getActiveEditor() {
    return getActiveEditor(getActivePage());
  }

  /**
   * Gets the currently active editor.
   * @param page the page, may be {@code null}
   * @return the active editor or {@code null} if the page
   *         is {@code null} or there is no active editor
   */
  public static IEditorPart getActiveEditor(@Nullable final IWorkbenchPage page) {
    return page != null ? page.getActiveEditor() : null;
  }

  /**
   * Gets the currently active editor.
   * @param window the window, may be {@code null}
   * @return the active editor or {@code null} if the window
   *         is {@code null} or there is no active editor
   */
  public static IEditorPart getActiveEditor(@Nullable final IWorkbenchWindow window) {
    return getActiveEditor(getActivePage(window));
  }

  /**
   * Gets the currently active editor.
   * @param workbench the workbench, may be {@code null}
   * @return the active editor or {@code null} if the workbench
   *         is {@code null} or there is no active editor
   */
  public static IEditorPart getActiveEditor(@Nullable final IWorkbench workbench) {
    return getActiveEditor(getActiveWindow(workbench));
  }

  /**
   * Gets the active editor reference.
   * @return the active editor reference or {@code null} if there is no active editor reference
   */
  public static IEditorReference getActiveEditorReference() {
    return getActiveEditorReference(getActivePage());
  }

  /**
   * Gets the currently active editor reference.
   * @param page the page, may be {@code null}
   * @return the active editor reference or {@code null} if the page
   *         is {@code null} or there is no active editor reference
   */
  public static IEditorReference getActiveEditorReference(@Nullable final IWorkbenchPage page) {
    IWorkbenchPartReference reference = getActivePartReference(page);

    return reference instanceof IEditorReference ? (IEditorReference) reference : null;
  }

  /**
   * Gets the currently active editor reference.
   * @param window the window, may be {@code null}
   * @return the active editor reference or {@code null} if the window
   *         is {@code null} or there is no active editor reference
   */
  public static IEditorReference getActiveEditorReference(@Nullable final IWorkbenchWindow window) {
    return getActiveEditorReference(getActivePage(window));
  }

  /**
   * Gets the currently active editor reference.
   * @param workbench the workbench, may be {@code null}
   * @return the active editor reference or {@code null} if the workbench
   *         is {@code null} or there is no active editor reference
   */
  public static IEditorReference getActiveEditorReference(@Nullable final IWorkbench workbench) {
    return getActiveEditorReference(getActiveWindow(workbench));
  }

  public static IResource getResource(@Nullable final IEditorPart editor) {
    return editor != null ? (IResource) editor.getEditorInput().getAdapter(IResource.class) : null;
  }

  public static IFile getFile(@Nullable final IEditorPart editor) {
    if (editor == null) {
      return null;
    }

    IEditorInput input = editor.getEditorInput();

    if (input instanceof IFileEditorInput) {
      return ((IFileEditorInput) input).getFile();
    }

    IResource resource = getResource(editor);

    return resource instanceof IFile ? (IFile) resource : null;
  }

  /**
   * Gets the text viewer from given editor.
   * @param editor the editor, may be {@code null}
   * @return the text viewer or {@code null} if the editor
   *         is {@code null} or there is no text viewer
   */
  public static ITextViewer getTextViewer(@Nullable final IEditorPart editor) {
    return Parts.getTextViewer(editor);
  }

  /**
   * Gets the source viewer from given editor.
   * @param editor the editor, may be {@code null}
   * @return the source viewer or {@code null} if the editor
   *         is {@code null} or there is no source viewer
   */
  public static ISourceViewer getSourceViewer(@Nullable final IEditorPart editor) {
    return Parts.getSourceViewer(editor);
  }

  /**
   * Gets the text widget from given editor.
   * @param editor the editor, may be {@code null}
   * @return the text widget or {@code null} if the editor
   *         is {@code null} or there is no text widget
   */
  public static StyledText getStyledText(@Nullable final IEditorPart editor) {
    return Parts.getStyledText(editor);
  }

  /**
   * Gets the input document from given editor.
   * @param editor the editor, may be {@code null}
   * @return the document or {@code null} if the editor
   *         is {@code null} or there is no document
   */
  public static IDocument getDocument(@Nullable final IEditorPart editor) {
    return Parts.getDocument(editor);
  }

  /**
   * Waits for the currently active editor.
   * This method blocks until there is an active editor.
   * @see #getActiveEditor()
   */
  public static IEditorPart waitForActiveEditor() {
    return waitForActiveEditor(waitForActivePage());
  }

  /**
   * Waits for the currently active editor.
   * This method blocks until there is an active editor.
   * @param page the page, can not be {@code null}
   * @see #getActiveEditor(IWorkbenchPage)
   */
  public static IEditorPart waitForActiveEditor(final IWorkbenchPage page) {
    requireNonNull(page);

    IEditorPart editor;

    while ((editor = getActiveEditor(page)) == null) {}

    return editor;
  }

  /**
   * Waits for the currently active editor.
   * This method blocks until there is an active editor.
   * @param window the window, can not be {@code null}
   * @see #getActiveEditor(IWorkbenchWindow)
   */
  public static IEditorPart waitForActiveEditor(final IWorkbenchWindow window) {
    return waitForActiveEditor(waitForActivePage(window));
  }

  /**
   * Waits for the currently active editor.
   * This method blocks until there is an active editor.
   * @param workbench the workbench, can not be {@code null}
   * @see #getActiveEditor(IWorkbench)
   */
  public static IEditorPart waitForActiveEditor(final IWorkbench workbench) {
    return waitForActiveEditor(waitForActiveWindow(workbench));
  }

  /**
   * Waits for the currently active editor reference.
   * This method blocks until there is an active editor reference.
   * @see #getActiveEditor()
   */
  public static IEditorReference waitForActiveEditorReference() {
    return waitForActiveEditorReference(waitForActivePage());
  }

  /**
   * Waits for the currently active editor reference.
   * This method blocks until there is an active editor reference.
   * @param page the page, can not be {@code null}
   * @see #getActiveEditor(IWorkbenchPage)
   */
  public static IEditorReference waitForActiveEditorReference(final IWorkbenchPage page) {
    requireNonNull(page);

    IEditorReference editor;

    while ((editor = getActiveEditorReference(page)) == null) {}

    return editor;
  }

  /**
   * Waits for the currently active editor reference.
   * This method blocks until there is an active editor reference.
   * @param window the window, can not be {@code null}
   * @see #getActiveEditor(IWorkbenchWindow)
   */
  public static IEditorReference waitForActiveEditorReference(final IWorkbenchWindow window) {
    return waitForActiveEditorReference(waitForActivePage(window));
  }

  /**
   * Waits for the currently active editor reference.
   * This method blocks until there is an active editor reference.
   * @param workbench the workbench, can not be {@code null}
   * @see #getActiveEditor(IWorkbench)
   */
  public static IEditorReference waitForActiveEditorReference(final IWorkbench workbench) {
    return waitForActiveEditorReference(waitForActiveWindow(workbench));
  }
}
