package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.core.commands.CommandEvent;
import org.eclipse.core.commands.common.CommandException;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.CommandListener;

public final class CommandDebugListener extends AbstractDebugListener implements CommandListener {
  public CommandDebugListener() {}

  public CommandDebugListener(final DebugConsole console) {
    super(console);
  }

  public final void commandChanged(final CommandEvent event) {
    this.printHeader("Command changed");
    this.printCommandEvent(event);
  }

  private final void printCommandEvent(final CommandEvent event) {
    try {
      this.put(Debug.dumpCommandEvent(event));
    } catch (CommandException e) {
      error("Command error", e);
    }
  }
}
