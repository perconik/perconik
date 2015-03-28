package sk.stuba.fiit.perconik.utilities.configuration;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.ForwardingMap;

import static com.google.common.base.Preconditions.checkNotNull;

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
    this.map = checkNotNull(map);
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
  public abstract Object put(final String key, @Nullable final Object value);

  @Override
  public void putAll(final Map<? extends String, ?> map) {
    this.standardPutAll(map);
  }

  public Object get(final String key) {
    return this.map.get(key);
  }
}
