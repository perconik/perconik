package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.commands.common.NotDefinedException;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;

public final class CommandExecutionDebugListener extends AbstractDebugListener implements CommandExecutionListener {
  public CommandExecutionDebugListener() {}

  public CommandExecutionDebugListener(final DebugConsole console) {
    super(console);
  }

  public void preExecute(final String identifier, final ExecutionEvent event) {
    this.printHeader("Command pre execute");
    this.printLine("identifier", identifier);
    this.printExecutionEvent(event);
  }

  public void postExecuteSuccess(final String identifier, final Object result) {
    this.printHeader("Command execute success");
    this.printLine("identifier", identifier);
    this.printLine("result", result);
  }

  public void postExecuteFailure(final String identifier, final ExecutionException exception) {
    this.printHeader("Command execute failure");
    this.printLine("identifier", identifier);
    this.printLine("exception", exception);
  }

  public void notDefined(final String identifier, final NotDefinedException exception) {
    this.printHeader("Command not defined");
    this.printLine("identifier", identifier);
    this.printLine("exception", exception);
  }

  public void notEnabled(final String identifier, final NotEnabledException exception) {
    this.printHeader("Command not enabled");
    this.printLine("identifier", identifier);
    this.printLine("exception", exception);
  }

  public void notHandled(final String identifier, final NotHandledException exception) {
    this.printHeader("Command not handled");
    this.printLine("identifier", identifier);
    this.printLine("exception", exception);
  }

  private void printExecutionEvent(final ExecutionEvent event) {
    try {
      this.put(Debug.dumpExecutionEvent(event));
    } catch (CommandException e) {
      error("Command error", e);
    }
  }
}
