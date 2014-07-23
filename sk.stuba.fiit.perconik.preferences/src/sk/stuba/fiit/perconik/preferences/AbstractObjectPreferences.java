package sk.stuba.fiit.perconik.preferences;

import sk.stuba.fiit.perconik.utilities.io.Serialization;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractObjectPreferences extends AbstractPreferences {
  final ClassResolver resolver;

  public AbstractObjectPreferences(final Scope scope, final String qualifier, final ClassResolver resolver) {
    super(scope, qualifier);

    this.resolver = checkNotNull(resolver);
  }

  static final Object fromBytesOrFailure(final String key, final byte[] value, final ClassResolver resolver) {
    try {
      return Serialization.fromBytes(value, resolver);
    } catch (Exception e) {
      throw new RuntimeException("Unable to read object under key " + key + " from byte array", e);
    }
  }

  static final byte[] toBytesOrFailure(final String key, final Object value) {
    try {
      return Serialization.toBytes(value);
    } catch (Exception e) {
      throw new RuntimeException("Unable to write object under key " + key + " to byte array", e);
    }
  }

  protected final void putObject(final String key, final Object value) {
    this.data.putByteArray(key, toBytesOrFailure(key, value));
  }

  protected final Object getObject(final String key) {
    return fromBytesOrFailure(key, this.data.getByteArray(key, null), this.resolver);
  }
}
