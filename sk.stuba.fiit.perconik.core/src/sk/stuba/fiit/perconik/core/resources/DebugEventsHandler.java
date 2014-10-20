package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.debug.core.DebugPlugin;

import sk.stuba.fiit.perconik.core.listeners.DebugEventsListener;

enum DebugEventsHandler implements Handler<DebugEventsListener> {
  INSTANCE;

  public void register(final DebugEventsListener listener) {
    DebugPlugin.getDefault().addDebugEventListener(listener);
  }

  public void unregister(final DebugEventsListener listener) {
    DebugPlugin.getDefault().removeDebugEventListener(listener);
  }
}
