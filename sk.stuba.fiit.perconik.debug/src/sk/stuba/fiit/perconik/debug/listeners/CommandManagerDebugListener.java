package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.commands.CommandManagerEvent;
import sk.stuba.fiit.perconik.core.listeners.CommandManagerListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public final class CommandManagerDebugListener extends AbstractDebugListener implements CommandManagerListener
{
	public CommandManagerDebugListener()
	{
	}
	
	public CommandManagerDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void commandManagerChanged(final CommandManagerEvent event)
	{
		this.printHeader("Command manager changed");
		this.printCommandManagerEvent(event);
	}
	
	private final void printCommandManagerEvent(final CommandManagerEvent event)
	{
		this.put(Debug.dumpCommandManagerEvent(event));
	}
}
