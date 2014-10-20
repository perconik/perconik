package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionListenerExtension;
import org.eclipse.jface.text.contentassist.ICompletionListenerExtension2;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A completion listener.
 *
 * @see Listener
 * @see ICompletionListener
 * @see ICompletionListenerExtension
 * @see ICompletionListenerExtension2
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface CompletionListener extends Listener, ICompletionListener, ICompletionListenerExtension, ICompletionListenerExtension2 {
}
