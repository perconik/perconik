package sk.stuba.fiit.perconik.utilities.function;

import java.io.Serializable;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Numerates {
  private Numerates() {}

  private static final class NumerateFunction<T> implements Function<T, Integer>, Serializable {
    private static final long serialVersionUID = 0;

    private final Numerate<T> numerate;

    NumerateFunction(final Numerate<T> numerate) {
      this.numerate = checkNotNull(numerate);
    }

    @Override
    public Integer apply(@Nullable final T input) {
      return this.numerate.apply(input);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (o instanceof NumerateFunction) {
        NumerateFunction<?> other = (NumerateFunction<?>) o;

        return this.numerate.equals(other.numerate);
      }

      return false;
    }

    @Override
    public int hashCode() {
      return this.numerate.hashCode();
    }

    @Override
    public String toString() {
      return "forNumerate(" + this.numerate + ")";
    }
  }

  public static <T> Function<T, Integer> asFunction(final Numerate<T> numerate) {
    return new NumerateFunction<>(numerate);
  }
}
