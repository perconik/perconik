package sk.stuba.fiit.perconik.eclipse.core.commands;

import java.util.concurrent.atomic.AtomicReference;

import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.WAITING;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullAndNotEmpty;

public final class CommandExecutionStateHandler
{
	private final String identifier;
	
	final AtomicReference<CommandExecutionState> state;
	
	CommandExecutionStateHandler(final String identifier)
	{
		this.identifier = checkNotNullAndNotEmpty(identifier);
		this.state      = new AtomicReference<>(WAITING);
	}
	
	public static final CommandExecutionStateHandler of(final String identifier)
	{
		return new CommandExecutionStateHandler(identifier);
	}
	
	public final void transit(final CommandExecutionState state)
	{
		this.state.set(state);
	}
	
	public final void transitOnMatch(final String identifier, final CommandExecutionState state)
	{
		if (identifier.equals(this.identifier))
		{
			this.state.set(state);
		}
	}
	
	public final boolean compareAndTransit(final CommandExecutionState expect, final CommandExecutionState state)
	{
		return this.state.compareAndSet(expect, state);
	}

	public final boolean compareAndTransitOnMatch(final String identifier, final CommandExecutionState expect, final CommandExecutionState state)
	{
		return identifier.equals(this.identifier) && this.state.compareAndSet(expect, state);
	}
	
	public final String getIdentifier()
	{
		return this.identifier;
	}
	
	public final CommandExecutionState getState()
	{
		return this.state.get();
	}
}
