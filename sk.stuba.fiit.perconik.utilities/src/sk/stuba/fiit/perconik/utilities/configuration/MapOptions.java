package sk.stuba.fiit.perconik.utilities.configuration;

import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MapOptions extends AbstractOptions {
  private final Putter putter;

  public MapOptions(final Map<String, Object> map) {
    this(map, StandardPutter.instance);
  }

  public MapOptions(final Map<String, Object> map, final Putter putter) {
    super(map);

    this.putter = requireNonNull(putter);
  }

  public interface Putter {
    public Object put(final Map<String, Object> map, final String key, final Object value);
  }

  private enum StandardPutter implements Putter {
    instance;

    public Object put(final Map<String, Object> map, final String key, final Object value) {
      return map.put(key, value);
    }
  }

  @Override
  public Object put(final String key, final Object value) {
    return this.putter.put(this, key, value);
  }
}
