package sk.stuba.fiit.perconik.utilities.configuration;

import com.google.common.reflect.TypeToken;

public interface OptionParser<T> {
  public T parse(Object object);

  public TypeToken<T> type();
}
