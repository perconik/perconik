package sk.stuba.fiit.perconik.core.services;

import com.google.common.base.Preconditions;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An abstract extension of {@link AbstractService} class holding
 * {@link Provider} and {@link Manager} instances. This skeleton
 * implementation provides an abstract builder mechanism to construct
 * instances of extended classes.
 *
 * @param <P> the type of the provider for this service
 * @param <M> the type of the manager for this service
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractGenericService<P extends Provider, M extends Manager> extends AbstractService {
  /**
   * The provider.
   */
  protected final P provider;

  /**
   * The manager.
   */
  protected final M manager;

  /**
   * Constructor for use by subclasses.
   */
  protected AbstractGenericService(final AbstractGenericBuilder<?, P, M> builder) {
    this.provider = checkNotNull(builder.provider);
    this.manager = checkNotNull(builder.manager);
  }

  /**
   * An abstract builder for creating generic service instances.
   *
   * <p>Builder instances can be reused, it is safe to call {@link #build}
   * multiple times to build multiple generic services in series.
   *
   * @param <B> the type of the extender of this builder
   * @param <P> the type of the provider for the built service
   * @param <M> the type of the manager for the built service
   *
   * @author Pavol Zbell
   * @since 1.0
   */
  protected static abstract class AbstractGenericBuilder<B extends AbstractGenericBuilder<B, P, M>, P extends Provider, M extends Manager> {
    P provider;

    M manager;

    /**
     * Constructor for use by subclasses.
     */
    protected AbstractGenericBuilder() {}

    /**
     * Must always return {@code this}.
     */
    protected abstract B asSubtype();

    /**
     * Sets the provider of the built generic service.
     * @param provider the provider, not {@code null}
     * @return this {@code Builder} object
     * @throws NullPointerException if the provider is {@code null}
     * @throws IllegalStateException if the provider is already set
     */
    public final B provider(final P provider) {
      Preconditions.checkState(this.provider == null);

      this.provider = checkNotNull(provider);

      return this.asSubtype();
    }

    /**
     * Sets the manager of the built generic service.
     * @param manager the provider, not {@code null}
     * @return this {@code Builder} object
     * @throws NullPointerException if the manager is {@code null}
     * @throws IllegalStateException if the manager is already set
     */
    public final B manager(final M manager) {
      Preconditions.checkState(this.manager == null);

      this.manager = checkNotNull(manager);

      return this.asSubtype();
    }

    /**
     * Returns a newly created generic service.
     */
    public abstract Service build();
  }
}
