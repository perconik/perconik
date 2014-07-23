package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import com.google.common.primitives.Primitives;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

final class Utilities {
  private Utilities() {
    throw new AssertionError();
  }

  static final String format(String format, Object ... args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i] instanceof Class) {
        args[i] = ((Class<?>) args[i]).getName();
      }
    }

    return String.format(format, args);
  }

  static final RuntimeException createArgument(String format, Object ... args) {
    return new IllegalArgumentException(format(format, args));
  }

  static final RuntimeException createState(String format, Object ... args) {
    return new IllegalStateException(format(format, args));
  }

  static final void throwArgument(String format, Object ... args) {
    throw createArgument(format, args);
  }

  static final void throwState(String format, Object ... args) {
    throw createState(format(format, args));
  }

  static final void checkArgument(boolean expression, String format, Object ... args) {
    if (!expression) {
      throwArgument(format, args);
    }
  }

  static final void checkState(boolean expression, String format, Object ... args) {
    if (!expression) {
      throwState(format, args);
    }
  }

  static final <T, R, S extends R> Invokable<T, S> specialize(Invokable<T, R> invokable, TypeToken<S> type) {
    TypeToken<? extends R> base = invokable.getReturnType();

    if (!wrap(type).isAssignableFrom(wrap(base))) {
      throw new IllegalArgumentException("Invokable is known to return " + base + ", not " + type);
    }

    @SuppressWarnings("unchecked")
    Invokable<T, S> specialized = (Invokable<T, S>) invokable;

    return specialized;
  }

  static <T> TypeToken<T> wrap(TypeToken<T> type) {
    return (TypeToken<T>) (type.isPrimitive() ? TypeToken.of(Primitives.wrap(type.getRawType())) : type);
  }

  static <T> TypeToken<T> unwrap(TypeToken<T> type) {
    return (TypeToken<T>) (type.isPrimitive() ? TypeToken.of(Primitives.unwrap(type.getRawType())) : type);
  }
}
