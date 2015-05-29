package sk.stuba.fiit.perconik.utilities;

import java.io.Serializable;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Exceptional<T> implements Serializable {
  private static final long serialVersionUID = 0L;

  Exceptional() {}

  public static final <T> Exceptional<T> of(final T reference) {
    return new Success<>(reference);
  }

  public static final <T> Exceptional<T> failure(final Throwable reference) {
    return new Failure<>(reference);
  }

  private static final class Success<T> extends Exceptional<T> {
    private static final long serialVersionUID = 0L;

    private final T reference;

    Success(final T reference) {
      this.reference = reference;
    }

    @Override
    public T or(final T other) {
      checkNotNull(other);

      return this.reference;
    }

    @Override
    public Exceptional<T> or(final Exceptional<T> other) {
      checkNotNull(other);

      return this;
    }

    @Override
    public T orNull() {
      return this.reference;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
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
    public int hashCode() {
      return 0x598df91c + this.reference.hashCode();
    }

    @Override
    public String toString() {
      return "Exceptional.of(" + this.reference + ")";
    }

    @Override
    public Optional<T> toOptional() {
      return Optional.of(this.reference);
    }

    @Override
    public T get() {
      return this.reference;
    }

    @Override
    public Throwable failure() {
      throw new IllegalStateException();
    }

    @Override
    public boolean isSuccess() {
      return true;
    }

    @Override
    public boolean isFailure() {
      return false;
    }
  }

  private static final class Failure<T> extends Exceptional<T> {
    private static final long serialVersionUID = 0L;

    private final Throwable reference;

    Failure(final Throwable reference) {
      this.reference = reference;
    }

    @Override
    public T or(final T other) {
      return checkNotNull(other);
    }

    @Override
    public Exceptional<T> or(final Exceptional<T> other) {
      return checkNotNull(other);
    }

    @Override
    public T orNull() {
      return null;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
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
    public int hashCode() {
      return 0x598df91c + this.reference.hashCode();
    }

    @Override
    public String toString() {
      return "Exceptional.of(" + this.reference + ")";
    }

    @Override
    public Optional<T> toOptional() {
      return Optional.absent();
    }

    @Override
    public T get() {
      throw new IllegalStateException();
    }

    @Override
    public Throwable failure() {
      return this.reference;
    }

    @Override
    public boolean isSuccess() {
      return false;
    }

    @Override
    public boolean isFailure() {
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
