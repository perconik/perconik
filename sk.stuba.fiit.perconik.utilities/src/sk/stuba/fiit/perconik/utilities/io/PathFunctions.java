package sk.stuba.fiit.perconik.utilities.io;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Function;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;

public final class PathFunctions {
  private PathFunctions() {}

  private static <T extends Path> Function<T, T> castWhole(final Function<? extends Path, ? extends Path> function) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    Function<T, T> result = (Function<T, T>) function;

    return result;
  }

  private static <T extends Path, R> Function<T, R> castInput(final Function<? extends Path, R> function) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings("unchecked")
    Function<T, R> result = (Function<T, R>) function;

    return result;
  }

  public static <T extends Path> Function<T, T> normalize() {
    return castWhole(NormalizeFunction.INSTANCE);
  }

  public static <T extends Path> Function<T, T> relativize(final T path) {
    return castWhole(new RelativizeFunction(path));
  }

  public static <T extends Path> Function<T, T> resolve(final T path) {
    return castWhole(new ResolveFunction(path));
  }

  public static <T extends Path> Function<T, T> resolveSibling(final T path) {
    return castWhole(new ResolveSiblingFunction(path));
  }

  enum NormalizeFunction implements Function<Path, Path> {
    INSTANCE;

    public Path apply(@Nonnull final Path path) {
      return path.normalize();
    }

    // TODO add toString
  }

  static final class RelativizeFunction implements Function<Path, Path>, Serializable {
    private static final long serialVersionUID = 0L;

    final Path path;

    public RelativizeFunction(final Path path) {
      this.path = checkNotNull(path);
    }

    public Path apply(@Nullable final Path path) {
      return path != null ? path.relativize(this.path) : null;
    }

    // TODO add equals, hashCode, toString
  }

  static final class ResolveFunction implements Function<Path, Path>, Serializable {
    private static final long serialVersionUID = 0L;

    final Path path;

    public ResolveFunction(final Path path) {
      this.path = checkNotNull(path);
    }

    public Path apply(@Nullable final Path path) {
      return path != null ? path.resolve(this.path) : null;
    }

    // TODO add equals, hashCode, toString
  }

  static final class ResolveSiblingFunction implements Function<Path, Path>, Serializable {
    private static final long serialVersionUID = 0L;

    final Path path;

    public ResolveSiblingFunction(final Path path) {
      this.path = checkNotNull(path);
    }

    public Path apply(@Nullable final Path path) {
      return path != null ? path.resolveSibling(this.path) : null;
    }

    // TODO add equals, hashCode, toString
  }

  public static <T extends Path> Function<T, T> name(final int index) {
    return castWhole(new NameFunction(index));
  }

  public static <T extends Path> Function<T, Integer> nameCount() {
    return castInput(NameCountFunction.INSTANCE);
  }

  static final class NameFunction implements Function<Path, Path>, Serializable {
    private static final long serialVersionUID = 0L;

    private final int index;

    NameFunction(final int index) {
      checkArgument(index >= 0);

      this.index = index;
    }

    public Path apply(@Nullable final Path path) {
      return path != null ? path.getName(this.index) : null;
    }

    // TODO add equals, hashCode, toString
  }

  enum NameCountFunction implements Function<Path, Integer> {
    INSTANCE;

    public Integer apply(@Nonnull final Path path) {
      return path.getNameCount();
    }

    // TODO add toString
  }

  public static <T extends Path> Function<T, T> root() {
    return castWhole(RootFunction.INSTANCE);
  }

  public static <T extends Path> Function<T, T> parent() {
    return castWhole(ParentFunction.INSTANCE);
  }

  public static <T extends Path> Function<T, T> fileName() {
    return castWhole(FileNameFunction.INSTANCE);
  }

  public static <T extends Path> Function<T, FileSystem> fileSystem() {
    return castInput(FileSystemFunction.INSTANCE);
  }

  enum RootFunction implements Function<Path, Path> {
    INSTANCE;

    public Path apply(@Nonnull final Path path) {
      return path.getRoot();
    }

    // TODO add toString
  }

  enum ParentFunction implements Function<Path, Path> {
    INSTANCE;

    public Path apply(@Nonnull final Path path) {
      return path.getParent();
    }

    // TODO add toString
  }

  enum FileNameFunction implements Function<Path, Path> {
    INSTANCE;

    public Path apply(@Nonnull final Path path) {
      return path.getFileName();
    }

    // TODO add toString
  }

  enum FileSystemFunction implements Function<Path, FileSystem> {
    INSTANCE;

    public FileSystem apply(@Nonnull final Path path) {
      return path.getFileSystem();
    }

    // TODO add toString
  }

  public static <T extends Path> Function<T, T> toAbsolutePath() {
    return castWhole(ToAbsolutePathFunction.INSTANCE);
  }

  public static <T extends Path> Function<T, T> toRealPath() {
    return castWhole(ToRealPathFunction.Default.INSTANCE);
  }

  public static <T extends Path> Function<T, T> toRealPath(final LinkOption ... options) {
    return castWhole(new ToRealPathFunction(options));
  }

  public static <T extends Path> Function<T, File> toFile() {
    return castInput(ToFileFunction.INSTANCE);
  }

  public static <T extends Path> Function<T, URI> toUri() {
    return castInput(ToUriFunction.INSTANCE);
  }

  enum ToAbsolutePathFunction implements Function<Path, Path> {
    INSTANCE;

    public Path apply(@Nonnull final Path path) {
      return path.toAbsolutePath();
    }

    // TODO add toString
  }

  static final class ToRealPathFunction implements Function<Path, Path>, Serializable {
    private static final long serialVersionUID = 0L;

    private final LinkOption[] options;

    ToRealPathFunction(final LinkOption ... options) {
      this.options = Arrays.copyOf(options, options.length);
    }

    public Path apply(@Nonnull final Path path) {
      try {
        return path.toRealPath(this.options);
      } catch (IOException e) {
        throw propagate(e);
      }
    }

    enum Default implements Function<Path, Path> {
      INSTANCE;

      public Path apply(@Nonnull final Path path) {
        try {
          return path.toRealPath();
        } catch (IOException e) {
          throw propagate(e);
        }
      }

      // TODO add toString
    }

    // TODO add equals, hashCode, toString
  }

  enum ToFileFunction implements Function<Path, File> {
    INSTANCE;

    public File apply(@Nonnull final Path path) {
      return path.toFile();
    }

    // TODO add toString
  }

  enum ToUriFunction implements Function<Path, URI> {
    INSTANCE;

    public URI apply(@Nonnull final Path path) {
      return path.toUri();
    }

    // TODO add toString
  }
}
