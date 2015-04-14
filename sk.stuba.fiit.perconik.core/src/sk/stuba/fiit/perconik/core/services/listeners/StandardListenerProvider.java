package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import sk.stuba.fiit.perconik.core.IllegalListenerClassException;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.ListenerInstantiationException;
import sk.stuba.fiit.perconik.utilities.MoreSets;
import sk.stuba.fiit.perconik.utilities.reflect.ReflectionException;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.AccessorConstructionException;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Maps.newConcurrentMap;

final class StandardListenerProvider extends AbstractListenerProvider {
  private final BiMap<String, Class<? extends Listener>> nameToImplementation;

  private final Map<Class<? extends Listener>, Listener> implementationToListenerCache;

  private final ListenerProvider parent;

  StandardListenerProvider(final Builder builder) {
    this.nameToImplementation = HashBiMap.create(builder.nameToImplementation);
    this.implementationToListenerCache = newConcurrentMap();
    this.parent = builder.parent.or(ListenerProviders.superListenerProvider());
  }

  public static class Builder implements ListenerProvider.Builder {
    final BiMap<String, Class<? extends Listener>> nameToImplementation;

    Optional<ListenerProvider> parent;

    public Builder() {
      this.nameToImplementation = HashBiMap.create();
      this.parent = Optional.absent();
    }

    public Builder add(final Class<? extends Listener> implementation) {
      checkNotNull(implementation);

      this.nameToImplementation.put(implementation.getName(), implementation.asSubclass(Listener.class));

      return this;
    }

    public Builder addAll(final Iterable<Class<? extends Listener>> implementations) {
      for (Class<? extends Listener> type: implementations) {
        this.add(type);
      }

      return this;
    }

    public Builder parent(final ListenerProvider parent) {
      checkState(!this.parent.isPresent());

      this.parent = Optional.of(checkNotNull(parent));

      return this;
    }

    public StandardListenerProvider build() {
      return new StandardListenerProvider(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  protected ClassLoader loader() {
    return this.getClass().getClassLoader();
  }

  public <L extends Listener> L forClass(final Class<L> implementation) {
    Listener listener = this.implementationToListenerCache.get(cast(implementation));

    if (listener != null) {
      return implementation.cast(listener);
    }

    L instance;

    try {
      instance = StaticListenerLookup.forClass(implementation).get();
    } catch (ReflectionException e) {
      Throwable[] suppressions = e.getSuppressed();

      Exception cause;

      if (suppressions.length == 1 && suppressions[0] instanceof AccessorConstructionException) {
        cause = new IllegalListenerClassException(suppressions[0]);
      } else {
        cause = new ListenerInstantiationException(e);
      }

      return this.parentForClass(implementation, cause);
    }

    if (!this.nameToImplementation.containsValue(implementation)) {
      this.nameToImplementation.put(implementation.getName(), implementation);
    }

    this.implementationToListenerCache.put(implementation, instance);

    assert this.nameToImplementation.size() >= this.implementationToListenerCache.size();

    return instance;
  }

  public Class<? extends Listener> loadClass(final String name) throws ClassNotFoundException {
    Class<? extends Listener> type = this.nameToImplementation.get(name);

    if (type != null) {
      return type;
    }

    try {
      return cast(this.load(name));
    } catch (Exception cause) {
      return this.parentLoadClass(name, cause);
    }
  }

  public Set<Class<? extends Listener>> classes() {
    return MoreSets.newHashSet(this.nameToImplementation.values(), this.parent.classes());
  }

  public ListenerProvider parent() {
    return this.parent;
  }
}
