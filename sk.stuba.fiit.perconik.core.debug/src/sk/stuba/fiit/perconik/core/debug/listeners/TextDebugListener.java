package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextEvent;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.TextListener;

public final class TextDebugListener extends AbstractDebugListener implements TextListener {
  public TextDebugListener() {}

  public TextDebugListener(final DebugConsole console) {
    super(console);
  }

  public void textChanged(final ITextViewer viewer, final TextEvent event) {
    this.printHeader("Text changed");
    this.printTextEvent(event);
  }

  private void printTextEvent(final TextEvent event) {
    this.put(Debug.dumpTextEvent(event));
  }
}
