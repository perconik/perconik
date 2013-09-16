package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jgit.events.RefsChangedEvent;
import sk.stuba.fiit.perconik.core.listeners.GitReferenceListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public final class GitReferenceDebugListener extends AbstractDebugListener implements GitReferenceListener
{
	public GitReferenceDebugListener()
	{
	}
	
	public GitReferenceDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void onRefsChanged(final RefsChangedEvent event)
	{
		this.printHeader("Git reference changed");
		this.printGitReferenceEvent(event);
	}

	private final void printGitReferenceEvent(final RefsChangedEvent event)
	{
		this.put(Debug.dumpGitReferenceEvent(event));
	}
}
