package sk.stuba.fiit.perconik.activity.listeners.ui.text;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.TextConsolePage;

import sk.stuba.fiit.perconik.eclipse.ui.Parts;
import sk.stuba.fiit.perconik.eclipse.ui.console.ConsoleViews;
import sk.stuba.fiit.perconik.eclipse.ui.console.TextConsolePages;

final class TextViewerSupport {
  private TextViewerSupport() {}

  static ITextViewer getTextViewer(final IWorkbenchPart part) {
    ITextViewer viewer = Parts.getTextViewer(part);

    if (part instanceof IConsoleView && viewer == null) {
      IConsoleView view = (IConsoleView) part;
      TextConsolePage page = ConsoleViews.getCurrentTextConsolePage(view);

      viewer = TextConsolePages.getTextConsoleViewer(page);
    }

    return viewer;
  }
}
