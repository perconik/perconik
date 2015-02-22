package sk.stuba.fiit.perconik.core.persistence.data;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;

final class Unsafe {
  private Unsafe() {}

  @SuppressWarnings({"unchecked", "unused"})
  static <L extends Listener> Resource<L> cast(final Class<L> type, final Resource<?> resource) {
    return (Resource<L>) resource;
  }

  static <L extends Listener> void register(final Class<L> type, final Resource<?> resource) {
    Resources.register(type, cast(type, resource));
  }

  static <L extends Listener> void unregister(final Class<L> type, final Resource<?> resource) {
    Resources.unregister(type, cast(type, resource));
  }
}
