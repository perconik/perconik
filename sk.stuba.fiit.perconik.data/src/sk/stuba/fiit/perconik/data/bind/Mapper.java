package sk.stuba.fiit.perconik.data.bind;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import sk.stuba.fiit.perconik.data.bind.NamingStrategy.LowerUnderscore;
import sk.stuba.fiit.perconik.data.type.common.CommonModule;
import sk.stuba.fiit.perconik.data.type.content.ContentModule;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

public final class Mapper {
  private static final JavaType defaultMapType;

  private static final ObjectMapper sharedInstance;

  static {
    TypeFactory defaultTypeFactory = TypeFactory.defaultInstance();

    defaultMapType = defaultTypeFactory.constructMapType(LinkedHashMap.class, String.class, Object.class);

    sharedInstance = new ObjectMapper();

    sharedInstance.registerModule(new CommonModule());
    sharedInstance.registerModule(new ContentModule());
    sharedInstance.registerModule(new GuavaModule());

    sharedInstance.setPropertyNamingStrategy(new LowerUnderscore());
    sharedInstance.setVisibility(FIELD, NONE);

    sharedInstance.disable(FAIL_ON_EMPTY_BEANS);
  }

  private Mapper() {}

  public static JavaType getMapType() {
    return defaultMapType;
  }

  public static ObjectMapper getShared() {
    return sharedInstance;
  }
}
