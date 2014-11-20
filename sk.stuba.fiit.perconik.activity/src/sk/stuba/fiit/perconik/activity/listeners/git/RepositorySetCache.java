package sk.stuba.fiit.perconik.activity.listeners.git;

import org.eclipse.jgit.lib.Repository;

final class RepositorySetCache<E> {
  private static final Object key = new Object();

  private final RepositoryMapCache<Object, E> map;

  RepositorySetCache() {
    this.map = new RepositoryMapCache<>();
  }

  E update(final Repository repository, final E value) {
    return this.map.update(repository, key, value);
  }

  boolean updated(final Repository repository, final E value) {
    return this.map.updated(repository, key, value);
  }
}
