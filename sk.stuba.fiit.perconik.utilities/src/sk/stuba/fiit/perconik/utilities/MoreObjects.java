package sk.stuba.fiit.perconik.utilities;

import javax.annotation.Nullable;

/**
 * Static utility methods pertaining to {@code Object} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreObjects {
  private MoreObjects() {}

  public static <T> T firstNonNullOrNull(@Nullable final T first, @Nullable final T second) {
    return first != null ? first : second;
  }
}
