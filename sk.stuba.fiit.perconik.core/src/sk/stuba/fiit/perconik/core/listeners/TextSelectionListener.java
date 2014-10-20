package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A text selection listener.
 *
 * @see Listener
 * @see SelectionListener
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface TextSelectionListener extends Listener {
  public void selectionChanged(IWorkbenchPart part, ITextSelection selection);
}
