package sk.stuba.fiit.perconik.activity.listeners.ui.element;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

final class ElementSelectionEvent {
  final long time;

  final IWorkbenchPart part;

  final IStructuredSelection selection;

  ElementSelectionEvent(final long time, final IWorkbenchPart part, final IStructuredSelection selection) {
    assert time >= 0L;

    this.time = time;

    assert part != null && selection != null;

    this.part = part;
    this.selection = selection;
  }

  boolean contentEquals(final ElementSelectionEvent other) {
    return this.part == other.part && this.selection.equals(other.selection);
  }

  boolean isContinuousWith(final ElementSelectionEvent other) {
    if (this.part != other.part) {
      return false;
    }

    if (this.selection.isEmpty() || other.selection.isEmpty()) {
      return false;
    }

    Object[] a = this.selection.toArray();
    Object[] b = other.selection.toArray();

    return a[0].equals(b[0]) || a[a.length - 1].equals(b[b.length - 1]);
  }
}
