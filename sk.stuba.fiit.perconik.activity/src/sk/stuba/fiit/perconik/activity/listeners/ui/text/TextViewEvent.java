package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.IEditorPart;

final class TextViewEvent {
  final long time;

  final IEditorPart editor;

  final ISourceViewer viewer;

  final int verticalOffset;

  TextViewEvent(final long time, final IEditorPart editor, final ISourceViewer viewer, final int verticalOffset) {
    assert time >= 0L && editor != null && viewer != null;

    this.time = time;
    this.editor = editor;
    this.viewer = viewer;
    this.verticalOffset = verticalOffset;
  }

  boolean isContinuousWith(final TextViewEvent other) {
    return this.editor == other.editor && this.viewer == other.viewer;
  }
}
