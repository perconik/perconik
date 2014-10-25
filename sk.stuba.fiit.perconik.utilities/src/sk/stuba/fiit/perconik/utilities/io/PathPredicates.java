package sk.stuba.fiit.perconik.utilities.io;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;

import javax.annotation.Nonnull;

import com.google.common.base.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;

public final class PathPredicates {
  private PathPredicates() {}

  private static <T extends Path> Predicate<T> cast(final Predicate<? extends Path> predicate) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    Predicate<T> result = (Predicate<T>) predicate;

    return result;
  }

  public static <T extends Path> Predicate<T> startsWith(final T prefix) {
    return new StartsWithPredicate<>(prefix);
  }

  public static <T extends Path> Predicate<T> endsWith(final T suffix) {
    return new EndsWithPredicate<>(suffix);
  }

  static final class StartsWithPredicate<T extends Path> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 0;

    private final T prefix;

    StartsWithPredicate(final T prefix) {
      this.prefix = checkNotNull(prefix);
    }

    public boolean apply(@Nonnull final T path) {
      return path != null ? path.startsWith(this.prefix) : false;
    }

    // TODO add equals, hashCode, toString
  }

  static final class EndsWithPredicate<T extends Path> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 0;

    private final T suffix;

    EndsWithPredicate(final T suffix) {
      this.suffix = checkNotNull(suffix);
    }

    public boolean apply(@Nonnull final T path) {
      return path != null ? path.endsWith(this.suffix) : false;
    }

    // TODO add equals, hashCode, toString
  }

  public static <T extends Path> Predicate<T> isDirectory() {
    return cast(IsDirectoryPredicate.Default.INSTANCE);
  }

  public static <T extends Path> Predicate<T> isDirectory(final LinkOption ... options) {
    return new IsDirectoryPredicate<>(options);
  }

  public static <T extends Path> Predicate<T> isRegularFile() {
    return cast(IsRegularFilePredicate.Default.INSTANCE);
  }

  public static <T extends Path> Predicate<T> isRegularFile(final LinkOption ... options) {
    return new IsRegularFilePredicate<>(options);
  }

  public static <T extends Path> Predicate<T> isExecutable() {
    return cast(IsExecutablePredicate.INSTANCE);
  }

  public static <T extends Path> Predicate<T> isReadable() {
    return cast(IsReadablePredicate.INSTANCE);
  }

  public static <T extends Path> Predicate<T> isWritable() {
    return cast(IsWritablePredicate.INSTANCE);
  }

  public static <T extends Path> Predicate<T> isHidden() {
    return cast(IsHiddenPredicate.INSTANCE);
  }

  public static <T extends Path> Predicate<T> isSymbolicLink() {
    return cast(IsSymbolicLinkPredicate.INSTANCE);
  }

  static final class IsDirectoryPredicate<T extends Path> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 0;

    private final LinkOption[] options;

    IsDirectoryPredicate(final LinkOption ... options) {
      this.options = Arrays.copyOf(options, options.length);
    }

    public boolean apply(@Nonnull final T path) {
      return Files.isDirectory(path, this.options);
    }

    enum Default implements Predicate<Path> {
      INSTANCE;

      public boolean apply(@Nonnull final Path path) {
        return Files.isDirectory(path);
      }

      // TODO add toString
    }

    // TODO add equals, hashCode, toString
  }

  static final class IsRegularFilePredicate<T extends Path> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 0;

    private final LinkOption[] options;

    IsRegularFilePredicate(final LinkOption ... options) {
      this.options = Arrays.copyOf(options, options.length);
    }

    public boolean apply(@Nonnull final T path) {
      return Files.isRegularFile(path, this.options);
    }

    enum Default implements Predicate<Path> {
      INSTANCE;

      public boolean apply(@Nonnull final Path path) {
        return Files.isRegularFile(path);
      }

      // TODO add toString
    }

    // TODO add equals, hashCode, toString
  }

  enum IsExecutablePredicate implements Predicate<Path> {
    INSTANCE;

    public boolean apply(@Nonnull final Path path) {
      return Files.isExecutable(path);
    }

    // TODO add toString
  }

  enum IsReadablePredicate implements Predicate<Path> {
    INSTANCE;

    public boolean apply(@Nonnull final Path path) {
      return Files.isReadable(path);
    }

    // TODO add toString
  }

  enum IsWritablePredicate implements Predicate<Path> {
    INSTANCE;

    public boolean apply(@Nonnull final Path path) {
      return Files.isWritable(path);
    }

    // TODO add toString
  }

  enum IsHiddenPredicate implements Predicate<Path> {
    INSTANCE;

    public boolean apply(@Nonnull final Path path) {
      try {
        return Files.isHidden(path);
      } catch (IOException e) {
        throw propagate(e);
      }
    }

    // TODO add toString
  }

  enum IsSymbolicLinkPredicate implements Predicate<Path> {
    INSTANCE;

    public boolean apply(@Nonnull final Path path) {
      return Files.isSymbolicLink(path);
    }

    // TODO add toString
  }
}
