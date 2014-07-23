package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.ResourceAlreadyRegistredException;
import sk.stuba.fiit.perconik.core.ResourceNotRegistredException;

/**
 * Static utility methods pertaining to {@link ResourceManager} instances.
 * Also see this class's counterparts {@link ResourceServices}
 * and {@link ResourceProviders}.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourceManagers {
  private ResourceManagers() {
    throw new AssertionError();
  }

  /**
   * Creates a standard resource manager.
   * 
   * <p>Standard resource manager is a reference implementation
   * of {@link ResourceManager} interface.
   * 
   * <p>The returned manager never panics. In other words it does
   * not propagate {@link ResourceAlreadyRegistredException}
   * or {@link ResourceNotRegistredException}.
   */
  public static final ResourceManager create() {
    return new StandardResourceManager();
  }
}
