package sk.stuba.fiit.perconik.core;

import java.util.Set;

/**
 * A listener capable of filtering events by specified type.
 * 
 * @param <T> the type of acceptable events 
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface FilteringListener<T> extends Listener {
  /**
   * Gets accepted event types.
   * @return accepted event types
   */
  public Set<T> getEventTypes();
}
