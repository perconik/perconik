package sk.stuba.fiit.perconik.utilities.configuration;

import java.util.Map;

import com.google.common.collect.ForwardingMap;

import static java.util.Objects.requireNonNull;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractMapOptions extends ForwardingMap<String, Object> implements Options {
  final transient Map<String, Object> map;

  /**
   * Constructor for use by subclasses.
   */
  protected AbstractMapOptions(final Map<String, Object> map) {
    this.map = requireNonNull(map);
  }

  /**
   * Underlying map of options.
   */
  @Override
  protected final Map<String, Object> delegate() {
    return this.map;
  }

  public void fromMap(final Map<String, Object> map) {
    this.clear();
    this.putAll(map);
  }

  @Override
  public abstract Object put(final String key, final Object value);

  @Override
  public void putAll(final Map<? extends String, ?> map) {
    this.standardPutAll(map);
  }

  public Object get(final String key) {
    return this.map.get(key);
  }
}
