package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

public interface OptionsWriter {
  public Object putAsBoolean(String key, @Nullable Object value);

  public Object putAsByte(String key, @Nullable Object value);

  public Object putAsShort(String key, @Nullable Object value);

  public Object putAsInteger(String key, @Nullable Object value);

  public Object putAsLong(String key, @Nullable Object value);

  public Object putAsUnsignedInteger(String key, @Nullable Object value);

  public Object putAsUnsignedLong(String key, @Nullable Object value);

  public Object putAsFloat(String key, @Nullable Object value);

  public Object putAsDouble(String key, @Nullable Object value);

  public Object putAsCharacter(String key, @Nullable Object value);

  public Object putAsString(String key, @Nullable Object value);
}
