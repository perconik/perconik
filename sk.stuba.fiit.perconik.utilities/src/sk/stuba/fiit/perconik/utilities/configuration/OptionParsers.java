package sk.stuba.fiit.perconik.utilities.configuration;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;

import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolvers;

import static java.util.Objects.requireNonNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Throwables.propagate;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;
import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public final class OptionParsers {
  private OptionParsers() {}

  private enum ObjectOptionParser implements OptionParser<Object> {
    INSTANCE;

    public Object parse(final Object object) {
      return requireNonNull(object);
    }
  }

  public static OptionParser<Object> objectParser() {
    return ObjectOptionParser.INSTANCE;
  }

  private enum BooleanOptionParser implements OptionParser<Boolean> {
    INSTANCE;

    public Boolean parse(final Object object) {
      return object instanceof Boolean ? (Boolean) object : Boolean.valueOf(object.toString());
    }
  }

  public static OptionParser<Boolean> booleanParser() {
    return BooleanOptionParser.INSTANCE;
  }

  private enum ByteOptionParser implements OptionParser<Byte> {
    INSTANCE;

    public Byte parse(final Object object) {
      return object instanceof Byte ? (Byte) object : Byte.valueOf(object.toString());
    }
  }

  private enum ShortOptionParser implements OptionParser<Short> {
    INSTANCE;

    public Short parse(final Object object) {
      return object instanceof Short ? (Short) object : Short.valueOf(object.toString());
    }
  }

  private enum IntegerOptionParser implements OptionParser<Integer> {
    INSTANCE;

    public Integer parse(final Object object) {
      return object instanceof Integer ? (Integer) object : Integer.valueOf(object.toString());
    }
  }

  private enum LongOptionParser implements OptionParser<Long> {
    INSTANCE;

    public Long parse(final Object object) {
      return object instanceof Long ? (Long) object : Long.valueOf(object.toString());
    }
  }

  public static OptionParser<Byte> byteParser() {
    return ByteOptionParser.INSTANCE;
  }

  public static OptionParser<Short> shortParser() {
    return ShortOptionParser.INSTANCE;
  }

  public static OptionParser<Integer> integerParser() {
    return IntegerOptionParser.INSTANCE;
  }

  public static OptionParser<Long> longParser() {
    return LongOptionParser.INSTANCE;
  }

  private enum FloatOptionParser implements OptionParser<Float> {
    INSTANCE;

    public Float parse(final Object object) {
      return object instanceof Float ? (Float) object : Float.valueOf(object.toString());
    }
  }

  private enum DoubleOptionParser implements OptionParser<Double> {
    INSTANCE;

    public Double parse(final Object object) {
      return object instanceof Double ? (Double) object : Double.valueOf(object.toString());
    }
  }

  public static OptionParser<Float> floatParser() {
    return FloatOptionParser.INSTANCE;
  }

  public static OptionParser<Double> doubleParser() {
    return DoubleOptionParser.INSTANCE;
  }

  private enum CharacterOptionParser implements OptionParser<Character> {
    INSTANCE;

    public Character parse(final Object object) {
      if (object instanceof Character) {
        return (Character) object;
      }

      String value = object.toString();

      checkArgument(value.length() == 1);

      return Character.valueOf(value.charAt(0));
    }
  }

  private enum StringOptionParser implements OptionParser<String> {
    INSTANCE;

    public String parse(final Object object) {
      return object instanceof String ? (String) object : object.toString();
    }
  }

  public static OptionParser<Character> characterParser() {
    return CharacterOptionParser.INSTANCE;
  }

  public static OptionParser<String> stringParser() {
    return StringOptionParser.INSTANCE;
  }

  private enum BigIntegerOptionParser implements OptionParser<BigInteger> {
    INSTANCE;

    public BigInteger parse(final Object object) {
      return object instanceof BigInteger ? (BigInteger) object : new BigInteger(object.toString());
    }
  }

  private enum BigDecimalOptionParser implements OptionParser<BigDecimal> {
    INSTANCE;

    public BigDecimal parse(final Object object) {
      return object instanceof BigDecimal ? (BigDecimal) object : new BigDecimal(object.toString());
    }
  }

  public static OptionParser<BigInteger> bigIntegerParser() {
    return BigIntegerOptionParser.INSTANCE;
  }

  public static OptionParser<BigDecimal> bigDecimalParser() {
    return BigDecimalOptionParser.INSTANCE;
  }

  private enum UnsignedIntegerOptionParser implements OptionParser<UnsignedInteger> {
    INSTANCE;

    public UnsignedInteger parse(final Object object) {
      return object instanceof UnsignedInteger ? (UnsignedInteger) object : UnsignedInteger.valueOf(object.toString());
    }
  }

  private enum UnsignedLongOptionParser implements OptionParser<UnsignedLong> {
    INSTANCE;

    public UnsignedLong parse(final Object object) {
      return object instanceof UnsignedLong ? (UnsignedLong) object : UnsignedLong.valueOf(object.toString());
    }
  }

  public static OptionParser<UnsignedInteger> unsignedIntegerParser() {
    return UnsignedIntegerOptionParser.INSTANCE;
  }

  public static OptionParser<UnsignedLong> unsignedLongParser() {
    return UnsignedLongOptionParser.INSTANCE;
  }

  private enum PathOptionParser implements OptionParser<Path> {
    INSTANCE;

    public Path parse(final Object object) {
      return object instanceof Path ? (Path) object : Paths.get(object.toString());
    }
  }

  private enum UriOptionParser implements OptionParser<URI> {
    INSTANCE;

    public URI parse(final Object object) {
      return object instanceof URI ? (URI) object : newUri(object.toString());
    }
  }

  private enum UrlOptionParser implements OptionParser<URL> {
    INSTANCE;

    public URL parse(final Object object) {
      return object instanceof URL ? (URL) object : newUrl(object.toString());
    }
  }

  public static OptionParser<Path> pathParser() {
    return PathOptionParser.INSTANCE;
  }

  public static OptionParser<URI> uriParser() {
    return UriOptionParser.INSTANCE;
  }

  public static OptionParser<URL> urlParser() {
    return UrlOptionParser.INSTANCE;
  }

  private static final class ClassParser implements OptionParser<Class<?>>, Serializable {
    private static final long serialVersionUID = 0L;

    private final ClassResolver resolver;

    ClassParser(final ClassResolver resolver) {
      this.resolver = requireNonNull(resolver);
    }

    public Class<?> parse(final Object object) {
      try {
        return object instanceof Class ? (Class<?>) object : this.resolver.forName(object.toString());
      } catch (ClassNotFoundException failure) {
        throw propagate(failure);
      }
    }
  }

  public static OptionParser<Class<?>> classParser() {
    return new ClassParser(ClassResolvers.getDefault());
  }

  public static OptionParser<Class<?>> classParser(final ClassResolver resolver) {
    return new ClassParser(resolver);
  }
}
