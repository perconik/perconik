package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class TextSelectionReader extends DisplayTask<TextSelectionCapture> {
  static final TextSelectionReader instance = new TextSelectionReader();

  private TextSelectionReader() {}

  @Override
  public TextSelectionCapture call() {
    IEditorPart editor = Editors.getActiveEditor();

    if (editor == null) {
      return null;
    }

    ISourceViewer viewer = Editors.getSourceViewer(editor);

    return new TextSelectionCapture(editor, viewer, viewer.getSelectedRange());
  }
}
