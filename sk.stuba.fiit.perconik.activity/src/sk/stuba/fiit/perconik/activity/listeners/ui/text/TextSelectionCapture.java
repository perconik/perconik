package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPart;

final class TextSelectionCapture {
  final IWorkbenchPart part;

  final ITextViewer viewer;

  final Point range;

  TextSelectionCapture(final IWorkbenchPart part, final ITextViewer viewer, final Point range) {
    assert part != null && viewer != null && range != null;

    this.part = part;
    this.viewer = viewer;
    this.range = range;
  }
}
