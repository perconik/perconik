package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.annotation.Nullable;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

abstract class AbstractAccessor<T> implements Accessor<T> {
  final TypeToken<T> token;

  AbstractAccessor(TypeToken<T> token) {
    assert token != null;

    this.token = token;
  }

  static class ConstantAccessor<T> extends AbstractAccessor<T> {
    final T constant;

    ConstantAccessor(TypeToken<T> type, T constant) {
      super(type);

      this.constant = constant;
    }

    @Override
    final T getFailing() {
      return this.constant;
    }
  }

  static class FieldAccessor<T> extends AbstractAccessor<T> {
    final Field field;

    @Nullable
    final Object receiver;

    FieldAccessor(TypeToken<T> type, Field field, @Nullable Object receiver) {
      super(type);

      this.field = field;
      this.receiver = receiver;
    }

    @Override
    final T getFailing() throws IllegalAccessException {
      return (T) this.token.getRawType().cast(this.field.get(this.receiver));
    }
  }

  static class InvokableAccessor<T> extends AbstractAccessor<T> {
    final Invokable<Object, T> invokable;

    @Nullable
    final Object receiver;

    final Object[] arguments;

    InvokableAccessor(TypeToken<T> type, Invokable<Object, T> invokable, @Nullable Object receiver, Object ... arguments) {
      super(type);

      this.invokable = invokable;
      this.receiver = receiver;
      this.arguments = Arrays.copyOf(arguments, arguments.length);
    }

    @Override
    final T getFailing() throws IllegalAccessException, InvocationTargetException {
      return this.invokable.invoke(this.receiver, this.arguments);
    }
  }

  public final T get() {
    try {
      return this.getFailing();
    } catch (ReflectiveOperationException e) {
      throw new AccessorInvocationException(e);
    }
  }

  abstract T getFailing() throws ReflectiveOperationException;
}
