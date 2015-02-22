package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

final class Unsafe {
  private Unsafe() {}

  @SuppressWarnings({"unchecked", "unused"})
  static <L extends Listener> Resource<L> cast(final Class<L> type, final Resource<?> resource) {
    return (Resource<L>) resource;
  }
}
