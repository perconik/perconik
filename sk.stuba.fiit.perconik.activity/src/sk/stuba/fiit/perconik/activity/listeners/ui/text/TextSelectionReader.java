package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.TextConsolePage;

import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;
import sk.stuba.fiit.perconik.eclipse.ui.console.ConsoleViews;
import sk.stuba.fiit.perconik.eclipse.ui.console.TextConsolePages;

final class TextSelectionReader extends DisplayTask<TextSelectionCapture> {
  static final TextSelectionReader instance = new TextSelectionReader();

  private TextSelectionReader() {}

  @Override
  public TextSelectionCapture call() {
    IWorkbenchPart part = Parts.getActivePart();
    ITextViewer viewer = Parts.getTextViewer(part);

    if (part instanceof IConsoleView && viewer == null) {
      IConsoleView view = (IConsoleView) part;
      TextConsolePage page = ConsoleViews.getCurrentTextConsolePage(view);

      viewer = TextConsolePages.getTextConsoleViewer(page);
    }

    if (viewer == null) {
      return null;
    }

    return new TextSelectionCapture(part, viewer, viewer.getSelectedRange());
  }
}
