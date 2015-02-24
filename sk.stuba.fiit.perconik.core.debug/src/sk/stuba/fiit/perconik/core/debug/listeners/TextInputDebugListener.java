package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.TextInputListener;

public final class TextInputDebugListener extends AbstractDebugListener implements TextInputListener {
  public TextInputDebugListener() {}

  public TextInputDebugListener(final DebugConsole console) {
    super(console);
  }

  public void inputDocumentAboutToBeChanged(final ITextViewer viewer, final IDocument before, final IDocument after) {
    this.printHeader("Input document about to be changed");
    this.printDocumentChange(before, after);
  }

  public void inputDocumentChanged(final ITextViewer viewer, final IDocument before, final IDocument after) {
    this.printHeader("Input document changed");
    this.printDocumentChange(before, after);
  }

  private void printDocumentChange(final IDocument before, final IDocument after) {
    this.put(Debug.dumpDocumentChange(before, after));
  }
}
