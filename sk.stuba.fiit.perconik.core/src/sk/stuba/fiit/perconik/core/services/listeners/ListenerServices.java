package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerService.Builder;

/**
 * Static utility methods pertaining to {@link ListenerService} instances.
 * Also see this class's counterparts {@link ListenerProviders}
 * and {@link ListenerManagers}.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenerServices {
  private ListenerServices() {
    throw new AssertionError();
  }

  /**
   * Returns a new standard listener service builder.
   * 
   * <p>Standard listener service is a reference implementation of
   * {@link ListenerService} interface. It has an operational state and
   * holds immutable references to listener provider and manager which
   * are accessible only while the service is running.
   * 
   * <p>Transitions between operational states of the standard listener
   * service are thread safe as well as access to the listener provider
   * and manager. The service does not start any additional threads.
   */
  public static final Builder builder() {
    return StandardListenerService.builder();
  }
}
