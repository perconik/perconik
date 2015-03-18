package sk.stuba.fiit.perconik.data;

import java.util.Map;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import sk.stuba.fiit.perconik.data.type.content.AnyContentDeserializer;
import sk.stuba.fiit.perconik.data.type.content.AnyContentKeySerializer;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newLinkedHashMap;

public class AnyData extends Data {
  protected final Map<String, Object> other;

  public AnyData() {
    this.other = newLinkedHashMap();
  }

  protected AnyData(final Map<String, Object> other) {
    this.other = checkNotNull(other);
  }

  public static AnyData fromMap(final Map<String, Object> data) {
    return fromMap(AnyData.class, data);
  }

  public static AnyData fromString(final String data) {
    return fromString(AnyData.class, data);
  }

  public static AnyData of(final Map<String, Object> other) {
    return new AnyData(other);
  }

  @JsonAnyGetter
  @JsonSerialize(keyUsing = AnyContentKeySerializer.class)
  public Map<String, Object> any() {
    return this.other;
  }

  @JsonAnySetter
  @JsonDeserialize(using = AnyContentDeserializer.class)
  public void put(final String key, @Nullable final Object value) {
    this.other.put(key, value);
  }

  public Object get(final String key) {
    return this.other.get(key);
  }
}
