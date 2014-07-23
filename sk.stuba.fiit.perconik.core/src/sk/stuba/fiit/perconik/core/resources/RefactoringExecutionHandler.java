package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ltk.core.refactoring.RefactoringCore;

import sk.stuba.fiit.perconik.core.listeners.RefactoringExecutionListener;

enum RefactoringExecutionHandler implements Handler<RefactoringExecutionListener>
{
	INSTANCE;
	
	public final void register(final RefactoringExecutionListener listener)
	{
		RefactoringCore.getHistoryService().addExecutionListener(listener);
	}

	public final void unregister(final RefactoringExecutionListener listener)
	{
		RefactoringCore.getHistoryService().removeExecutionListener(listener);
	}
}
