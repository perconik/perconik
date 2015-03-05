package sk.stuba.fiit.perconik.utilities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Optional;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Optional.of;

public final class Optionals {
  private Optionals() {}

  private enum FromNonnullFunction implements Function<Object, Optional<?>> {
    INSTANCE;

    public Optional<?> apply(@Nonnull final Object reference) {
      return of(reference);
    }
  }

  private enum FromNullableFunction implements Function<Object, Optional<?>> {
    INSTANCE;

    public Optional<?> apply(@Nullable final Object reference) {
      return fromNullable(reference);
    }
  }

  public static <U extends T, T> Function<U, Optional<T>> fromNonnullFunction() {
    return Function.class.cast(FromNonnullFunction.INSTANCE);
  }

  public static <U extends T, T> Function<U, Optional<T>> fromNullableFunction() {
    return Function.class.cast(FromNullableFunction.INSTANCE);
  }
}
