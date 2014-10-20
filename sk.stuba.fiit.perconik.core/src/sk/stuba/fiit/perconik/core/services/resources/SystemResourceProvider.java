package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Collections;
import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

final class SystemResourceProvider extends AbstractResourceProvider {
  private static final ResourceProvider instance = new SystemResourceProvider();

  private SystemResourceProvider() {}

  static ResourceProvider getInstance() {
    return instance;
  }

  public Resource<?> forName(final String name) {
    return null;
  }

  public <L extends Listener> Set<Resource<L>> forType(final Class<L> type) {
    return Collections.emptySet();
  }

  public Set<String> names() {
    return Collections.emptySet();
  }

  public Set<Class<? extends Listener>> types() {
    return Collections.emptySet();
  }

  public ResourceProvider parent() {
    return null;
  }
}
