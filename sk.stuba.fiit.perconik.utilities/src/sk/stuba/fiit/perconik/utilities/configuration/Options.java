package sk.stuba.fiit.perconik.utilities.configuration;

import java.util.Map;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Options {
  /**
   * Loads options from map to this options instance.
   *
   * <p>Note that the implementation specifies whether
   * this method extracts map entries or provides a view of the map.
   *
   * @throws UnsupportedOperationException if the options
   *         does not support loading new values
   */
  public void fromMap(Map<String, Object> map);

  /**
   * Converts this options instance to map.
   *
   * <p>Note that the implementation specifies whether
   * this method returns a snapshot or view of options.
   *
   * @return either a snapshot or view of options
   */
  public Map<String, Object> toMap();
}
