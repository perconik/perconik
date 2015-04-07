package sk.stuba.fiit.perconik.preferences;

import com.google.common.base.Objects.ToStringHelper;

import sk.stuba.fiit.perconik.utilities.io.Serialization;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

import static java.lang.String.format;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.firstNonNullOrEmpty;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toDefaultString;

public abstract class AbstractObjectPreferences extends AbstractPreferences {
  final ClassResolver resolver;

  public AbstractObjectPreferences(final Scope scope, final String qualifier, final ClassResolver resolver) {
    super(scope, qualifier);

    this.resolver = checkNotNull(resolver);
  }

  static final Object fromBytes(final String key, final byte[] value, final ClassResolver resolver) {
    try {
      return Serialization.fromBytes(value, resolver);
    } catch (Exception e) {
      throw new RuntimeException(format("Unable to read object under key %s from byte array", key), e);
    }
  }

  static final byte[] toBytes(final String key, final Object value) {
    try {
      return Serialization.toBytes(value);
    } catch (Exception e) {
      throw new RuntimeException(format("Unable to write object %s under key %s to byte array", toDefaultString(value), key), e);
    }
  }

  protected static final String toString(final Throwable failure) {
    return firstNonNullOrEmpty(failure.getMessage(), "Unknown serialization error");
  }

  protected final void putObject(final String key, final Object value) {
    this.data.putByteArray(key, toBytes(key, value));
  }

  protected final Object getObject(final String key) {
    return fromBytes(key, this.data.getByteArray(key, null), this.resolver);
  }

  @Override
  protected ToStringHelper toStringHelper() {
    return super.toStringHelper().add("resolver", this.resolver);
  }
}
