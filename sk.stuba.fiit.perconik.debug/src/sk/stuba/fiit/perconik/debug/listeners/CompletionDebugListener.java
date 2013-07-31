package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import sk.stuba.fiit.perconik.core.listeners.CompletionListener;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public final class CompletionDebugListener extends AbstractDebugListener implements CompletionListener
{
	public CompletionDebugListener()
	{
	}
	
	public CompletionDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void assistSessionStarted(final ContentAssistEvent event)
	{
		// TODO
	}

	public final void assistSessionRestarted(final ContentAssistEvent event)
	{
		// TODO
	}

	public final void assistSessionEnded(final ContentAssistEvent event)
	{
		// TODO
	}

	public final void applied(final ICompletionProposal proposal)
	{
		// TODO
	}

	public final void selectionChanged(final ICompletionProposal proposal, final boolean smart)
	{
		// TODO
	}
}
