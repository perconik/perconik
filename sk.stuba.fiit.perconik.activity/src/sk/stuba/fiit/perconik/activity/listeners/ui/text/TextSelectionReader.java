package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;

final class TextSelectionReader extends DisplayTask<TextSelectionCapture> {
  static final TextSelectionReader instance = new TextSelectionReader();

  private TextSelectionReader() {}

  @Override
  public TextSelectionCapture call() {
    IWorkbenchPart part = Parts.getActivePart();
    ITextViewer viewer = TextViewerSupport.getTextViewer(part);

    if (viewer == null) {
      return null;
    }

    return new TextSelectionCapture(part, viewer, viewer.getSelectedRange());
  }
}
