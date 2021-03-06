package sk.stuba.fiit.perconik.core.debug.services.listeners;

import java.util.Collection;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.DebugListeners;
import sk.stuba.fiit.perconik.core.debug.DebugNameableProxy;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;

import static com.google.common.base.Preconditions.checkNotNull;

public final class DebugListenerManagerProxy extends DebugNameableProxy implements DebugListenerManager {
  private final ListenerManager manager;

  private DebugListenerManagerProxy(final ListenerManager manager, final DebugConsole console) {
    super(console);

    this.manager = checkNotNull(manager);
  }

  public static DebugListenerManagerProxy wrap(final ListenerManager manager) {
    return wrap(manager, Debug.getDefaultConsole());
  }

  public static DebugListenerManagerProxy wrap(final ListenerManager manager, final DebugConsole console) {
    if (manager instanceof DebugListenerManagerProxy) {
      return (DebugListenerManagerProxy) manager;
    }

    return new DebugListenerManagerProxy(manager, console);
  }

  public static ListenerManager unwrap(final ListenerManager manager) {
    if (manager instanceof DebugListenerManagerProxy) {
      return ((DebugListenerManagerProxy) manager).delegate();
    }

    return manager;
  }

  @Override
  public ListenerManager delegate() {
    return this.manager;
  }

  public <L extends Listener> void register(final L listener) {
    this.print("Registering listener %s", DebugListeners.toString(listener));
    this.tab();

    this.delegate().register(listener);

    this.untab();
  }

  public <L extends Listener> void unregister(final L listener) {
    this.print("Unregistering listener %s", DebugListeners.toString(listener));
    this.tab();

    this.delegate().unregister(listener);

    this.untab();
  }

  public <L extends Listener> void unregisterAll(final Class<L> type) {
    this.print("Unregistering all listeners assignable to listener type %s", DebugListeners.toString(type));
    this.tab();

    this.delegate().unregisterAll(type);

    this.untab();
  }

  public SetMultimap<Resource<?>, Listener> registrations() {
    return this.delegate().registrations();
  }

  public <L extends Listener> Collection<L> registered(final Class<L> type) {
    return this.delegate().registered(type);
  }

  public boolean registered(final Listener listener) {
    return this.delegate().registered(listener);
  }
}
