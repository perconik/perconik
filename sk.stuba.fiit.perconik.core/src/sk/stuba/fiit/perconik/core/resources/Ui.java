package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

final class Ui {
  private Ui() {}

  static IWorkbenchPart dereferencePart(final IWorkbenchPartReference reference) {
    return reference.getPart(false);
  }

  static IEditorPart dereferenceEditor(final IEditorReference reference) {
    return reference.getEditor(false);
  }

  static IViewPart dereferenceView(final IViewReference reference) {
    return reference.getView(false);
  }
}
