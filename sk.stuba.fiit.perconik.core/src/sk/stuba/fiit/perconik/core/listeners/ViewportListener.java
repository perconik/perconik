package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.IViewportListener;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A viewport listener.
 *
 * @see Listener
 * @see IViewportListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ViewportListener extends Listener {
  public void viewportChanged(ITextViewer viewer, int verticalOffset);
}
