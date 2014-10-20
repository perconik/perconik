package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import sk.stuba.fiit.perconik.utilities.reflect.accessor.AbstractAccessor.ConstantAccessor;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.AbstractAccessor.FieldAccessor;
import sk.stuba.fiit.perconik.utilities.reflect.accessor.AbstractAccessor.InvokableAccessor;

import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.checkArgument;
import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.specialize;

public final class DynamicAccessor<T> {
  private DynamicAccessor() {}

  public static <T> Accessor<T> ofInstanceConstant(final Object instance, final Class<T> type, final String name) throws IllegalAccessException, NoSuchFieldException {
    return ofInstanceConstant(instance, TypeToken.of(type), name);
  }

  public static <T> Accessor<T> ofInstanceConstant(final Object instance, final TypeToken<T> type, final String name) throws IllegalAccessException, NoSuchFieldException {
    Field field = instance.getClass().getField(name);

    int modifiers = field.getModifiers();

    checkArgument(!Modifier.isStatic(modifiers), "Field %s of %s is static", name, instance.getClass());
    checkArgument(Modifier.isFinal(modifiers), "Field %s of %s is not final", name, instance.getClass());
    checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s has incorrect type", name, instance.getClass());

    return new InstanceConstant<>(type, (T) type.getRawType().cast(field.get(null)));
  }

  public static <T> Accessor<T> ofInstanceField(final Object instance, final Class<T> type, final String name) throws NoSuchFieldException {
    return ofInstanceField(instance, TypeToken.of(type), name);
  }

  public static <T> Accessor<T> ofInstanceField(final Object instance, final TypeToken<T> type, final String name) throws NoSuchFieldException {
    Field field = instance.getClass().getField(name);

    int modifiers = field.getModifiers();

    checkArgument(!Modifier.isStatic(modifiers), "Field %s of %s is static", name, instance.getClass());
    checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s has incorrect type", name, instance.getClass());

    return new InstanceField<>(type, field, instance);
  }

  public static <T> Accessor<T> ofInstanceMethod(final Object instance, final Class<T> type, final String name) throws NoSuchMethodException {
    return ofInstanceMethod(instance, TypeToken.of(type), name);
  }

  public static <T> Accessor<T> ofInstanceMethod(final Object instance, final TypeToken<T> type, final String name, final Object ... arguments) throws NoSuchMethodException {
    Invokable<Object, Object> method = (Invokable<Object, Object>) Invokable.from(instance.getClass().getMethod(name));

    checkArgument(!method.isStatic(), "Method %s of %s is static", name, instance.getClass());

    return new InstanceMethod<>(type, specialize(method, type), instance, arguments);
  }

  private static final class InstanceConstant<T> extends ConstantAccessor<T> {
    InstanceConstant(final TypeToken<T> type, final T constant) {
      super(type, constant);
    }
  }

  private static final class InstanceField<T> extends FieldAccessor<T> {
    InstanceField(final TypeToken<T> type, final Field field, final Object receiver) {
      super(type, field, receiver);
    }
  }

  private static final class InstanceMethod<T> extends InvokableAccessor<T> {
    InstanceMethod(final TypeToken<T> type, final Invokable<Object, T> method, final Object receiver, final Object ... arguments) {
      super(type, method, receiver, arguments);
    }
  }
}
