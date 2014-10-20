package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import sk.stuba.fiit.perconik.utilities.reflect.accessor.AbstractAccessor.ConstantAccessor;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.AbstractAccessor.FieldAccessor;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.AbstractAccessor.InvokableAccessor;

import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.checkArgument;
import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.createArgument;
import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.specialize;

public final class StaticAccessor<T> {
  private StaticAccessor() {}

  public static <T> Accessor<T> ofClassConstant(final Class<?> implementation, final Class<T> type, final String name) throws IllegalAccessException, NoSuchFieldException {
    return ofClassConstant(implementation, TypeToken.of(type), name);
  }

  public static <T> Accessor<T> ofClassConstant(final Class<?> implementation, final TypeToken<T> type, final String name) throws IllegalAccessException, NoSuchFieldException {
    Field field = implementation.getField(name);

    int modifiers = field.getModifiers();

    checkArgument(Modifier.isStatic(modifiers), "Field %s of %s is not static", name, implementation);
    checkArgument(Modifier.isFinal(modifiers), "Field %s of %s is not final", name, implementation);
    checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s has incorrect type", name, implementation);

    return new ClassConstant<>(type, (T) type.getRawType().cast(field.get(null)));
  }

  public static <T> Accessor<T> ofClassField(final Class<?> implementation, final Class<T> type, final String name) throws NoSuchFieldException {
    return ofClassField(implementation, TypeToken.of(type), name);
  }

  public static <T> Accessor<T> ofClassField(final Class<?> implementation, final TypeToken<T> type, final String name) throws NoSuchFieldException {
    Field field = implementation.getField(name);

    int modifiers = field.getModifiers();

    checkArgument(Modifier.isStatic(modifiers), "Field %s of %s is not static", name, implementation);
    checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s has incorrect type", name, implementation);

    return new ClassField<>(type, field);
  }

  public static <T> Accessor<T> ofClassConstructor(final Class<T> type, final Object ... arguments) throws NoSuchMethodException {
    return ofClassConstructor(TypeToken.of(type), arguments);
  }

  public static <T> Accessor<T> ofClassConstructor(final TypeToken<T> type, final Object ... arguments) throws NoSuchMethodException {
    Constructor<T> constructor = (Constructor<T>) type.getRawType().getConstructor();

    return new ClassConstructor<>(type, (Invokable<Object, T>) Invokable.from(constructor), arguments);
  }

  public static <T> Accessor<T> ofClassMethod(final Class<?> implementation, final Class<T> type, final String name, final Object ... arguments) throws NoSuchMethodException {
    return ofClassMethod(implementation, TypeToken.of(type), name, arguments);
  }

  public static <T> Accessor<T> ofClassMethod(final Class<?> implementation, final TypeToken<T> type, final String name, final Object ... arguments) throws NoSuchMethodException {
    Invokable<Object, Object> method = (Invokable<Object, Object>) Invokable.from(implementation.getMethod(name));

    checkArgument(method.isStatic(), "Method %s of %s is not static", name, implementation);

    return new ClassMethod<>(type, specialize(method, type), arguments);
  }

  public static <T> Accessor<T> ofEnumConstant(final Class<T> type, final String name) {
    return ofEnumConstant(TypeToken.of(type), name);
  }

  public static <T> Accessor<T> ofEnumConstant(final TypeToken<T> type, final String name) {
    Class<T> raw = (Class<T>) type.getRawType();

    checkArgument(Enum.class.isAssignableFrom(raw), "Class %s is not an enum", type);

    T[] constants = raw.getEnumConstants();

    for (T constant: constants) {
      if (name.equals(Enum.class.cast(constant).name())) {
        return new EnumConstant<>(type, constant);
      }
    }

    throw createArgument("Constant %s not found in enum %s", name, type);
  }

  private static final class ClassConstant<T> extends ConstantAccessor<T> {
    ClassConstant(final TypeToken<T> type, final T constant) {
      super(type, constant);
    }
  }

  private static final class ClassField<T> extends FieldAccessor<T> {
    ClassField(final TypeToken<T> type, final Field field) {
      super(type, field, null);
    }
  }

  private static final class ClassConstructor<T> extends InvokableAccessor<T> {
    ClassConstructor(final TypeToken<T> type, final Invokable<Object, T> constructor, final Object ... arguments) {
      super(type, constructor, null, arguments);
    }
  }

  private static final class ClassMethod<T> extends InvokableAccessor<T> {
    ClassMethod(final TypeToken<T> type, final Invokable<Object, T> method, final Object ... arguments) {
      super(type, method, arguments);
    }
  }

  private static final class EnumConstant<T> extends ConstantAccessor<T> {
    EnumConstant(final TypeToken<T> type, final T constant) {
      super(type, constant);
    }
  }
}
