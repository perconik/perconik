package sk.stuba.fiit.perconik.core.services.listeners;

import java.util.Arrays;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider.Builder;

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

  private ListenerProviders() {
    throw new AssertionError();
  }

  public static final Builder builder() {
    return StandardListenerProvider.builder();
  }

  public static final Builder builder(@Nullable final ListenerProvider parent) {
    Builder builder = builder();

    if (parent != null) {
      builder.parent(parent);
    }

    return builder;
  }

  public static final ListenerClassesSupplier supplier(final ListenerProvider provider) {
    return new ListenerClassesSupplier() {
      public final Set<Class<? extends Listener>> get() {
        return provider.classes();
      }
    };
  }

  public static final ListenerClassesSupplier merge(final ListenerClassesSupplier ... suppliers) {
    return merge(Arrays.asList(suppliers));
  }

  public static final ListenerClassesSupplier merge(final Iterable<ListenerClassesSupplier> suppliers) {
    return new ListenerClassesSupplier() {
      public final Set<Class<? extends Listener>> get() {
        Set<Class<? extends Listener>> classes = Sets.newHashSet();

        for (ListenerClassesSupplier supplier: suppliers) {
          classes.addAll(supplier.get());
        }

        return classes;
      }
    };
  }

  public static final ListenerProvider getSystemProvider() {
    return SystemListenerProvider.getInstance();
  }
}
