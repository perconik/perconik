package sk.stuba.fiit.perconik.activity.listeners.git;

import java.io.File;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.jgit.lib.Repository;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Maps.immutableEntry;
import static com.google.common.collect.Maps.newConcurrentMap;

final class RepositoryMapCache<K, V> {
  private final ConcurrentMap<Entry<File, K>, V> map;

  RepositoryMapCache() {
    this.map = newConcurrentMap();
  }

  V update(final Repository repository, final K key, final V value) {
    return this.map.replace(immutableEntry(repository.getDirectory(), key), requireNonNull(value));
  }

  boolean updated(final Repository repository, final K key, final V value) {
    return this.update(repository, key, value) != null;
  }
}
