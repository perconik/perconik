package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.TextPresentationListener;

public final class TextPresentationDebugListener extends AbstractDebugListener implements TextPresentationListener {
  public TextPresentationDebugListener() {}

  public TextPresentationDebugListener(final DebugConsole console) {
    super(console);
  }

  public void applyTextPresentation(final ITextViewer viewer, final TextPresentation presentation) {
    this.printHeader("Text presentation about to be applied");
    this.printTextPresentation(presentation);
  }

  private void printTextPresentation(final TextPresentation presentation) {
    this.put(Debug.dumpTextPresentation(presentation));
  }
}
