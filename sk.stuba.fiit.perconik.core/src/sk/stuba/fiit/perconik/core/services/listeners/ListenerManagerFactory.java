package sk.stuba.fiit.perconik.core.services.listeners;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.core.services.ManagerFactory;

/**
 * The {@code ListenerManagerFactory}
 * creates {@link ListenerManager} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface ListenerManagerFactory extends ManagerFactory {
  /**
   * Creates a listener manager.
   */
  @Override
  public ListenerManager create();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(@Nullable Object o);
}
