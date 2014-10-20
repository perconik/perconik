package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.services.resources.ResourceService.Builder;

/**
 * Static utility methods pertaining to {@link ResourceService} instances.
 * Also see this class's counterparts {@link ResourceProviders}
 * and {@link ResourceManagers}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourceServices {
  private ResourceServices() {}

  /**
   * Returns a new standard resource service builder.
   *
   * <p>Standard resource service is a reference implementation of
   * {@link ResourceService} interface. It has an operational state and
   * holds immutable references to resource provider and manager which
   * are accessible only while the service is running.
   *
   * <p>Transitions between operational states of the standard resource
   * service are thread safe as well as access to the resource provider
   * and manager. The service does not start any additional threads.
   */
  public static Builder builder() {
    return StandardResourceService.builder();
  }
}
