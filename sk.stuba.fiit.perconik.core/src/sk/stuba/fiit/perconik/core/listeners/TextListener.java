package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextEvent;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A text listener.
 *
 * @see Listener
 * @see ITextListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface TextListener extends Listener {
  public void textChanged(ITextViewer viewer, TextEvent event);
}
