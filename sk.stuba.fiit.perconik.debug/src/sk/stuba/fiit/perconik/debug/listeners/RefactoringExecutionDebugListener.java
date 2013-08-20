package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ltk.core.refactoring.history.RefactoringExecutionEvent;
import sk.stuba.fiit.perconik.core.listeners.RefactoringExecutionListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class RefactoringExecutionDebugListener extends AbstractDebugListener implements RefactoringExecutionListener
{
	public RefactoringExecutionDebugListener()
	{
	}
	
	public RefactoringExecutionDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void executionNotification(final RefactoringExecutionEvent event)
	{
		this.printHeader("Refactoring execution");
		this.printRefactoringExecutionEventg(event);
	}
	
	private final void printRefactoringExecutionEventg(final RefactoringExecutionEvent event)
	{
		this.put(Debug.dumpRefactoringExecutionEvent(event));
	}
}
