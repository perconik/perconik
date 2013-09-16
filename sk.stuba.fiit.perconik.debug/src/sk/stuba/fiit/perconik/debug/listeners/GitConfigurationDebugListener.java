package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jgit.events.ConfigChangedEvent;
import sk.stuba.fiit.perconik.core.listeners.GitConfigurationListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

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
