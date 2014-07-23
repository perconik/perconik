package sk.stuba.fiit.perconik.eclipse.ui;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

public enum WorkbenchReferencePolicy {
  DEFAULT(false),

  RESTORE(true);

  private final boolean restore;

  private WorkbenchReferencePolicy(final boolean restore) {
    this.restore = restore;
  }

  public final IEditorPart getEditor(final IEditorReference reference) {
    return reference.getEditor(this.restore);
  }

  public final IWorkbenchPart getPart(final IWorkbenchPartReference reference) {
    return reference.getPart(this.restore);
  }

  public final IViewPart getView(final IViewReference reference) {
    return reference.getView(this.restore);
  }
}
