package sk.stuba.fiit.perconik.utilities.configuration;

import javax.annotation.Nullable;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.requireNonNullOrEmpty;

public abstract class AbstractOptionMapping<T> implements OptionMapping<T> {
  /**
   * Option key, non-empty string.
   */
  protected final String key;

  /**
   * Default value, may be {@code null}.
   */
  @Nullable
  protected final T defaultValue;

  /**
   * Constructor for use by subclasses.
   */
  protected AbstractOptionMapping(final String key, @Nullable final T defaultValue) {
    this.key = requireNonNullOrEmpty(key);
    this.defaultValue = defaultValue;
  }

  public final String getKey() {
    return this.key;
  }

  public final T getDefaultValue() {
    return this.defaultValue;
  }
}
