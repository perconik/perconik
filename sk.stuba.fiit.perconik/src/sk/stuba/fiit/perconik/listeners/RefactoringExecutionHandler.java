package sk.stuba.fiit.perconik.listeners;

import org.eclipse.ltk.core.refactoring.RefactoringCore;

enum RefactoringExecutionHandler implements Handler<RefactoringExecutionListener>
{
	INSTANCE;
	
	public final void add(final RefactoringExecutionListener listener)
	{
		RefactoringCore.getHistoryService().addExecutionListener(listener);
	}

	public final void remove(final RefactoringExecutionListener listener)
	{
		RefactoringCore.getHistoryService().removeExecutionListener(listener);
	}
}
