package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.core.commands.IExecutionListenerWithChecks;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A command execution listener.
 * 
 * @see Listener
 * @see IExecutionListenerWithChecks
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface CommandExecutionListener extends Listener, IExecutionListenerWithChecks
{
}
