package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import com.google.common.reflect.TypeToken;

public final class DelayedLookup<T> extends AbstractLookup<T> {
  DelayedLookup(final Builder<T> builder) {
    super(builder);
  }

  public static final class Builder<T> extends AbstractBuilder<T> {
    public Builder() {}

    public Builder<T> classConstant(final Class<?> implementation, final Class<? extends T> type, final String name) {
      return classConstant(implementation, TypeToken.of(type), name);
    }

    public Builder<T> classConstant(final Class<?> implementation, final TypeToken<? extends T> type, final String name) {
      try {
        this.add(StaticAccessor.ofClassConstant(implementation, type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public Builder<T> classField(final Class<?> implementation, final Class<? extends T> type, final String name) {
      return classField(implementation, TypeToken.of(type), name);
    }

    public Builder<T> classField(final Class<?> implementation, final TypeToken<? extends T> type, final String name) {
      try {
        this.add(StaticAccessor.ofClassField(implementation, type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public Builder<T> classConstructor(final Class<? extends T> type, final Object ... arguments) {
      return this.classConstructor(TypeToken.of(type), arguments);
    }

    public Builder<T> classConstructor(final TypeToken<? extends T> type, final Object ... arguments) {
      try {
        this.add(StaticAccessor.ofClassConstructor(type, arguments));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public Builder<T> classMethod(final Class<?> implementation, final Class<? extends T> type, final String name, final Object ... arguments) {
      return classMethod(implementation, TypeToken.of(type), name, arguments);
    }

    public Builder<T> classMethod(final Class<?> implementation, final TypeToken<? extends T> type, final String name, final Object ... arguments) {
      try {
        this.add(StaticAccessor.ofClassMethod(implementation, type, name, arguments));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public Builder<T> enumConstant(final Class<? extends T> type, final String name) {
      return this.enumConstant(TypeToken.of(type), name);
    }

    public Builder<T> enumConstant(final TypeToken<? extends T> type, final String name) {
      try {
        this.add(StaticAccessor.ofEnumConstant(type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public Builder<T> instanceConstant(final Object instance, final Class<? extends T> type, final String name) {
      return instanceConstant(instance, TypeToken.of(type), name);
    }

    public Builder<T> instanceConstant(final Object instance, final TypeToken<? extends T> type, final String name) {
      try {
        this.add(DynamicAccessor.ofInstanceConstant(instance, type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public Builder<T> instanceField(final Object instance, final Class<? extends T> type, final String name) {
      return instanceField(instance, TypeToken.of(type), name);
    }

    public Builder<T> instanceField(final Object instance, final TypeToken<? extends T> type, final String name) {
      try {
        this.add(DynamicAccessor.ofInstanceField(instance, type, name));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    public Builder<T> instanceMethod(final Object instance, final Class<? extends T> type, final String name, final Object ... arguments) {
      return instanceMethod(instance, TypeToken.of(type), name, arguments);
    }

    public Builder<T> instanceMethod(final Object instance, final TypeToken<? extends T> type, final String name, final Object ... arguments) {
      try {
        this.add(DynamicAccessor.ofInstanceMethod(instance, type, name, arguments));
      } catch (Exception e) {
        this.handle(e);
      }

      return this;
    }

    @Override
    public DelayedLookup<T> build() {
      return new DelayedLookup<>(this);
    }
  }

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }
}
