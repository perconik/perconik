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
      public final ResourceService get() {
        return DefaultResources.getDefaultResourceService();
      }
    });

    Internals.setApi(ListenerService.class, new Supplier<ListenerService>() {
      public final ListenerService get() {
        return DefaultListeners.getDefaultListenerService();
      }
    });
  }

  private Services() {
    throw new AssertionError();
  }

  static final Set<Service> inStartOrder() {
    synchronized (Internals.class) {
      ImmutableSet.Builder<Service> builder = ImmutableSet.builder();

      builder.add(Services.getResourceService());
      builder.add(Services.getListenerService());

      return builder.build();
    }
  }

  static final Set<Service> inStopOrder() {
    return ImmutableSet.copyOf(Lists.reverse(newArrayList(inStartOrder())));
  }

  /**
   * Sets the resource service.
   * @param service the service, not {@code null}
   */
  public static final void setResourceService(final ResourceService service) {
    Internals.setApi(ResourceService.class, service);
  }

  /**
   * Sets the listener service.
   * @param service the service, not {@code null}
   */
  public static final void setListenerService(final ListenerService service) {
    Internals.setApi(ListenerService.class, service);
  }

  /**
   * Returns the currently active resource service.
   */
  public static final ResourceService getResourceService() {
    return Internals.getApi(ResourceService.class);
  }

  /**
   * Returns the currently active listener service.
   */
  public static final ListenerService getListenerService() {
    return Internals.getApi(ListenerService.class);
  }
}
