package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jgit.events.IndexChangedEvent;
import sk.stuba.fiit.perconik.core.listeners.GitIndexListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public final class GitIndexDebugListener extends AbstractDebugListener implements GitIndexListener
{
	public GitIndexDebugListener()
	{
	}
	
	public GitIndexDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void onIndexChanged(final IndexChangedEvent event)
	{
		this.printHeader("Git index changed");
		this.printGitIndexEvent(event);
	}

	private final void printGitIndexEvent(final IndexChangedEvent event)
	{
		this.put(Debug.dumpGitIndexEvent(event));
	}
}
