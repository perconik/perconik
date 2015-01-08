package sk.stuba.fiit.perconik.utilities.configuration;

import java.util.Map;

import com.google.common.collect.ForwardingMap;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractOptions extends ForwardingMap<String, Object> implements Options {
  protected final transient Map<String, Object> map;

  protected AbstractOptions(final Map<String, Object> map) {
    this.map = map;
  }

  @Override
  protected final Map<String, Object> delegate() {
    return this.map;
  }

  @Override
  public abstract Object put(final String key, final Object value);

  @Override
  public void putAll(final Map<? extends String, ?> map) {
    this.standardPutAll(map);
  }

  public void fromMap(final Map<String, Object> map) {
    this.putAll(map);
  }
}
