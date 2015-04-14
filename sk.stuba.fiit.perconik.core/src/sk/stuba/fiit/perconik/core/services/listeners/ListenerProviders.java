package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Set;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider.Builder;

import static java.util.Arrays.asList;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Static utility methods pertaining to {@link ListenerProvider} instances.
 * Also see this class's counterparts {@link ListenerServices}
 * and {@link ListenerManagers}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenerProviders {
  // TODO add javadocs

  private ListenerProviders() {}

  public static ListenerProvider superListenerProvider() {
    return SuperListenerProvider.getInstance();
  }

  public static Builder builder() {
    return StandardListenerProvider.builder();
  }

  public static Builder builder(@Nullable final ListenerProvider parent) {
    Builder builder = builder();

    if (parent != null) {
      builder.parent(parent);
    }

    return builder;
  }

  public static ListenerClassesSupplier supplier(final ListenerProvider provider) {
    return new ListenerClassesSupplier() {
      public Set<Class<? extends Listener>> get() {
        return provider.classes();
      }
    };
  }

  public static ListenerClassesSupplier merge(final ListenerClassesSupplier ... suppliers) {
    return merge(asList(suppliers));
  }

  public static ListenerClassesSupplier merge(final Iterable<ListenerClassesSupplier> suppliers) {
    return new ListenerClassesSupplier() {
      public Set<Class<? extends Listener>> get() {
        Set<Class<? extends Listener>> classes = newHashSet();

        for (ListenerClassesSupplier supplier: suppliers) {
          classes.addAll(supplier.get());
        }

        return classes;
      }
    };
  }
}
