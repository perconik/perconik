package sk.stuba.fiit.perconik.utilities.constant;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.base.Function;

import static com.google.common.base.Preconditions.checkArgument;

public final class TypeConstantSupport<E extends Enum<E> & TypeConstant<T>, T> extends AbstractConstantSupport<Class<? extends T>, E> {
  private static final long serialVersionUID = -2957728433673208288L;

  private TypeConstantSupport(final Class<E> type) {
    super(type);
  }

  private enum Transformation implements Function<TypeConstant<?>, Class<?>> {
    INSTANCE;

    public Class<?> apply(@Nonnull final TypeConstant<?> constant) {
      return constant.getType();
    }
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  Function<E, Class<? extends T>> transformation() {
    return (Function) Transformation.INSTANCE;
  }

  public static <E extends Enum<E> & TypeConstant<T>, T> TypeConstantSupport<E, T> of(final Class<E> type) {
    return new TypeConstantSupport<>(type);
  }

  private static final class SerializationProxy<E extends Enum<E> & TypeConstant<T>, T> extends AbstractSerializationProxy<Class<? extends T>, E, TypeConstantSupport<E, T>> {
    private static final long serialVersionUID = -8420579032363855266L;

    SerializationProxy(final TypeConstantSupport<E, T> support) {
      super(support);
    }

    @Override
    TypeConstantSupport<E, T> resolve(final Class<E> type) {
      return of(type);
    }
  }

  @SuppressWarnings({"static-method", "unused"})
  private void readObject(final ObjectInputStream in) throws InvalidObjectException {
    throw new InvalidObjectException("Serialization proxy required");
  }

  private Object writeReplace() {
    return new SerializationProxy<>(this);
  }

  public Set<Class<? extends T>> getTypes() {
    return this.map.keySet();
  }

  public E getConstant(final Class<? extends T> type) {
    E constant = this.map.get(type);

    checkArgument(constant != null, "Constant for type %s not found", type.getName());

    return constant;
  }
}
