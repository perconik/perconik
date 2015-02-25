package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;

final class TextViewEvent {
  final long time;

  final ITextViewer viewer;

  final int verticalOffset;

  final boolean last;

  TextViewEvent(final long time, final ITextViewer viewer, final int verticalOffset) {
    this(time, viewer, verticalOffset, false);
  }

  TextViewEvent(final long time, final ITextViewer viewer, final int verticalOffset, final boolean last) {
    assert time >= 0L && viewer != null;

    this.time = time;
    this.viewer = viewer;
    this.verticalOffset = verticalOffset;
    this.last = last;
  }

  boolean isContinuousWith(final TextViewEvent other) {
    return !this.last && this.viewer.equals(other.viewer);
  }
}
