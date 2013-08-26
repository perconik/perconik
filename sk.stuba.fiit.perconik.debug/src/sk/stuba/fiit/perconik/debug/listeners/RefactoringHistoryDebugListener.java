package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ltk.core.refactoring.history.RefactoringHistoryEvent;
import sk.stuba.fiit.perconik.core.listeners.RefactoringHistoryListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public final class RefactoringHistoryDebugListener extends AbstractDebugListener implements RefactoringHistoryListener
{
	public RefactoringHistoryDebugListener()
	{
	}
	
	public RefactoringHistoryDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void historyNotification(final RefactoringHistoryEvent event)
	{
		this.printHeader("Refectoring history");
		this.printRefactoringHistoryEvent(event);
	}
	
	private final void printRefactoringHistoryEvent(final RefactoringHistoryEvent event)
	{
		this.put(Debug.dumpRefactoringHistoryEvent(event));
	}
}
