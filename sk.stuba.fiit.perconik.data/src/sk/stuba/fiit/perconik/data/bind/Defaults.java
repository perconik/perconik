package sk.stuba.fiit.perconik.data.bind;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public final class Defaults {
  /**
   * Default map type set to {@code LinkedHashMap<String, Object>}.
   */
  public static final JavaType MAP_TYPE;

  /**
   * Default time format set to ISO-8601.
   */
  public static final String TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

  static {
    TypeFactory defaultTypeFactory = TypeFactory.defaultInstance();

    MAP_TYPE = defaultTypeFactory.constructMapType(LinkedHashMap.class, String.class, Object.class);
  }

  private Defaults() {}
}
