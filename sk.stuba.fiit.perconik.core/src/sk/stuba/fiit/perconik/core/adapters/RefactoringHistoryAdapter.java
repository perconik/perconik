package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ltk.core.refactoring.history.RefactoringHistoryEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.RefactoringHistoryListener;

/**
 * An abstract adapter class for a {@code RefactoringHistoryListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code RefactoringHistoryListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see RefactoringHistoryListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class RefactoringHistoryAdapter extends Adapter implements RefactoringHistoryListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected RefactoringHistoryAdapter()
	{
	}

	public void historyNotification(RefactoringHistoryEvent event)
	{
	}
}
