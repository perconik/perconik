package sk.stuba.fiit.perconik.utilities.configuration;

import com.google.common.reflect.TypeToken;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractOptionParser<T> implements OptionParser<T> {
  protected final TypeToken<T> token;

  protected AbstractOptionParser(final Class<T> type) {
    this(TypeToken.of(type));
  }

  protected AbstractOptionParser(final TypeToken<T> token) {
    this.token = checkNotNull(token);
  }

  public final TypeToken<T> type() {
    return this.token;
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
