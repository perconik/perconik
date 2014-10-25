package sk.stuba.fiit.perconik.data;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.Joiner;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sk.stuba.fiit.perconik.data.content.AnyStructuredContent;
import sk.stuba.fiit.perconik.data.type.content.AnyStructuredContentDeserializer;
import sk.stuba.fiit.perconik.utilities.MoreMaps;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Iterators.concat;
import static com.google.common.collect.Iterators.singletonIterator;
import static com.google.common.collect.Lists.asList;
import static com.google.common.collect.Maps.newLinkedHashMap;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.sequence;
import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeCause;

public class AnyStructuredData extends AnyData implements AnyStructuredContent {
  public AnyStructuredData() {
    super(new Structure());
  }

  protected AnyStructuredData(final Map<String, Object> other) {
    super(Structure.from(other));
  }

  public static AnyStructuredData fromMap(final Map<String, Object> data) {
    return fromMap(AnyStructuredData.class, data);
  }

  public static AnyStructuredData fromString(final String data) {
    return fromString(AnyStructuredData.class, data);
  }

  public static AnyStructuredData of(final Map<String, Object> other) {
    return new AnyStructuredData(other);
  }

  static final class Structure extends ForwardingMap<String, Object> implements Serializable {
    private static final long serialVersionUID = -8851372460060747540L;

    final transient Map<String, Object> map;

    Structure() {
      this.map = newLinkedHashMap();
    }

    static Structure from(final Map<String, Object> map) {
      Structure structure = new Structure();

      structure.putAll(map);

      return structure;
    }

    @Override
    protected Map<String, Object> delegate() {
      return this.map;
    }

    private static Iterator<String> normalize(final Iterator<String> key) {
      checkState(key.hasNext());

      String component = key.next();

      checkArgument(!component.isEmpty());

      Iterator<String> components = sequence(component).iterator();

      return key.hasNext() ? concat(components, key) : components;
    }

    Object put(Iterator<String> key, final Object value) {
      key = normalize(key);

      String component = key.next();

      if (!key.hasNext()) {
        return this.map.put(component, value);
      }

      AnyStructuredData data;

      Object other = this.map.get(component);

      if (other instanceof AnyStructuredData) {
        data = (AnyStructuredData) other;
      } else {
        data = new AnyStructuredData();

        this.map.put(component, data);
      }

      return data.structure().put(key, value);
    }

    Object remove(Iterator<String> key) {
      key = normalize(key);

      String component = key.next();

      if (!key.hasNext()) {
        return this.map.remove(component);
      }

      Object value = this.map.get(component);

      if (value instanceof AnyStructuredData) {
        return ((AnyStructuredData) value).structure().remove(key);
      }

      return null;
    }

    boolean contains(Iterator<String> key) {
      key = normalize(key);

      String component = key.next();

      if (!key.hasNext()) {
        return this.map.containsKey(component);
      }

      Object value = this.map.get(component);

      if (value instanceof AnyStructuredData) {
        return ((AnyStructuredData) value).structure().contains(key);
      }

      return false;
    }

    Object get(Iterator<String> key) {
      key = normalize(key);

      Object value = this.map.get(key.next());

      if (!key.hasNext()) {
        return value;
      }

      if (value instanceof AnyStructuredData) {
        return ((AnyStructuredData) value).structure().get(key);
      }

      return null;
    }

    @Override
    public Object put(final String key, final Object value) {
      return this.put(singletonIterator(key), value);
    }

    @Override
    public void putAll(final Map<? extends String, ? extends Object> map) {
      this.standardPutAll(map);
    }

    @Override
    public Object remove(final Object key) {
      return this.remove(singletonIterator((String) key));
    }

    @Override
    public boolean containsKey(final Object key) {
      return this.contains(singletonIterator((String) key));
    }

    @Override
    public Object get(final Object key) {
      if (key instanceof String) {
        return this.get(singletonIterator((String) key));
      }

      return null;
    }

    private static final class SerializationProxy<E extends Enum<E>> implements Serializable {
      private static final long serialVersionUID = 7166925961646497798L;

      private final Map<String, Object> map;

      SerializationProxy(final Structure structure) {
        this.map = structure.map;
      }

      private Object readResolve() throws InvalidObjectException {
        try {
          return from(this.map);
        } catch (Exception e) {
          throw initializeCause(new InvalidObjectException("Unknown deserialization error"), e);
        }
      }
    }

    @SuppressWarnings({"static-method", "unused"})
    private void readObject(final ObjectInputStream in) throws InvalidObjectException {
      throw new InvalidObjectException("Serialization proxy required");
    }

    private Object writeReplace() {
      return new SerializationProxy<>(this);
    }
  }

  final Structure structure() {
    return (Structure) this.other;
  }

  public Map<String, Object> flatten() {
    return MoreMaps.flatten(this.toMap(), Joiner.on(separator), Maps.<String, Object>newLinkedHashMap());
  }

  @JsonAnySetter
  @JsonDeserialize(using = AnyStructuredContentDeserializer.class)
  @Override
  public void put(final String key, @Nullable final Object value) {
    this.other.put(key, value);
  }

  public void put(final Iterable<String> key, final Object value) {
    this.put(key.iterator(), value);
  }

  public void put(final Iterator<String> key, final Object value) {
    this.structure().put(key, value);
  }

  @Override
  public Object get(final String key) {
    return this.other.get(key);
  }

  public Object get(final String key, final String ... more) {
    return this.get(asList(key, more));
  }

  public Object get(final Iterable<String> key) {
    return this.get(key.iterator());
  }

  public Object get(final Iterator<String> key) {
    return this.structure().get(key);
  }
}
