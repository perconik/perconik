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
   * Loads options from map to this options instance (optional operation).
   *
   * <p>Clears all present options and load contents only from specified map.
   *
   * <p>Note that the implementation specifies whether
   * this method extracts map entries or provides a view of the map.
   *
   * @throws UnsupportedOperationException if the options
   *         instance does not support loading values
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

  /**
   * Associates the specified value with the specified key in this options
   * instance (optional operation).
   *
   * @param key key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @return the previous value associated with {@code key}, or {@code null} by default
   *
   * @throws IllegalOptionException if some property of the {@code key} or
   *         {@code value} prevents it from being stored in this options instance
   * @throws NullPointerException if the {@code key} is {@code null}
   * @throws UnsupportedOperationException if the options
   *         instance does not support putting new values
   */
  public Object put(String key, Object value);

  /**
   * Returns the value to which the specified key is mapped, or {@code null} by default.
   *
   * @param key the key whose associated value is to be returned
   * @return the value to which the specified key is mapped, or {@code null} by default
   *
   * @throws NullPointerException if the {@code key} is {@code null}
   */
  public Object get(String key);
}
