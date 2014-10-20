package sk.stuba.fiit.perconik.core.debug.services.listeners;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManagers;

public final class DebugListenerManagers {
  private DebugListenerManagers() {}

  public static DebugListenerManager create() {
    ListenerManager manager = ListenerManagers.create();

    return DebugListenerManagerProxy.wrap(manager);
  }
}
