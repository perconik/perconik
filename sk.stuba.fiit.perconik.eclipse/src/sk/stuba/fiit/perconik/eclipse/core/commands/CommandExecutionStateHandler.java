package sk.stuba.fiit.perconik.eclipse.core.commands;

import java.util.concurrent.atomic.AtomicReference;

import static sk.stuba.fiit.perconik.eclipse.core.commands.CommandExecutionState.WAITING;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullOrEmpty;

public final class CommandExecutionStateHandler {
  private final String identifier;

  final AtomicReference<CommandExecutionState> state;

  CommandExecutionStateHandler(final String identifier) {
    this.identifier = checkNotNullOrEmpty(identifier);

    this.state = new AtomicReference<>(WAITING);
  }

  public static CommandExecutionStateHandler of(final String identifier) {
    return new CommandExecutionStateHandler(identifier);
  }

  public void transit(final CommandExecutionState state) {
    this.state.set(state);
  }

  public void transitOnMatch(final String identifier, final CommandExecutionState state) {
    if (identifier.equals(this.identifier)) {
      this.state.set(state);
    }
  }

  public boolean compareAndTransit(final CommandExecutionState expect, final CommandExecutionState state) {
    return this.state.compareAndSet(expect, state);
  }

  public boolean compareAndTransitOnMatch(final String identifier, final CommandExecutionState expect, final CommandExecutionState state) {
    return identifier.equals(this.identifier) && this.state.compareAndSet(expect, state);
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public CommandExecutionState getState() {
    return this.state.get();
  }
}
