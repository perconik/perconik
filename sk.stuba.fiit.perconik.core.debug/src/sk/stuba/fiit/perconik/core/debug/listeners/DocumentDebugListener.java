package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jface.text.DocumentEvent;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.DocumentListener;

public final class DocumentDebugListener extends AbstractDebugListener implements DocumentListener {
  public DocumentDebugListener() {}

  public DocumentDebugListener(final DebugConsole console) {
    super(console);
  }

  public void documentAboutToBeChanged(final DocumentEvent event) {
    this.printHeader("Document about to be changed");
    this.printDocumentEvent(event);
  }

  public void documentChanged(final DocumentEvent event) {
    this.printHeader("Document changed");
    this.printDocumentEvent(event);
  }

  private void printDocumentEvent(final DocumentEvent event) {
    this.put(Debug.dumpDocumentEvent(event));
  }
}
