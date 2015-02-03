package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;

public interface OptionsReader {
  public Boolean getAsBoolean(String key);

  public Boolean getAsBoolean(String key, @Nullable Object defaultValue);

  public Byte getAsByte(String key);

  public Byte getAsByte(String key, @Nullable Object defaultValue);

  public Short getAsShort(String key);

  public Short getAsShort(String key, @Nullable Object defaultValue);

  public Integer getAsInteger(String key);

  public Integer getAsInteger(String key, @Nullable Object defaultValue);

  public Long getAsLong(String key);

  public Long getAsLong(String key, @Nullable Object defaultValue);

  public UnsignedInteger getAsUnsignedInteger(String key);

  public UnsignedInteger getAsUnsignedInteger(String key, @Nullable Object defaultValue);

  public UnsignedLong getAsUnsignedLong(String key);

  public UnsignedLong getAsUnsignedLong(String key, @Nullable Object defaultValue);

  public Float getAsFloat(String key);

  public Float getAsFloat(String key, @Nullable Object defaultValue);

  public Double getAsDouble(String key);

  public Double getAsDouble(String key, @Nullable Object defaultValue);

  public Character getAsCharacter(String key);

  public Character getAsCharacter(String key, @Nullable Object defaultValue);

  public String getAsString(String key);

  public String getAsString(String key, @Nullable Object defaultValue);
}
