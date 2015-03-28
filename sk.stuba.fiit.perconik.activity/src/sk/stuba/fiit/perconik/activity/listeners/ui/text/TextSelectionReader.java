package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class TextSelectionReader extends DisplayTask<TextSelectionCapture> {
  static final TextSelectionReader instance = new TextSelectionReader();

  private TextSelectionReader() {}

  @Override
  public TextSelectionCapture call() {
    IEditorPart editor = Editors.getActiveEditor();
    ITextViewer viewer = Editors.getTextViewer(editor);

    if (editor == null || viewer == null) {
      return null;
    }

    return new TextSelectionCapture(editor, viewer, viewer.getSelectedRange());
  }
}
