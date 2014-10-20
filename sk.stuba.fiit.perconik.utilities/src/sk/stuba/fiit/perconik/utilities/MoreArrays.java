package sk.stuba.fiit.perconik.utilities;

import javax.annotation.Nullable;

/**
 * Static utility methods for unsorted and preferably small arrays.
 * Methods in this class use basic and very simple algorithms for
 * array operations. These algorithms perform best on unsorted small
 * arrays.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreArrays {
  private MoreArrays() {}

  public static boolean contains(final Object[] a, @Nullable final Object key) {
    return search(a, key) >= 0;
  }

  public static int search(final Object[] a, @Nullable final Object key) {
    int length = a.length;

    for (int i = 0; i < length; i ++) {
      Object o = a[i];

      if (key == null ? o == null : key.equals(o)) {
        return i;
      }
    }

    return -1;
  }

  public static Object[] wrap(final Object o) {
    if (o instanceof Object[]) {
      return (Object[]) o;
    }

    return new Object[] {o};
  }
}
