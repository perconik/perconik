package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextViewer;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A text input listener.
 *
 * @see Listener
 * @see ITextInputListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface TextInputListener extends Listener {
  public void inputDocumentAboutToBeChanged(ITextViewer viewer, IDocument before, IDocument after);

  public void inputDocumentChanged(ITextViewer viewer, IDocument before, IDocument after);
}
