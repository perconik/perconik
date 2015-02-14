package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

public interface OptionAccessor<T> extends OptionMapping<T> {
  public Object putValue(Options options, @Nullable T value);

  public Object putValue(OptionsWriter writer, @Nullable T value);

  public Object putDefaultValue(Options options);

  public Object putDefaultValue(OptionsWriter writer);

  public Object putRawValue(Options options, @Nullable Object value);

  public Object putRawValue(OptionsWriter writer, @Nullable Object value);

  public T getValue(Options options);

  public T getValue(OptionsReader reader);

  public Object getRawValue(Options options);

  public Object getRawValue(OptionsReader reader);

  public OptionMapping<T> toMapping();
}
