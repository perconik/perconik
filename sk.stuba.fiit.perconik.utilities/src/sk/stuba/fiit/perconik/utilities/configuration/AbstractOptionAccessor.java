package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

import static sk.stuba.fiit.perconik.utilities.MoreObjects.firstNonNullOrNull;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.newReader;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.newWriter;

public abstract class AbstractOptionAccessor<T> extends AbstractOptionMapping<T> implements OptionAccessor<T> {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractOptionAccessor(final String key, @Nullable final T defaultValue) {
    super(key, defaultValue);
  }

  protected abstract OptionParser<? extends T> parser();

  public Object putValue(final Options options, @Nullable final T value) {
    return this.putValue(newWriter(options), value);
  }

  public Object putValue(final OptionsWriter writer, @Nullable final T value) {
    return writer.put(this.parser(), this.key, value);
  }

  public Object putDefaultValue(final Options options) {
    return this.putDefaultValue(newWriter(options));
  }

  public Object putDefaultValue(final OptionsWriter writer) {
    return writer.put(this.parser(), this.key, this.defaultValue);
  }

  public Object putRawValue(final Options options, @Nullable final Object value) {
    return this.putRawValue(newWriter(options), value);
  }

  public Object putRawValue(final OptionsWriter writer, @Nullable final Object value) {
    return writer.putRaw(this.key, value);
  }

  public T getValue(final Options options) {
    return this.getValue(newReader(options));
  }

  public T getValue(final OptionsReader reader) {
    return firstNonNullOrNull(reader.get(this.parser(), this.key), this.defaultValue);
  }

  @Override
  public Object getRawValue(final Options options) {
    return this.getRawValue(newReader(options));
  }

  public Object getRawValue(final OptionsReader reader) {
    return firstNonNullOrNull(reader.getRaw(this.key), this.defaultValue);
  }

  public OptionMapping<T> toMapping() {
    return new SimpleOptionMapping<>(this.key, this.defaultValue);
  }
}
