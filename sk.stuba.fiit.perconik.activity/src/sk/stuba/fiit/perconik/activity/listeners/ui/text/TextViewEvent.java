package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;

import sk.stuba.fiit.perconik.eclipse.jface.text.LineRegion;

import static com.google.common.collect.Range.closed;

final class TextViewEvent extends AbstractTextEvent {
  final ITextViewer viewer;

  final LineRegion region;

  final int verticalOffset;

  TextViewEvent(final long time, final ITextViewer viewer, final LineRegion region, final int verticalOffset) {
    super(time);

    assert viewer != null && region != null;

    this.viewer = viewer;
    this.region = region;
    this.verticalOffset = verticalOffset;
  }

  boolean isContinuousWith(final TextViewEvent other) {
    if (!this.viewer.equals(other.viewer)) {
      return false;
    }

    LineRegion a = this.region;
    LineRegion b = other.region;

    return closed(a.start.line, a.end.line).isConnected(closed(b.start.line, b.end.line));
  }
}
