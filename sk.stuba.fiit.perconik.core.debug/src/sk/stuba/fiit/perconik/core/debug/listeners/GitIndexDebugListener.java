package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jgit.events.IndexChangedEvent;
import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.GitIndexListener;

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
