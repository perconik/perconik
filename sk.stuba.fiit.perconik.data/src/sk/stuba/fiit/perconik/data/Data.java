package sk.stuba.fiit.perconik.data;

import java.util.Map;

import javax.annotation.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

import sk.stuba.fiit.perconik.data.bind.Mapper;
import sk.stuba.fiit.perconik.data.bind.NamingStrategy.LowerUnderscore;
import sk.stuba.fiit.perconik.data.bind.Writer;
import sk.stuba.fiit.perconik.data.content.Content;

import static com.google.common.collect.Maps.newTreeMap;

@JsonNaming(LowerUnderscore.class)
public class Data implements Content {
  public Data() {}

  public static final <T extends Data> T fromMap(final Class<T> type, final Map<String, Object> data) {
    try {
      return Mapper.getShared().convertValue(data, type);
    } catch (Exception e) {
      throw new DataException(e);
    }
  }

  public static final <T extends Data> T fromString(final Class<T> type, final String data) {
    try {
      return Mapper.getShared().readValue(data, type);
    } catch (Exception e) {
      throw new DataException(e);
    }
  }

  @Override
  public final boolean equals(@Nullable final Object o) {
    return o == this || o instanceof Data && ((Data) o).toMap().equals(this.toMap());
  }

  @Override
  public final int hashCode() {
    return this.toMap().hashCode();
  }

  public Map<String, Object> toMap() {
    try {
      return Mapper.getShared().convertValue(this, Mapper.getMapType());
    } catch (Exception e) {
      throw new DataException(e);
    }
  }

  public Map<String, Object> toMap(final boolean pretty) {
    if (!pretty) {
      return this.toMap();
    }

    Map<String, Object> map = newTreeMap();

    map.putAll(this.toMap());

    return map;
  }

  @Override
  public String toString() {
    try {
      return Mapper.getShared().writeValueAsString(this);
    } catch (Exception e) {
      throw new DataException(e);
    }
  }

  public String toString(final boolean pretty) {
    if (!pretty) {
      return this.toString();
    }

    try {
      return Writer.getPretty().writeValueAsString(this.toMap());
    } catch (Exception e) {
      throw new DataException(e);
    }
  }
}
