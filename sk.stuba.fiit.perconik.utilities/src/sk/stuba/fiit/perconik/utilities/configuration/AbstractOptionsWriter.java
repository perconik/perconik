package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

public abstract class AbstractOptionsWriter implements OptionsWriter {
  protected AbstractOptionsWriter() {}

  protected abstract Options options();

  protected abstract OptionParser parser();

  public Object putAsBoolean(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseBoolean(value) : null);
  }

  public Object putAsByte(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseByte(value) : null);
  }

  public Object putAsShort(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseShort(value) : null);
  }

  public Object putAsInteger(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseInteger(value) : null);
  }

  public Object putAsLong(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseLong(value) : null);
  }

  public Object putAsUnsignedInteger(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseUnsignedInteger(value) : null);
  }

  public Object putAsUnsignedLong(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseUnsignedLong(value) : null);
  }

  public Object putAsFloat(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseFloat(value) : null);
  }

  public Object putAsDouble(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseDouble(value) : null);
  }

  public Object putAsCharacter(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseCharacter(value) : null);
  }

  public Object putAsString(final String key, @Nullable final Object value) {
    return this.options().put(key, value != null ? this.parser().parseString(value) : null);
  }
}
