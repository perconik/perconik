package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorPart;

final class TextSelectionCapture {
  final IEditorPart editor;

  final ITextViewer viewer;

  final Point range;

  TextSelectionCapture(final IEditorPart editor, final ITextViewer viewer, final Point range) {
    assert editor != null && viewer != null && range != null;

    this.editor = editor;
    this.viewer = viewer;
    this.range = range;
  }
}
