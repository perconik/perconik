package sk.stuba.fiit.perconik.core.debug.services.listeners;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManagerFactory;

public final class DebugListenerManagerFactory implements ListenerManagerFactory {
  public DebugListenerManagerFactory() {}

  public final ListenerManager create() {
    return DebugListenerManagers.create();
  }
}
