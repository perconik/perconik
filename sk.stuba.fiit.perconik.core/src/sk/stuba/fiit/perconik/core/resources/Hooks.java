package sk.stuba.fiit.perconik.core.resources;

import javax.annotation.Nullable;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferenceEditor;
import static sk.stuba.fiit.perconik.core.resources.Ui.dereferencePart;
import static sk.stuba.fiit.perconik.core.resources.Ui.dereferenceView;
import static sk.stuba.fiit.perconik.eclipse.ui.Pages.waitForActivePage;
import static sk.stuba.fiit.perconik.eclipse.ui.Windows.waitForActiveWindow;
import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForWorkbench;

final class Hooks {
  private Hooks() {}

  static <T> void addAll(final Hook<T, ?> hook, final Iterable<T> objects) {
    for (T object: objects) {
      hook.add(object);
    }
  }

  static <T> void addNonNull(final Hook<T, ?> hook, @Nullable final T object) {
    if (object != null) {
      hook.add(object);
    }
  }

  static <T> void removeAll(final Hook<T, ?> hook, final Iterable<T> objects) {
    for (T object: objects) {
      hook.remove(object);
    }
  }

  static <T> void removeNonNull(final Hook<T, ?> hook, @Nullable final T object) {
    if (object != null) {
      hook.remove(object);
    }
  }

  static void addWindowsAsynchronouslyTo(final Hook<IWorkbenchWindow, ?> hook) {
    final Runnable initializer = new Runnable() {
      @Override
      public void run() {
        addAll(hook, asList(waitForWorkbench().getWorkbenchWindows()));
      }
    };

    Display.getDefault().asyncExec(initializer);
  }

  static void addPagesAsynchronouslyTo(final Hook<IWorkbenchPage, ?> hook) {
    final Runnable initializer = new Runnable() {
      @Override
      public void run() {
        addAll(hook, asList(waitForActiveWindow().getPages()));
      }
    };

    Display.getDefault().asyncExec(initializer);
  }

  static void addPartsAsynchronouslyTo(final Hook<IWorkbenchPart, ?> hook) {
    final Runnable initializer = new Runnable() {
      @Override
      public void run() {
        for (IViewReference reference: waitForActivePage().getViewReferences()) {
          addNonNull(hook, dereferencePart(reference));
        }
      }
    };

    Display.getDefault().asyncExec(initializer);
  }

  static void addEditorsAsynchronouslyTo(final Hook<IEditorPart, ?> hook) {
    final Runnable initializer = new Runnable() {
      @Override
      public void run() {
        for (IEditorReference reference: waitForActivePage().getEditorReferences()) {
          addNonNull(hook, dereferenceEditor(reference));
        }
      }
    };

    Display.getDefault().asyncExec(initializer);
  }

  static void addViewsAsynchronouslyTo(final Hook<IViewPart, ?> hook) {
    final Runnable initializer = new Runnable() {
      @Override
      public void run() {
        for (IViewReference reference: waitForActivePage().getViewReferences()) {
          addNonNull(hook, dereferenceView(reference));
        }
      }
    };

    Display.getDefault().asyncExec(initializer);
  }

  static void addTextViewersAsynchronouslyTo(final Hook<ITextViewer, ?> hook) {
    final Runnable initializer = new Runnable() {
      @Override
      public void run() {
        for (IEditorReference reference: waitForActivePage().getEditorReferences()) {
          addNonNull(hook, Editors.getTextViewer(dereferenceEditor(reference)));
        }
      }
    };

    Display.getDefault().asyncExec(initializer);
  }

  static void addSourceViewersAsynchronouslyTo(final Hook<ISourceViewer, ?> hook) {
    final Runnable initializer = new Runnable() {
      @Override
      public void run() {
        for (IEditorReference reference: waitForActivePage().getEditorReferences()) {
          addNonNull(hook, Editors.getSourceViewer(dereferenceEditor(reference)));
        }
      }
    };

    Display.getDefault().asyncExec(initializer);
  }

  static void addDocumentsAsynchronouslyTo(final Hook<IDocument, ?> hook) {
    final Runnable initializer = new Runnable() {
      @Override
      public void run() {
        for (IEditorReference reference: waitForActivePage().getEditorReferences()) {
          addNonNull(hook, Editors.getDocument(dereferenceEditor(reference)));
        }
      }
    };

    Display.getDefault().asyncExec(initializer);
  }
}
