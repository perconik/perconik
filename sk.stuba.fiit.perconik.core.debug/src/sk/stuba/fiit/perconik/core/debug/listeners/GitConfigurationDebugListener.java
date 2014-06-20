package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jgit.events.ConfigChangedEvent;
import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.GitConfigurationListener;

public final class GitConfigurationDebugListener extends AbstractDebugListener implements GitConfigurationListener
{
	public GitConfigurationDebugListener()
	{
	}
	
	public GitConfigurationDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void onConfigChanged(final ConfigChangedEvent event)
	{
		this.printHeader("Git configuration changed");
		this.printGitConfigurationEvent(event);
	}

	private final void printGitConfigurationEvent(final ConfigChangedEvent event)
	{
		this.put(Debug.dumpGitConfigurationEvent(event));
	}
}
