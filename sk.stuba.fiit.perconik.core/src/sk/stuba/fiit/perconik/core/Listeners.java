package sk.stuba.fiit.perconik.core;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.annotations.Internal;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;
import sk.stuba.fiit.perconik.utilities.reflect.Reflections;

import static java.util.Arrays.asList;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Sets.newHashSetWithExpectedSize;

/**
 * Static accessor methods pertaining to the listeners core.
 *
 * <p>This class provides access to the underlying functionality
 * of the currently active {@code ListenerService}.
 *
 * @see Listener
 * @see Resources
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Listeners {
  // TODO add javadocs

  private Listeners() {}

  static ListenerService service() {
    return Services.getListenerService();
  }

  static ListenerProvider provider() {
    return service().getListenerProvider();
  }

  static ListenerManager manager() {
    return service().getListenerManager();
  }

  public static Listener forClass(final Class<? extends Listener> type) {
    return provider().forClass(type);
  }

  public static void register(final Listener listener) {
    manager().register(listener);
  }

  public static void registerAll(final Listener ... listeners) {
    registerAll(asList(listeners));
  }

  public static void registerAll(final Iterable<? extends Listener> listeners) {
    List<Exception> failures = newLinkedList();

    ListenerManager manager = manager();

    for (Listener listener: listeners) {
      try {
        manager.register(listener);
      } catch (Exception failure) {
        failures.add(failure);
      }
    }

    if (!failures.isEmpty()) {
      throw MoreThrowables.initializeSuppressor(new ListenerRegistrationException(), failures);
    }
  }

  public static void registerAll(final ListenerClassesSupplier supplier) {
    List<Exception> failures = newLinkedList();

    ListenerProvider provider = provider();
    ListenerManager manager = manager();

    for (Class<? extends Listener> implementation: supplier.get()) {
      Listener listener = provider.forClass(implementation);

      try {
        manager.register(listener);
      } catch (Exception failure) {
        failures.add(failure);
      }
    }

    if (!failures.isEmpty()) {
      throw MoreThrowables.initializeSuppressor(new ListenerRegistrationException(), failures);
    }
  }

  public static void unregister(final Listener listener) {
    manager().unregister(listener);
  }

  public static void unregisterAll() {
    unregisterAll(Listener.class);
  }

  public static void unregisterAll(final Class<? extends Listener> type) {
    manager().unregisterAll(type);
  }

  public static Collection<Listener> registered() {
    return registered(Listener.class);
  }

  public static <L extends Listener> Collection<L> registered(final Class<L> type) {
    return manager().registered(type);
  }

  public static SetMultimap<Resource<?>, Listener> registrations() {
    return manager().registrations();
  }

  public static boolean isRegistered(final Listener listener) {
    return manager().registered(listener);
  }

  public static Set<Class<? extends Listener>> resolveTypes(final Listener listener) {
    return resolveTypes(listener.getClass());
  }

  public static Set<Class<? extends Listener>> resolveTypes(final Class<? extends Listener> type) {
    Set<Class<?>> raw = Reflections.collectInterfaces(type);

    raw.remove(Registrable.class);
    raw.remove(Listener.class);

    Set<Class<? extends Listener>> types = newHashSetWithExpectedSize(raw.size());

    for (Class<?> supertype: raw) {
      if (Listener.class.isAssignableFrom(supertype) && !supertype.isAnnotationPresent(Internal.class)) {
        types.add(supertype.asSubclass(Listener.class));
      }
    }

    Iterable<Class<? extends Listener>> iterable = newArrayList(types);

    for (Class<?> a: iterable) {
      for (Class<?> b: iterable) {
        if (a != b && a.isAssignableFrom(b)) {
          types.remove(a);
        }
      }
    }

    return types;
  }
}
