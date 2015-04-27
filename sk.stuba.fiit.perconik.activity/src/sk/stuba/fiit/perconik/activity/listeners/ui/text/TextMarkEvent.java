package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.ui.IWorkbenchPart;

final class TextMarkEvent extends AbstractTextEvent {
  final IWorkbenchPart part;

  final IMarkSelection selection;

  TextMarkEvent(final long time, final IWorkbenchPart part, final IMarkSelection selection) {
    super(time);

    assert part != null && selection != null;

    this.part = part;
    this.selection = selection;
  }

  boolean contentEquals(final TextMarkEvent other) {
    return this.part == other.part && this.selection.equals(other.selection);
  }

  boolean isContinuousWith(final TextMarkEvent other) {
    if (this.part != other.part) {
      return false;
    }

    int a = this.selection.getOffset();
    int b = other.selection.getOffset();

    return a == b || (a + this.selection.getLength()) == (b + other.selection.getLength());
  }
}
