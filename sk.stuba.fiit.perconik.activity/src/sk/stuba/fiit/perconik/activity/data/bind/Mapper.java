package sk.stuba.fiit.perconik.activity.data.bind;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import sk.stuba.fiit.perconik.activity.data.type.BaseModule;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

public final class Mapper {
  private static final ObjectMapper shared;

  static {
    shared = new ObjectMapper();

    shared.registerModule(new BaseModule());
    shared.registerModule(new GuavaModule());

    shared.setPropertyNamingStrategy(new Naming());
    shared.setVisibility(FIELD, NONE);

    shared.disable(FAIL_ON_EMPTY_BEANS);
  }

  private Mapper() {
    throw new AssertionError();
  }

  public static final ObjectMapper getShared() {
    return shared;
  }
}
