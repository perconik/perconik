package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.checkArgument;
import static sk.stuba.fiit.perconik.utilities.reflect.accessor.Utilities.specialize;

public abstract class DynamicAccessor<T> extends AbstractAccessor<T> {
  DynamicAccessor(final TypeToken<T> token) {
    super(token);
  }

  public static final <T> Accessor<T> ofInstanceConstant(Object instance, Class<T> type, String name) throws IllegalAccessException, NoSuchFieldException {
    return ofInstanceConstant(instance, TypeToken.of(type), name);
  }

  public static final <T> Accessor<T> ofInstanceConstant(Object instance, TypeToken<T> type, String name) throws IllegalAccessException, NoSuchFieldException {
    Field field = instance.getClass().getField(name);

    int modifiers = field.getModifiers();

    checkArgument(!Modifier.isStatic(modifiers), "Field %s of %s is static", name, instance.getClass());
    checkArgument(Modifier.isFinal(modifiers), "Field %s of %s is not final", name, instance.getClass());
    checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s has incorrect type", name, instance.getClass());

    return new InstanceConstant<>(type, (T) type.getRawType().cast(field.get(null)));
  }

  public static final <T> Accessor<T> ofInstanceField(Object instance, Class<T> type, String name) throws NoSuchFieldException {
    return ofInstanceField(instance, TypeToken.of(type), name);
  }

  public static final <T> Accessor<T> ofInstanceField(Object instance, TypeToken<T> type, String name) throws NoSuchFieldException {
    Field field = instance.getClass().getField(name);

    int modifiers = field.getModifiers();

    checkArgument(!Modifier.isStatic(modifiers), "Field %s of %s is static", name, instance.getClass());
    checkArgument(type.equals(TypeToken.of(field.getGenericType())), "Field %s of %s has incorrect type", name, instance.getClass());

    return new InstanceField<>(type, field, instance);
  }

  public static final <T> Accessor<T> ofInstanceMethod(Object instance, Class<T> type, String name) throws NoSuchMethodException {
    return ofInstanceMethod(instance, TypeToken.of(type), name);
  }

  public static final <T> Accessor<T> ofInstanceMethod(Object instance, TypeToken<T> type, String name, Object ... arguments) throws NoSuchMethodException {
    Invokable<Object, Object> method = (Invokable<Object, Object>) Invokable.from(instance.getClass().getMethod(name));

    checkArgument(!method.isStatic(), "Method %s of %s is static", name, instance.getClass());

    return new InstanceMethod<>(type, specialize(method, type), instance, arguments);
  }

  private static final class InstanceConstant<T> extends ConstantAccessor<T> {
    InstanceConstant(TypeToken<T> type, T constant) {
      super(type, constant);
    }
  }

  private static final class InstanceField<T> extends FieldAccessor<T> {
    InstanceField(TypeToken<T> type, Field field, Object receiver) {
      super(type, field, receiver);
    }
  }

  private static final class InstanceMethod<T> extends InvokableAccessor<T> {
    InstanceMethod(TypeToken<T> type, Invokable<Object, T> method, Object receiver, Object ... arguments) {
      super(type, method, receiver, arguments);
    }
  }
}
