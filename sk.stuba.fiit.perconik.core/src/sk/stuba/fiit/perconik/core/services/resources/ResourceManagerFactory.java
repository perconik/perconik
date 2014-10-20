package sk.stuba.fiit.perconik.core.services.resources;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.services.ManagerFactory;

/**
 * The {@code ResourceManagerFactory}
 * creates {@link ResourceManager} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ResourceManagerFactory extends ManagerFactory {
  /**
   * Creayes a resource manager.
   */
  @Override
  public ResourceManager create();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(@Nullable Object o);
}
