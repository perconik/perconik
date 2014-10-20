package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.ListenerAlreadyRegistredException;
import sk.stuba.fiit.perconik.core.ListenerNotRegistredException;

/**
 * Static utility methods pertaining to {@link ListenerManager} instances.
 * Also see this class's counterparts {@link ListenerServices}
 * and {@link ListenerProviders}.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenerManagers {
  private ListenerManagers() {}

  /**
   * Creates a standard listener manager.
   *
   * <p>Standard listener manager is a reference implementation
   * of {@link ListenerManager} interface.
   *
   * <p>The returned manager never panics. In other words it does
   * not propagate {@link ListenerAlreadyRegistredException}
   * or {@link ListenerNotRegistredException}.
   */
  public static ListenerManager create() {
    return new StandardListenerManager();
  }
}
