package sk.stuba.fiit.perconik.utilities.constant;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.base.Function;

import static com.google.common.base.Preconditions.checkArgument;

public final class StringConstantSupport<E extends Enum<E> & StringConstant> extends AbstractConstantSupport<String, E> {
  private static final long serialVersionUID = -2957728433673208288L;

  private StringConstantSupport(final Class<E> type) {
    super(type);
  }

  private enum Transformation implements Function<StringConstant, String> {
    INSTANCE;

    public String apply(@Nonnull final StringConstant constant) {
      return constant.getValue();
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  Function<E, String> transformation() {
    return (Function<E, String>) Transformation.INSTANCE;
  }

  public static <E extends Enum<E> & StringConstant> StringConstantSupport<E> of(final Class<E> type) {
    return new StringConstantSupport<>(type);
  }

  private static final class SerializationProxy<E extends Enum<E> & StringConstant> extends AbstractSerializationProxy<String, E, StringConstantSupport<E>> {
    private static final long serialVersionUID = 6916645039794185238L;

    SerializationProxy(final StringConstantSupport<E> support) {
      super(support);
    }

    @Override
    StringConstantSupport<E> resolve(final Class<E> type) {
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

  public Set<String> getStrings() {
    return this.map.keySet();
  }

  public E getConstant(final String value) {
    E constant = this.map.get(value);

    checkArgument(constant != null, "Constant for value %s not found", value);

    return constant;
  }
}
