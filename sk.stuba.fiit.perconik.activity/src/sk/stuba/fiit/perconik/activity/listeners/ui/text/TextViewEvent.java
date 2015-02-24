package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;

final class TextViewEvent {
  final long time;

  final ITextViewer viewer;

  final int verticalOffset;

  TextViewEvent(final long time, final ITextViewer viewer, final int verticalOffset) {
    assert time >= 0L && viewer != null;

    this.time = time;
    this.viewer = viewer;
    this.verticalOffset = verticalOffset;
  }

  boolean isContinuousWith(final TextViewEvent other) {
    return this.viewer.equals(other.viewer);
  }
}
