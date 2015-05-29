package sk.stuba.fiit.perconik.utilities.constant;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.base.Function;

import static com.google.common.base.Preconditions.checkArgument;

public final class IntegralConstantSupport<E extends Enum<E> & IntegralConstant> extends AbstractConstantSupport<Integer, E> {
  private static final long serialVersionUID = 6686975853072661262L;

  private IntegralConstantSupport(final Class<E> type) {
    super(type);
  }

  private enum Transformation implements Function<IntegralConstant, Integer> {
    INSTANCE;

    public Integer apply(@Nonnull final IntegralConstant constant) {
      return constant.getValue();
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  Function<E, Integer> transformation() {
    return (Function<E, Integer>) Transformation.INSTANCE;
  }

  public static <E extends Enum<E> & IntegralConstant> IntegralConstantSupport<E> of(final Class<E> type) {
    return new IntegralConstantSupport<>(type);
  }

  public static <E extends Enum<E> & IntegralConstant> int constantsAsInteger(final Set<E> constants) {
    int values = 0;

    for (E constant: constants) {
      values |= constant.getValue();
    }

    return values;
  }

  private static final class SerializationProxy<E extends Enum<E> & IntegralConstant> extends AbstractSerializationProxy<Integer, E, IntegralConstantSupport<E>> {
    private static final long serialVersionUID = -8420579032363855266L;

    SerializationProxy(final IntegralConstantSupport<E> support) {
      super(support);
    }

    @Override
    IntegralConstantSupport<E> resolve(final Class<E> type) {
      return of(type);
    }
  }

  @SuppressWarnings({ "static-method", "unused" })
  private void readObject(final ObjectInputStream in) throws InvalidObjectException {
    throw new InvalidObjectException("Serialization proxy required");
  }

  private Object writeReplace() {
    return new SerializationProxy<>(this);
  }

  public Set<Integer> getIntegers() {
    return this.map.keySet();
  }

  public int getConstantsAsInteger() {
    return constantsAsInteger(this.map.values());
  }

  public Set<E> getConstants(final int values) {
    Set<E> flags = EnumSet.noneOf(this.type);

    for (E constant: this.map.values()) {
      if ((values & constant.getValue()) != 0) {
        flags.add(constant);
      }
    }

    return flags;
  }

  public E getConstant(final int value) {
    E constant = this.map.get(value);

    checkArgument(constant != null, "Constant for value %s not found", value);

    return constant;
  }
}
