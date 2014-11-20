package sk.stuba.fiit.perconik.activity.serializers.ui;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

public final class Ui {
  private static final boolean restore = false;

  private Ui() {}

  /**
   * Returns the referenced part or {@code null} if the part was not instantiated.
   */
  public static IWorkbenchPart dereferencePart(final IWorkbenchPartReference reference) {
    return reference.getPart(restore);
  }

  /**
   * Returns the referenced view or {@code null} if the view was not instantiated.
   */
  public static IViewPart dereferenceView(final IViewReference reference) {
    return reference.getView(restore);
  }

  /**
   * Returns the referenced editor or {@code null} if the editor was not instantiated.
   */
  public static IEditorPart dereferenceEditor(final IEditorReference reference) {
    return reference.getEditor(restore);
  }
}
