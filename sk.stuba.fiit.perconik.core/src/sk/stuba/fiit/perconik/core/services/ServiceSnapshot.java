package sk.stuba.fiit.perconik.core.services;

/**
 * A dynamically created accessor to a snapshot of active core services. 
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ServiceSnapshot {
  ServiceGroup<Service> services;

  private ServiceSnapshot() {
    this.services = new ServiceGroup<>(Services.inStartOrder());
  }

  /**
   * Takes a snapshot of currently active core services.
   * @return new snapshot of currently active core services
   */
  public static ServiceSnapshot take() {
    return new ServiceSnapshot();
  }

  /**
   * Gets snapshotted core services.
   */
  public ServiceGroup<Service> services() {
    return this.services;
  }

  /**
   * Gets snapshotted core services in start order.
   */
  public ServiceGroup<Service> servicesInStartOrder() {
    return this.services;
  }

  /**
   * Gets snapshotted core services in stop order.
   */
  public ServiceGroup<Service> servicesInStopOrder() {
    return this.services.reverse();
  }
}
