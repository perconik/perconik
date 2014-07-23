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

  IntegralConstantSupport(final Class<E> type) {
    super(type);
  }

  private static enum Transformation implements Function<IntegralConstant, Integer> {
    INSTANCE;

    public final Integer apply(@Nonnull final IntegralConstant constant) {
      return constant.getValue();
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  final Function<E, Integer> transformation() {
    return (Function<E, Integer>) Transformation.INSTANCE;
  }

  public static final <E extends Enum<E> & IntegralConstant> IntegralConstantSupport<E> of(final Class<E> type) {
    return new IntegralConstantSupport<>(type);
  }

  public static final <E extends Enum<E> & IntegralConstant> int constantsAsInteger(final Set<E> constants) {
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
    final IntegralConstantSupport<E> resolve(final Class<E> type) {
      return new IntegralConstantSupport<>(type);
    }
  }

  @SuppressWarnings({"static-method", "unused"})
  private final void readObject(final ObjectInputStream in) throws InvalidObjectException {
    throw new InvalidObjectException("Serialization proxy required");
  }

  private final Object writeReplace() {
    return new SerializationProxy<>(this);
  }

  public final Set<Integer> getIntegers() {
    return this.map.keySet();
  }

  public final int getConstantsAsInteger() {
    return constantsAsInteger(this.map.values());
  }

  public final Set<E> getConstants(final int values) {
    Set<E> flags = EnumSet.noneOf(this.type);

    for (E constant: this.map.values()) {
      if ((values & constant.getValue()) != 0) {
        flags.add(constant);
      }
    }

    return flags;
  }

  public final E getConstant(final int value) {
    E constant = this.map.get(value);

    checkArgument(constant != null, "Constant for value %s not found", value);

    return constant;
  }
}
