package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.core.commands.CommandManagerEvent;

import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.CommandManagerListener;

@Unsupported
public final class CommandManagerDebugListener extends AbstractDebugListener implements CommandManagerListener {
  public CommandManagerDebugListener() {}

  public CommandManagerDebugListener(final DebugConsole console) {
    super(console);
  }

  public void commandManagerChanged(final CommandManagerEvent event) {
    this.printHeader("Command manager changed");
    this.printCommandManagerEvent(event);
  }

  private void printCommandManagerEvent(final CommandManagerEvent event) {
    this.put(Debug.dumpCommandManagerEvent(event));
  }
}
