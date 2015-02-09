package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

final class Utilities {
  private Utilities() {}

  static String format(final String format, final Object ... args) {
    for (int i = 0; i < args.length; i ++) {
      if (args[i] instanceof Class) {
        args[i] = ((Class<?>) args[i]).getName();
      }
    }

    return String.format(format, args);
  }

  static RuntimeException createArgument(final String format, final Object ... args) {
    return new IllegalArgumentException(format(format, args));
  }

  static RuntimeException createState(final String format, final Object ... args) {
    return new IllegalStateException(format(format, args));
  }

  static void throwArgument(final String format, final Object ... args) {
    throw createArgument(format, args);
  }

  static void throwState(final String format, final Object ... args) {
    throw createState(format(format, args));
  }

  static void checkArgument(final boolean expression, final String format, final Object ... args) {
    if (!expression) {
      throwArgument(format, args);
    }
  }

  static void checkState(final boolean expression, final String format, final Object ... args) {
    if (!expression) {
      throwState(format, args);
    }
  }

  static <T, R, S extends R> Invokable<T, S> specialize(final Invokable<T, R> invokable, final TypeToken<S> type) {
    TypeToken<? extends R> base = invokable.getReturnType();

    if (!type.unwrap().isAssignableFrom(base.unwrap())) {
      throw new IllegalArgumentException("Invokable is known to return " + base + ", not " + type);
    }

    @SuppressWarnings("unchecked")
    Invokable<T, S> specialized = (Invokable<T, S>) invokable;

    return specialized;
  }
}
