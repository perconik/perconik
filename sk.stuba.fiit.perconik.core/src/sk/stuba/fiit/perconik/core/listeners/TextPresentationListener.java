package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.text.ITextPresentationListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextPresentation;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A text presentation listener.
 *
 * @see Listener
 * @see ITextPresentationListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface TextPresentationListener extends Listener {
  public void applyTextPresentation(ITextViewer viewer, TextPresentation textPresentation);
}
