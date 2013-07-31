package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import sk.stuba.fiit.perconik.core.listeners.CompletionListener;

public class CompletionAdapter extends Adapter implements CompletionListener
{
	public void assistSessionStarted(ContentAssistEvent event)
	{
	}

	public void assistSessionRestarted(ContentAssistEvent event)
	{
	}

	public void assistSessionEnded(ContentAssistEvent event)
	{
	}

	public void applied(ICompletionProposal proposal)
	{
	}

	public void selectionChanged(ICompletionProposal proposal, boolean smart)
	{
	}
}
