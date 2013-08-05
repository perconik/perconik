package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionListenerExtension;
import org.eclipse.jface.text.contentassist.ICompletionListenerExtension2;
import sk.stuba.fiit.perconik.core.Listener;

public interface CompletionListener extends Listener, ICompletionListener, ICompletionListenerExtension, ICompletionListenerExtension2
{
}
