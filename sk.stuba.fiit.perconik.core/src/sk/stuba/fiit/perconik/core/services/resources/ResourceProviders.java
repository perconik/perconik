package sk.stuba.fiit.perconik.core.services.resources;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider.Builder;

import static java.util.Arrays.asList;

/**
 * Static utility methods pertaining to {@link ResourceProvider} instances.
 * Also see this class's counterparts {@link ResourceServices}
 * and {@link ResourceManagers}.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourceProviders {
  // TODO add javadocs

  private ResourceProviders() {
    throw new AssertionError();
  }

  public static final Builder builder() {
    return StandardResourceProvider.builder();
  }

  public static final Builder builder(@Nullable final ResourceProvider parent) {
    Builder builder = builder();

    if (parent != null) {
      builder.parent(parent);
    }

    return builder;
  }

  public static final ResourceNamesSupplier supplier(final ResourceProvider provider) {
    return new ResourceNamesSupplier() {
      public final SetMultimap<Class<? extends Listener>, String> get() {
        return toResourceNamesMultimap(provider);
      }
    };
  }

  public static final ResourceNamesSupplier merge(final ResourceNamesSupplier ... suppliers) {
    return merge(asList(suppliers));
  }

  public static final ResourceNamesSupplier merge(final Iterable<ResourceNamesSupplier> suppliers) {
    return new ResourceNamesSupplier() {
      public final SetMultimap<Class<? extends Listener>, String> get() {
        SetMultimap<Class<? extends Listener>, String> names = HashMultimap.create();

        for (ResourceNamesSupplier supplier: suppliers) {
          names.putAll(supplier.get());
        }

        return names;
      }
    };
  }

  public static final SetMultimap<Class<? extends Listener>, String> toResourceNamesMultimap(final ResourceProvider provider) {
    SetMultimap<Class<? extends Listener>, String> names = HashMultimap.create();

    for (Class<? extends Listener> type: provider.types()) {
      for (Resource<?> resource: provider.forType(type)) {
        names.put(type, resource.getName());
      }
    }

    return names;
  }

  public static final ResourceProvider getSystemProvider() {
    return SystemResourceProvider.getInstance();
  }
}
