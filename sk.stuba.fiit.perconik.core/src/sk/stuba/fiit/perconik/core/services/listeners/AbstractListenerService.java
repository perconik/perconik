package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.services.AbstractGenericService;

/**
 * An abstract implementation of {@link ListenerService}. This skeleton
 * implementation provides an abstract builder mechanism to construct
 * instances of extended classes. It holds listener provider and manager.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractListenerService extends AbstractGenericService<ListenerProvider, ListenerManager> implements ListenerService {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractListenerService(final AbstractBuilder<?> builder) {
    super(builder);
  }

  /**
   * An abstract builder for creating listener service instances.
   * 
   * <p>Builder instances can be reused, it is safe to call {@link #build}
   * multiple times to build multiple listener services in series.
   * 
   * @author Pavol Zbell
   * @since 1.0
   */
  protected static abstract class AbstractBuilder<B extends AbstractBuilder<B>> extends AbstractGenericBuilder<B, ListenerProvider, ListenerManager> implements Builder {
    /**
     * Constructor for use by subclasses.
     */
    protected AbstractBuilder() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract ListenerService build();
  }

  /**
   * {@inheritDoc}
   * @throws IllegalStateException {@inheritDoc}
   */
  public final ListenerProvider getListenerProvider() {
    this.checkRunning();

    return this.provider;
  }

  /**
   * {@inheritDoc}
   * @throws IllegalStateException {@inheritDoc}
   */
  public final ListenerManager getListenerManager() {
    this.checkRunning();

    return this.manager;
  }
}
