package sk.stuba.fiit.perconik.utilities;

import java.io.Serializable;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Exceptional<T> implements Serializable {
  private static final long serialVersionUID = 0;

  Exceptional() {}

  public static final <T> Exceptional<T> of(T reference) {
    return new Success<>(reference);
  }

  public static final <T> Exceptional<T> failure(Throwable reference) {
    return new Failure<>(reference);
  }

  private static final class Success<T> extends Exceptional<T> {
    private static final long serialVersionUID = 0;

    private final T reference;

    Success(T reference) {
      this.reference = reference;
    }

    @Override
    public final T or(T other) {
      checkNotNull(other);

      return this.reference;
    }

    @Override
    public final Exceptional<T> or(Exceptional<T> other) {
      checkNotNull(other);

      return this;
    }

    @Override
    public final T orNull() {
      return this.reference;
    }

    @Override
    public final boolean equals(@Nullable Object o) {
      if (o == this) {
        return true;
      }

      if (!(o instanceof Success)) {
        return false;
      }

      Success<?> other = (Success<?>) o;

      return this.reference.equals(other.reference);
    }

    @Override
    public final int hashCode() {
      return 0x598df91c + this.reference.hashCode();
    }

    @Override
    public final String toString() {
      return "Exceptional.of(" + this.reference + ")";
    }

    @Override
    public final Optional<T> toOptional() {
      return Optional.of(this.reference);
    }

    @Override
    public final T get() {
      return this.reference;
    }

    @Override
    public final Throwable failure() {
      throw new IllegalStateException();
    }

    @Override
    public final boolean isSuccess() {
      return true;
    }

    @Override
    public final boolean isFailure() {
      return false;
    }
  }

  private static final class Failure<T> extends Exceptional<T> {
    private static final long serialVersionUID = 0;

    private final Throwable reference;

    Failure(Throwable reference) {
      this.reference = reference;
    }

    @Override
    public final T or(T other) {
      return checkNotNull(other);
    }

    @Override
    public final Exceptional<T> or(Exceptional<T> other) {
      return checkNotNull(other);
    }

    @Override
    public final T orNull() {
      return null;
    }

    @Override
    public final boolean equals(@Nullable Object o) {
      if (o == this) {
        return true;
      }

      if (!(o instanceof Failure)) {
        return false;
      }

      Failure<?> other = (Failure<?>) o;

      return this.reference.equals(other.reference);
    }

    @Override
    public final int hashCode() {
      return 0x598df91c + this.reference.hashCode();
    }

    @Override
    public final String toString() {
      return "Exceptional.of(" + this.reference + ")";
    }

    @Override
    public final Optional<T> toOptional() {
      return Optional.absent();
    }

    @Override
    public final T get() {
      throw new IllegalStateException();
    }

    @Override
    public final Throwable failure() {
      return this.reference;
    }

    @Override
    public final boolean isSuccess() {
      return false;
    }

    @Override
    public final boolean isFailure() {
      return true;
    }
  }

  public abstract T or(T other);

  public abstract Exceptional<T> or(Exceptional<T> other);

  @Nullable
  public abstract T orNull();

  @Override
  public abstract boolean equals(@Nullable Object object);

  @Override
  public abstract int hashCode();

  @Override
  public abstract String toString();

  public abstract Optional<T> toOptional();

  public abstract T get();

  public abstract Throwable failure();

  public abstract boolean isSuccess();

  public abstract boolean isFailure();
}
