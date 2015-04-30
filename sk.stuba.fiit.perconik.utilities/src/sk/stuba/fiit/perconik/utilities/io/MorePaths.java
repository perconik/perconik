package sk.stuba.fiit.perconik.utilities.io;

import java.net.URI;
import java.nio.file.Path;
import java.util.Iterator;

import com.google.common.collect.AbstractSequentialIterator;

import static java.nio.file.Paths.get;

import static com.google.common.base.Preconditions.checkState;

public final class MorePaths {
  private MorePaths() {}

  public static Path path(final URI uri) {
    return get(uri);
  }

  public static Path path(final String segment, final String ... more) {
    return get(segment, more);
  }

  private static final class DownToBaseIterator<T extends Path> extends AbstractSequentialIterator<T> {
    private final int count;

    protected DownToBaseIterator(final T base, final T path) {
      super(path);

      checkState(path.startsWith(base));

      this.count = base.getNameCount();
    }

    @Override
    protected T computeNext(final T previous) {
      int count = previous.getNameCount();

      if (this.count < count) {
        // safe cast as T overrides getParent() properly
        @SuppressWarnings("unchecked")
        T result = (T) previous.getParent();

        return result;
      }

      return null;
    }
  }

  private static final class DownToRootIterator<T extends Path> extends AbstractSequentialIterator<T> {
    protected DownToRootIterator(final T path) {
      super(path);
    }

    @Override
    protected T computeNext(final T previous) {
      // safe cast as T overrides getParent() properly
      @SuppressWarnings("unchecked")
      T result = (T) previous.getParent();

      return result;
    }
  }

  public static <T extends Path> Iterable<T> downToBase(final T base, final T path) {
    return new Iterable<T>() {
      public Iterator<T> iterator() {
        return new DownToBaseIterator<>(base, path);
      }
    };
  }

  public static <T extends Path> Iterable<T> downToRoot(final T path) {
    return new Iterable<T>() {
      public Iterator<T> iterator() {
        return new DownToRootIterator<>(path);
      }
    };
  }
}
