package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.RefactoringExecutionListener;

/**
 * An abstract adapter class for a {@code RefactoringExecutionListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code RefactoringExecutionListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see RefactoringExecutionListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class RefactoringExecutionAdapter extends Adapter implements RefactoringExecutionListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected RefactoringExecutionAdapter()
	{
	}

	public void executionNotification(RefactoringExecutionEvent event)
	{
	}
}
