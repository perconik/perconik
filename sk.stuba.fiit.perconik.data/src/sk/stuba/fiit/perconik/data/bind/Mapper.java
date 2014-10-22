package sk.stuba.fiit.perconik.data.bind;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import sk.stuba.fiit.perconik.data.bind.NamingStrategy.LowerUnderscore;
import sk.stuba.fiit.perconik.data.type.common.CommonModule;
import sk.stuba.fiit.perconik.data.type.content.ContentModule;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import static sk.stuba.fiit.perconik.data.bind.Defaults.MAP_TYPE;
import static sk.stuba.fiit.perconik.data.bind.Defaults.TIME_PATTERN;

public final class Mapper {
  private static final ObjectMapper sharedInstance;

  static {
    sharedInstance = new ObjectMapper();

    sharedInstance.registerModule(new CommonModule());
    sharedInstance.registerModule(new ContentModule());
    sharedInstance.registerModule(new GuavaModule());

    sharedInstance.setPropertyNamingStrategy(new LowerUnderscore());
    sharedInstance.setVisibility(FIELD, NONE);

    sharedInstance.setDateFormat(new SimpleDateFormat(TIME_PATTERN));
    sharedInstance.setTimeZone(TimeZone.getDefault());

    sharedInstance.disable(FAIL_ON_EMPTY_BEANS);
    sharedInstance.disable(WRITE_DATES_AS_TIMESTAMPS);
  }

  private Mapper() {}

  public static JavaType getMapType() {
    return MAP_TYPE;
  }

  public static ObjectMapper getShared() {
    return sharedInstance;
  }
}
