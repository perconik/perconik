package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jgit.events.RefsChangedEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.GitReferenceListener;

/**
 * An abstract adapter class for a {@code GitReferenceListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code GitReferenceListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see GitReferenceListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class GitReferenceAdapter extends Adapter implements GitReferenceListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected GitReferenceAdapter()
	{
	}

	public void onRefsChanged(RefsChangedEvent event)
	{
	}
}
