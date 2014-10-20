package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.debug.core.DebugEvent;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;

public final class DebugEventsDebugListener extends AbstractDebugListener implements DebugEventsListener {
  public DebugEventsDebugListener() {}

  public DebugEventsDebugListener(final DebugConsole console) {
    super(console);
  }

  public void handleDebugEvents(final DebugEvent[] events) {
    this.printHeader("Debug events");
    this.printDebugEvents(events);
  }

  private void printDebugEvents(final DebugEvent[] events) {
    this.put(Debug.dumpDebugEvents(events));
  }
}
