package sk.stuba.fiit.perconik.utilities.io;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryStream;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

public final class PathFilters {
  private PathFilters() {}

  public static <T> DirectoryStream.Filter<T> using(final Predicate<? super T> predicate) {
    return new PredicateFilter<>(predicate);
  }

  static final class PredicateFilter<T> implements DirectoryStream.Filter<T>, Serializable {
    private static final long serialVersionUID = 0L;

    private final Predicate<? super T> predicate;

    PredicateFilter(final Predicate<? super T> predicate) {
      this.predicate = checkNotNull(predicate);
    }

    public boolean accept(@Nullable final T entry) throws IOException {
      return this.predicate.apply(entry);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (o instanceof PredicateFilter) {
        PredicateFilter<?> other = (PredicateFilter<?>) o;

        return this.predicate.equals(other.predicate);
      }

      return false;
    }

    @Override
    public int hashCode() {
      return this.predicate.hashCode();
    }

    @Override
    public String toString() {
      return "Filters.using(" + this.predicate + ")";
    }
  }
}
