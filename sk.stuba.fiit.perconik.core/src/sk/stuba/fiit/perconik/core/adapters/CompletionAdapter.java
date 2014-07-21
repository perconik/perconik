package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.CompletionListener;

/**
 * An abstract adapter class for a {@code CompletionListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code CompletionListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see CompletionListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class CompletionAdapter extends Adapter implements CompletionListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected CompletionAdapter()
	{
	}

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
