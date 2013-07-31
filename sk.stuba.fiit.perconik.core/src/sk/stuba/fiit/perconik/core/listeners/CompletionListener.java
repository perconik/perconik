package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionListenerExtension;
import org.eclipse.jface.text.contentassist.ICompletionListenerExtension2;

public interface CompletionListener extends Listener, ICompletionListener, ICompletionListenerExtension, ICompletionListenerExtension2
{
}
