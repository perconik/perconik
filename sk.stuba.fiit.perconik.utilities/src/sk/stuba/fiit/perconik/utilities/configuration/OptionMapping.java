package sk.stuba.fiit.perconik.utilities.configuration;

import com.google.common.reflect.TypeToken;

public interface OptionMapping<T> {
  public TypeToken<T> getType();

  public String getKey();

  public T getDefaultValue();
}
