package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.ltk.core.refactoring.RefactoringCore;

enum RefactoringHistoryHandler implements Handler<RefactoringHistoryListener>
{
	INSTANCE;
	
	public final void add(final RefactoringHistoryListener listener)
	{
		RefactoringCore.getHistoryService().addHistoryListener(listener);
	}

	public final void remove(final RefactoringHistoryListener listener)
	{
		RefactoringCore.getHistoryService().removeHistoryListener(listener);
	}
}
