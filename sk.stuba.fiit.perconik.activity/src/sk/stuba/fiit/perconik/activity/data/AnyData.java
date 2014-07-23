package sk.stuba.fiit.perconik.activity.data;

import java.util.Map;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import sk.stuba.fiit.perconik.activity.data.bind.Deserializer;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newLinkedHashMap;

public class AnyData extends Data {
  protected final Map<String, Object> other;

  public AnyData() {
    this.other = newLinkedHashMap();
  }

  protected AnyData(Map<String, Object> other) {
    this.other = checkNotNull(other);
  }

  public static AnyData fromMap(Map<String, Object> data) {
    return fromMap(AnyData.class, data);
  }

  public static AnyData fromString(String data) {
    return fromString(AnyData.class, data);
  }

  public static AnyData of(Map<String, Object> other) {
    return new AnyData(other);
  }

  @JsonAnyGetter
  public Map<String, Object> any() {
    return this.other;
  }

  @JsonAnySetter
  @JsonDeserialize(using = Deserializer.class)
  public void set(String key, @Nullable Object value) {
    this.other.put(key, value);
  }

  public Object get(String key) {
    return this.other.get(key);
  }
}
