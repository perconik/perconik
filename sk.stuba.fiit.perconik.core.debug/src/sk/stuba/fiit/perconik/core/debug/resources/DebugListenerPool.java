package sk.stuba.fiit.perconik.core.debug.resources;

import java.util.Collection;
import java.util.Set;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.debug.AbstractDebugResource;
import sk.stuba.fiit.perconik.core.debug.DebugListener;
import sk.stuba.fiit.perconik.core.debug.annotations.DebugImplementation;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newConcurrentHashSet;

@DebugImplementation
public final class DebugListenerPool extends AbstractDebugResource<DebugListener> {
  private static final DebugListenerPool instance = new DebugListenerPool();

  private final Set<DebugListener> pool = newConcurrentHashSet();

  private DebugListenerPool() {}

  public static DebugListenerPool getInstance() {
    return instance;
  }

  public void register(final DebugListener listener) {
    this.pool.add(listener);
  }

  public void unregister(final DebugListener listener) {
    this.pool.remove(listener);
  }

  public <U extends Listener> Collection<U> registered(final Class<U> type) {
    Collection<U> result = newArrayList();

    for (DebugListener listener: this.pool) {
      if (type.isInstance(listener)) {
        result.add(type.cast(listener));
      }
    }

    return result;
  }

  public boolean registered(final Listener listener) {
    return this.pool.contains(listener);
  }
}
