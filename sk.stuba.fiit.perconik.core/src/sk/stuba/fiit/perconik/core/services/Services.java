package sk.stuba.fiit.perconik.core.services;

import java.util.Set;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import sk.stuba.fiit.perconik.core.listeners.DefaultListeners;
import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Static accessor methods to core services.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Services {
  static {
    Internals.setApi(ResourceService.class, new Supplier<ResourceService>() {
      public ResourceService get() {
        return DefaultResources.getDefaultResourceService();
      }
    });

    Internals.setApi(ListenerService.class, new Supplier<ListenerService>() {
      public ListenerService get() {
        return DefaultListeners.getDefaultListenerService();
      }
    });
  }

  private Services() {}

  static Set<Service> inStartOrder() {
    synchronized (Internals.class) {
      ImmutableSet.Builder<Service> builder = ImmutableSet.builder();

      builder.add(Services.getResourceService());
      builder.add(Services.getListenerService());

      return builder.build();
    }
  }

  static Set<Service> inStopOrder() {
    return ImmutableSet.copyOf(Lists.reverse(newArrayList(inStartOrder())));
  }

  /**
   * Sets the resource service.
   * @param service the service, not {@code null}
   */
  public static void setResourceService(final ResourceService service) {
    synchronized (Internals.class) {
      Internals.setApi(ResourceService.class, service);
    }
  }

  /**
   * Sets the listener service.
   * @param service the service, not {@code null}
   */
  public static void setListenerService(final ListenerService service) {
    synchronized (Internals.class) {
      Internals.setApi(ListenerService.class, service);
    }
  }

  /**
   * Unsets the resource service.
   */
  public static void unsetResourceService() {
    synchronized (Internals.class) {
      Internals.unsetApi(ResourceService.class);
    }
  }

  /**
   * Unsets the listener service.
   */
  public static void unsetListenerService() {
    synchronized (Internals.class) {
      Internals.unsetApi(ListenerService.class);
    }
  }

  /**
   * Returns the currently active resource service.
   */
  public static ResourceService getResourceService() {
    synchronized (Internals.class) {
      return Internals.getApi(ResourceService.class);
    }
  }

  /**
   * Returns the currently active listener service.
   */
  public static ListenerService getListenerService() {
    synchronized (Internals.class) {
      return Internals.getApi(ListenerService.class);
    }
  }
}
