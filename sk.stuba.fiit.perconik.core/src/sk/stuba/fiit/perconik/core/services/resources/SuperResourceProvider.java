package sk.stuba.fiit.perconik.core.services.resources;

import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

import static java.util.Collections.emptySet;

final class SuperResourceProvider extends AbstractResourceProvider {
  private static final ResourceProvider instance = new SuperResourceProvider();

  private SuperResourceProvider() {}

  static ResourceProvider getInstance() {
    return instance;
  }

  public Resource<?> forName(final String name) {
    return null;
  }

  public <L extends Listener> Set<Resource<L>> forType(final Class<L> type) {
    return emptySet();
  }

  public Set<String> names() {
    return emptySet();
  }

  public Set<Class<? extends Listener>> types() {
    return emptySet();
  }

  public ResourceProvider parent() {
    return null;
  }
}
