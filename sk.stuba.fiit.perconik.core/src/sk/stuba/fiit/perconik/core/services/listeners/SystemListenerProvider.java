package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import sk.stuba.fiit.perconik.core.IllegalListenerClassException;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.StaticAccessor;

final class SystemListenerProvider extends AbstractListenerProvider {
  private static final ListenerProvider instance = new SystemListenerProvider();

  private final BiMap<String, Class<? extends Listener>> map;

  private SystemListenerProvider() {
    this.map = HashBiMap.create();
  }

  static final ListenerProvider getInstance() {
    return instance;
  }

  @Override
  protected final ClassLoader loader() {
    return ClassLoader.getSystemClassLoader();
  }

  public final <L extends Listener> L forClass(final Class<L> implementation) {
    if (!this.map.containsValue(implementation)) {
      cast(implementation);
    }

    try {
      return StaticAccessor.ofEnumConstant(implementation, "INSTANCE").get();
    } catch (Exception e) {
      throw new IllegalListenerClassException(e);
    }
  }

  public final Class<? extends Listener> loadClass(final String name) throws ClassNotFoundException {
    Class<? extends Listener> type = this.map.get(name);

    if (type != null) {
      return type;
    }

    type = cast(this.load(name));

    this.map.put(name, type);

    return type;
  }

  public final Set<Class<? extends Listener>> classes() {
    return this.map.values();
  }

  public final ListenerProvider parent() {
    return null;
  }
}
