package sk.stuba.fiit.perconik.core;

import java.util.Map;

/**
 * A listener capable of counting events. The events are identified
 * by instances of implementations of {@link Key} interface.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface CountingListener extends Listener {
  /**
   * Gets all event counts in a key to count map.
   * @return key to event count map
   */
  public Map<Key, Long> getCounts();

  /**
   * Gets event count for given key.
   * @param key event key
   * @return event count
   */
  public long getCount(Key key);

  /**
   * Marker interface for event identification.
   *
   * @author Pavol Zbell
   * @since 1.0
   */
  public static interface Key {
  }
}
