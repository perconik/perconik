package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import com.google.common.reflect.TypeToken;

public final class DelayedLookup<T> extends AbstractLookup<T> {
  DelayedLookup(Builder<T> builder) {
    super(builder);
  }

  public static final class Builder<T> extends AbstractBuilder<T> {
    public Builder() {}

    public final Builder<T> classConstant(Class<?> implementation, Class<? extends T> type, String name) {
      return classConstant(implementation, TypeToken.of(type), name);
    }

    public final Builder<T> classConstant(Class<?> implementation, TypeToken<? extends T> type, String name) {
      try {
        this.add(StaticAccessor.ofClassConstant(implementation, type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public final Builder<T> classField(Class<?> implementation, Class<? extends T> type, String name) {
      return classField(implementation, TypeToken.of(type), name);
    }

    public final Builder<T> classField(Class<?> implementation, TypeToken<? extends T> type, String name) {
      try {
        this.add(StaticAccessor.ofClassField(implementation, type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public final Builder<T> classConstructor(Class<? extends T> type, Object ... arguments) {
      return this.classConstructor(TypeToken.of(type), arguments);
    }

    public final Builder<T> classConstructor(TypeToken<? extends T> type, Object ... arguments) {
      try {
        this.add(StaticAccessor.ofClassConstructor(type, arguments));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public final Builder<T> classMethod(Class<?> implementation, Class<? extends T> type, String name, Object ... arguments) {
      return classMethod(implementation, TypeToken.of(type), name, arguments);
    }

    public final Builder<T> classMethod(Class<?> implementation, TypeToken<? extends T> type, String name, Object ... arguments) {
      try {
        this.add(StaticAccessor.ofClassMethod(implementation, type, name, arguments));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public final Builder<T> enumConstant(Class<? extends T> type, String name) {
      return this.enumConstant(TypeToken.of(type), name);
    }

    public final Builder<T> enumConstant(TypeToken<? extends T> type, String name) {
      try {
        this.add(StaticAccessor.ofEnumConstant(type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public final Builder<T> instanceConstant(Object instance, Class<? extends T> type, String name) {
      return instanceConstant(instance, TypeToken.of(type), name);
    }

    public final Builder<T> instanceConstant(Object instance, TypeToken<? extends T> type, String name) {
      try {
        this.add(DynamicAccessor.ofInstanceConstant(instance, type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public final Builder<T> instanceField(Object instance, Class<? extends T> type, String name) {
      return instanceField(instance, TypeToken.of(type), name);
    }

    public final Builder<T> instanceField(Object instance, TypeToken<? extends T> type, String name) {
      try {
        this.add(DynamicAccessor.ofInstanceField(instance, type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public final Builder<T> instanceMethod(Object instance, Class<? extends T> type, String name, Object ... arguments) {
      return instanceMethod(instance, TypeToken.of(type), name, arguments);
    }

    public final Builder<T> instanceMethod(Object instance, TypeToken<? extends T> type, String name, Object ... arguments) {
      try {
        this.add(DynamicAccessor.ofInstanceMethod(instance, type, name, arguments));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    @Override
    public final DelayedLookup<T> build() {
      return new DelayedLookup<>(this);
    }
  }

  public static final <T> Builder<T> builder() {
    return new Builder<>();
  }
}
