package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;

public abstract class AbstractOptionsReader implements OptionsReader {
  protected AbstractOptionsReader() {}

  protected abstract Options options();

  protected abstract OptionParser parser();

  protected final Object getOrDefault(final String key, @Nullable final Object defaultValue) {
    Object value = this.options().get(key);

    return value != null ? value : defaultValue;
  }

  public Boolean getAsBoolean(final String key) {
    return this.getAsBoolean(key, null);
  }

  public Boolean getAsBoolean(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseBoolean(value) : null;
  }

  public Byte getAsByte(final String key) {
    return this.getAsByte(key, null);
  }

  public Byte getAsByte(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseByte(value) : null;
  }

  public Short getAsShort(final String key) {
    return this.getAsShort(key, null);
  }

  public Short getAsShort(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseShort(value) : null;
  }

  public Integer getAsInteger(final String key) {
    return this.getAsInteger(key, null);
  }

  public Integer getAsInteger(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseInteger(value) : null;
  }

  public Long getAsLong(final String key) {
    return this.getAsLong(key, null);
  }

  public Long getAsLong(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseLong(value) : null;
  }

  public UnsignedInteger getAsUnsignedInteger(final String key) {
    return this.getAsUnsignedInteger(key, null);
  }

  public UnsignedInteger getAsUnsignedInteger(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseUnsignedInteger(value) : null;
  }

  public UnsignedLong getAsUnsignedLong(final String key) {
    return this.getAsUnsignedLong(key, null);
  }

  public UnsignedLong getAsUnsignedLong(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseUnsignedLong(value) : null;
  }

  public Float getAsFloat(final String key) {
    return this.getAsFloat(key, null);
  }

  public Float getAsFloat(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseFloat(value) : null;
  }

  public Double getAsDouble(final String key) {
    return this.getAsDouble(key, null);
  }

  public Double getAsDouble(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseDouble(value) : null;
  }

  public Character getAsCharacter(final String key) {
    return this.getAsCharacter(key, null);
  }

  public Character getAsCharacter(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseCharacter(value) : null;
  }

  public String getAsString(final String key) {
    return this.getAsString(key, null);
  }

  public String getAsString(final String key, @Nullable final Object defaultValue) {
    Object value = this.getOrDefault(key, defaultValue);

    return value != null ? this.parser().parseString(value) : null;
  }
}
