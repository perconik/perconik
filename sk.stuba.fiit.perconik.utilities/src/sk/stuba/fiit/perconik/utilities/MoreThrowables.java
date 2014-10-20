package sk.stuba.fiit.perconik.utilities;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

/**
 * Static utility methods pertaining to {@code Throwable} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreThrowables {
  private MoreThrowables() {}

  public static <T extends Throwable> T initializeCause(final T throwable, @Nullable final Throwable cause) {
    throwable.initCause(cause);

    return throwable;
  }

  public static <T extends Throwable> T initializeCause(final T throwable, final Optional<? extends Throwable> cause) {
    return initializeCause(throwable, cause.orNull());
  }

  public static <T extends Throwable> T initializeSuppressor(final T suppressor, final Throwable suppressed) {
    suppressor.addSuppressed(suppressed);

    return suppressor;
  }

  public static <T extends Throwable> T initializeSuppressor(final T suppressor, final Iterable<? extends Throwable> suppressions) {
    addSuppressed(suppressor, suppressions);

    return suppressor;
  }

  public static void addSuppressed(final Throwable throwable, final Optional<? extends Throwable> suppressed) {
    if (suppressed.isPresent()) {
      throwable.addSuppressed(suppressed.get());
    }
  }

  public static void addSuppressed(final Throwable throwable, final Iterable<? extends Throwable> suppressions) {
    for (Throwable suppressed: suppressions) {
      throwable.addSuppressed(suppressed);
    }
  }
}
