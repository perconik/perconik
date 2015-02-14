package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

public interface OptionsWriter {
  public <T> Object put(OptionParser<? extends T> parser, String key, @Nullable Object value);

  public Object putRaw(String key, @Nullable Object value);
}
