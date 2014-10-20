package sk.stuba.fiit.perconik.core.debug.services.resources;

import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.DebugNameableProxy;
import sk.stuba.fiit.perconik.core.debug.resources.DebugResourceProxy;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSetWithExpectedSize;

public final class DebugResourceProviderProxy extends DebugNameableProxy implements DebugResourceProvider {
  private final ResourceProvider provider;

  private DebugResourceProviderProxy(final ResourceProvider provider, final DebugConsole console) {
    super(console);

    this.provider = checkNotNull(provider);
  }

  public static DebugResourceProviderProxy wrap(final ResourceProvider provider) {
    return wrap(provider, Debug.getDefaultConsole());
  }

  public static DebugResourceProviderProxy wrap(final ResourceProvider provider, final DebugConsole console) {
    if (provider instanceof DebugResourceProviderProxy) {
      return (DebugResourceProviderProxy) provider;
    }

    return new DebugResourceProviderProxy(provider, console);
  }

  public static ResourceProvider unwrap(final ResourceProvider provider) {
    if (provider instanceof DebugResourceProviderProxy) {
      return ((DebugResourceProviderProxy) provider).delegate();
    }

    return provider;
  }

  private static <L extends Listener> Set<Resource<L>> wrap(final Set<Resource<L>> resources) {
    Set<Resource<L>> proxies = newHashSetWithExpectedSize(resources.size());

    for (Resource<L> resource: resources) {
      proxies.add(DebugResourceProxy.wrap(resource));
    }

    return proxies;
  }

  @Override
  public ResourceProvider delegate() {
    return this.provider;
  }

  public Resource<?> forName(final String name) {
    this.put("Requesting resource by name %s ... ", name);

    Resource<?> resource = this.delegate().forName(name);

    if (resource != null) {
      this.print("done");

      return DebugResourceProxy.wrap(resource);
    }

    this.print("failed");

    return null;
  }

  public <L extends Listener> Set<Resource<L>> forType(final Class<L> type) {
    this.put("Requesting resources for listener type %s ... ", type.getName());

    Set<Resource<L>> resources = this.delegate().forType(type);

    this.print(!resources.isEmpty() ? "done" : "failed");

    return wrap(resources);
  }

  public Set<String> names() {
    return this.delegate().names();
  }

  public Set<Class<? extends Listener>> types() {
    return this.delegate().types();
  }

  public ResourceProvider parent() {
    return this.delegate().parent();
  }
}
