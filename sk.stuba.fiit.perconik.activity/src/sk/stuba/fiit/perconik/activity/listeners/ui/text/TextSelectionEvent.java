package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IWorkbenchPart;

import static com.google.common.base.Strings.isNullOrEmpty;

final class TextSelectionEvent {
  final long time;

  final IWorkbenchPart part;

  final ITextSelection selection;

  TextSelectionEvent(final long time, final IWorkbenchPart part, final ITextSelection selection) {
    assert time >= 0L && part != null && selection != null;

    this.time = time;
    this.part = part;
    this.selection = selection;
  }

  boolean contentEquals(final TextSelectionEvent other) {
    return this.part == other.part && this.selection.equals(other.selection);
  }

  boolean isContinuousWith(final TextSelectionEvent other) {
    if (this.part != other.part) {
      return false;
    }

    int a = this.selection.getOffset();
    int b = other.selection.getOffset();

    return a == b || (a + this.selection.getLength()) == (b + other.selection.getLength());
  }

  boolean isSelectionEmpty() {
    return this.selection.isEmpty();
  }

  boolean isSelectionTextEmpty() {
    return isNullOrEmpty(this.selection.getText());
  }
}
