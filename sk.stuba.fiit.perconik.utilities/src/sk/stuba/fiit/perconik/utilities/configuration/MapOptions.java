package sk.stuba.fiit.perconik.utilities.configuration;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.ImmutableMap.copyOf;
import static com.google.common.collect.Maps.newLinkedHashMap;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class MapOptions extends AbstractMapOptions implements Serializable {
  private static final long serialVersionUID = -6372082702258295853L;

  final transient Putter putter;

  MapOptions(final Map<String, Object> map, final Putter putter) {
    super(map);

    this.putter = requireNonNull(putter);
  }

  public static MapOptions from(final Map<String, Object> map) {
    return from(map, StandardPutter.instance);
  }

  public static MapOptions from(final Map<String, Object> map, final Putter putter) {
    if (map instanceof ImmutableMap) {
      return new Immutable(map, putter);
    }

    return new Regular(map, putter);
  }

  public static MapOptions view(final Map<String, Object> map) {
    return view(map, StandardPutter.instance);
  }

  public static MapOptions view(final Map<String, Object> map, final Putter putter) {
    if (map instanceof ImmutableMap) {
      return new Immutable(map, putter);
    }

    return new View(map, putter);
  }

  private static final class Regular extends MapOptions {
    private static final long serialVersionUID = 1704716560859767601L;

    Regular(final Map<String, Object> map, final Putter putter) {
      super(newLinkedHashMap(map), putter);
    }

    @Override
    public Object put(final String key, final Object value) {
      return this.putter.put(this, key, value);
    }

    public Map<String, Object> toMap() {
      return newLinkedHashMap(this);
    }

    private static final class SerializationProxy extends AbstractSerializationProxy<Regular> {
      private static final long serialVersionUID = -7140194869918852030L;

      SerializationProxy(final Regular options) {
        super(options);
      }

      @Override
      Regular construct(final Map<String, Object> map, final Putter putter) {
        return new Regular(map, putter);
      }
    }

    @Override
    Object writeReplace() {
      return new SerializationProxy(this);
    }
  }

  private static final class Immutable extends MapOptions {
    private static final long serialVersionUID = -3797590867166061284L;

    Immutable(final Map<String, Object> map, final Putter putter) {
      super(copyOf(map), putter);
    }

    @Override
    public Object put(final String key, final Object value) {
      throw new UnsupportedOperationException();
    }

    public Map<String, Object> toMap() {
      return this;
    }

    private static final class SerializationProxy extends AbstractSerializationProxy<Immutable> {
      private static final long serialVersionUID = -2172898611082615023L;

      SerializationProxy(final Immutable options) {
        super(options);
      }

      @Override
      Immutable construct(final Map<String, Object> map, final Putter putter) {
        return new Immutable(map, putter);
      }
    }

    @Override
    Object writeReplace() {
      return new SerializationProxy(this);
    }
  }

  private static final class View extends MapOptions {
    private static final long serialVersionUID = 1031140282957202002L;

    View(final Map<String, Object> map, final Putter putter) {
      super(map, putter);
    }

    @Override
    public Object put(final String key, final Object value) {
      return this.putter.put(this, key, value);
    }

    public Map<String, Object> toMap() {
      return this;
    }

    private static final class SerializationProxy extends AbstractSerializationProxy<View> {
      private static final long serialVersionUID = 5730743428732201474L;

      SerializationProxy(final View options) {
        super(options);
      }

      @Override
      View construct(final Map<String, Object> map, final Putter putter) {
        return new View(map, putter);
      }
    }

    @Override
    Object writeReplace() {
      return new SerializationProxy(this);
    }
  }

  public interface Putter {
    public Object put(final Map<String, Object> map, final String key, final Object value);
  }

  private enum StandardPutter implements Putter {
    instance;

    public Object put(final Map<String, Object> map, final String key, final Object value) {
      requireNonNull(map);

      try {
        return map.put(key, value);
      } catch (RuntimeException e) {
        throw new IllegalOptionException(e);
      }
    }
  }

  static abstract class AbstractSerializationProxy<T extends MapOptions> implements Serializable {
    private static final long serialVersionUID = 8169680327325018850L;

    private final Map<String, Object> map;

    private final Putter putter;

    AbstractSerializationProxy(final T options) {
      this.map = options.map;
      this.putter = options.putter;
    }

    abstract T construct(Map<String, Object> map, Putter putter);

    final Object readResolve() throws InvalidObjectException {
      try {
        return this.construct(this.map, this.putter);
      } catch (Exception e) {
        throw new InvalidObjectException("Unknown deserialization error");
      }
    }
  }

  @SuppressWarnings({"static-method", "unused"})
  final void readObject(final ObjectInputStream in) throws InvalidObjectException {
    throw new InvalidObjectException("Serialization proxy required");
  }

  abstract Object writeReplace();
}
