package sk.stuba.fiit.perconik.core.debug.resources;

import java.util.Collection;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.DebugListeners;
import sk.stuba.fiit.perconik.core.debug.DebugRegistrableProxy;
import sk.stuba.fiit.perconik.core.debug.DebugResource;
import sk.stuba.fiit.perconik.core.debug.DebugResources;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugProxy;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;

import static com.google.common.base.Preconditions.checkNotNull;

@DebugProxy
public final class DebugResourceProxy<L extends Listener> extends DebugRegistrableProxy implements DebugResource<L> {
  private final Resource<L> resource;

  private DebugResourceProxy(final Resource<L> resource, final DebugConsole console) {
    super(console);

    this.resource = checkNotNull(resource);
  }

  public static <L extends Listener> DebugResourceProxy<L> wrap(final Resource<L> resource) {
    return wrap(resource, Debug.getDefaultConsole());
  }

  public static <L extends Listener> DebugResourceProxy<L> wrap(final Resource<L> resource, final DebugConsole console) {
    if (resource instanceof DebugResourceProxy) {
      return (DebugResourceProxy<L>) resource;
    }

    return new DebugResourceProxy<>(resource, console);
  }

  public static <L extends Listener> Resource<L> unwrap(final Resource<L> resource) {
    if (resource instanceof DebugResourceProxy) {
      return ((DebugResourceProxy<L>) resource).delegate();
    }

    return resource;
  }

  @Override
  public Resource<L> delegate() {
    return this.resource;
  }

  public void register(final L listener) {
    this.print("Registering listener %s to resource %s", DebugListeners.toString(listener), DebugResources.toString(this.delegate()));
    this.tab();

    this.delegate().register(listener);

    this.untab();
  }

  public void unregister(final L listener) {
    this.print("Unregistering listener %s from resource %s", DebugListeners.toString(listener), DebugResources.toString(this.delegate()));
    this.tab();

    this.delegate().unregister(listener);

    this.untab();
  }

  public <U extends Listener> Collection<U> registered(final Class<U> type) {
    return this.delegate().registered(type);
  }

  public boolean registered(final Listener listener) {
    return this.delegate().registered(listener);
  }

  public String getName() {
    return this.delegate().getName();
  }
}
