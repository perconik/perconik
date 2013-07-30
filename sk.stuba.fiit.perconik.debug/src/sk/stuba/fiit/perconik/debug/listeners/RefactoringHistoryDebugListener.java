package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.ltk.core.refactoring.history.RefactoringHistoryEvent;
import sk.stuba.fiit.perconik.core.listeners.RefactoringHistoryListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class RefactoringHistoryDebugListener extends AbstractDebugListener implements RefactoringHistoryListener
{
	public RefactoringHistoryDebugListener()
	{
	}
	
	public RefactoringHistoryDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void historyNotification(final RefactoringHistoryEvent event)
	{
		// TODO
	}
}
