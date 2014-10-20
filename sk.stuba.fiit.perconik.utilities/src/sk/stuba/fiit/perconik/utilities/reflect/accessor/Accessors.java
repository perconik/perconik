package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import com.google.common.reflect.TypeToken;

import sk.stuba.fiit.perconik.utilities.Exceptional;

public final class Accessors {
  private Accessors() {}

  public static <T> Exceptional<Accessor<T>> ofClassConstant(final Class<?> implementation, final Class<T> type, final String name) {
    return ofClassConstant(implementation, TypeToken.of(type), name);
  }

  public static <T> Exceptional<Accessor<T>> ofClassConstant(final Class<?> implementation, final TypeToken<T> type, final String name) {
    try {
      return Exceptional.of(StaticAccessor.ofClassConstant(implementation, type, name));
    } catch (Exception e) {
      return Exceptional.failure(e);
    }
  }

  public static <T> Exceptional<Accessor<T>> ofClassField(final Class<?> implementation, final Class<T> type, final String name) {
    return ofClassField(implementation, TypeToken.of(type), name);
  }

  public static <T> Exceptional<Accessor<T>> ofClassField(final Class<?> implementation, final TypeToken<T> type, final String name) {
    try {
      return Exceptional.of(StaticAccessor.ofClassField(implementation, type, name));
    } catch (Exception e) {
      return Exceptional.failure(e);
    }
  }

  public static <T> Exceptional<Accessor<T>> ofClassConstructor(final Class<T> type, final Object ... arguments) {
    return ofClassConstructor(TypeToken.of(type), arguments);
  }

  public static <T> Exceptional<Accessor<T>> ofClassConstructor(final TypeToken<T> type, final Object ... arguments) {
    try {
      return Exceptional.of(StaticAccessor.ofClassConstructor(type, arguments));
    } catch (Exception e) {
      return Exceptional.failure(e);
    }
  }

  public static <T> Exceptional<Accessor<T>> ofClassMethod(final Class<?> implementation, final Class<T> type, final String name, final Object ... arguments) {
    return ofClassMethod(implementation, TypeToken.of(type), name, arguments);
  }

  public static <T> Exceptional<Accessor<T>> ofClassMethod(final Class<?> implementation, final TypeToken<T> type, final String name, final Object ... arguments) {
    try {
      return Exceptional.of(StaticAccessor.ofClassMethod(implementation, type, name, arguments));
    } catch (Exception e) {
      return Exceptional.failure(e);
    }
  }

  public static <T> Exceptional<Accessor<T>> ofEnumConstant(final Class<T> type, final String name) {
    return ofEnumConstant(TypeToken.of(type), name);
  }

  public static <T> Exceptional<Accessor<T>> ofEnumConstant(final TypeToken<T> type, final String name) {
    try {
      return Exceptional.of(StaticAccessor.ofEnumConstant(type, name));
    } catch (Exception e) {
      return Exceptional.failure(e);
    }
  }

  public static <T> Exceptional<Accessor<T>> ofInstanceConstant(final Object instance, final Class<T> type, final String name) {
    return ofInstanceConstant(instance, TypeToken.of(type), name);
  }

  public static <T> Exceptional<Accessor<T>> ofInstanceConstant(final Object instance, final TypeToken<T> type, final String name) {
    try {
      return Exceptional.of(DynamicAccessor.ofInstanceConstant(instance, type, name));
    } catch (Exception e) {
      return Exceptional.failure(e);
    }
  }

  public static <T> Exceptional<Accessor<T>> ofInstanceField(final Object instance, final Class<T> type, final String name) {
    return ofInstanceField(instance, TypeToken.of(type), name);
  }

  public static <T> Exceptional<Accessor<T>> ofInstanceField(final Object instance, final TypeToken<T> type, final String name) {
    try {
      return Exceptional.of(DynamicAccessor.ofInstanceField(instance, type, name));
    } catch (Exception e) {
      return Exceptional.failure(e);
    }
  }

  public static <T> Exceptional<Accessor<T>> ofInstanceMethod(final Object instance, final Class<T> type, final String name) {
    return ofInstanceMethod(instance, TypeToken.of(type), name);
  }

  public static <T> Exceptional<Accessor<T>> ofInstanceMethod(final Object instance, final TypeToken<T> type, final String name, final Object ... arguments) {
    try {
      return Exceptional.of(DynamicAccessor.ofInstanceMethod(instance, type, name, arguments));
    } catch (Exception e) {
      return Exceptional.failure(e);
    }
  }
}
