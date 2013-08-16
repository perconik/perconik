package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.commands.CommandManagerEvent;
import sk.stuba.fiit.perconik.core.listeners.CommandManagerChangeListener;
import sk.stuba.fiit.perconik.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class CommandManagerChangeDebugListener extends AbstractDebugListener implements CommandManagerChangeListener
{
	public CommandManagerChangeDebugListener()
	{
	}
	
	public CommandManagerChangeDebugListener(final DebugConsole console)
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
