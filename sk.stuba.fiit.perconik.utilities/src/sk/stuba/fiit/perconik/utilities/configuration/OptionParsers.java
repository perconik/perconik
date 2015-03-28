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
import com.google.common.reflect.TypeToken;

import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolvers;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;
import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUrl;

public final class OptionParsers {
  private OptionParsers() {}

  private enum ObjectParser implements OptionParser<Object> {
    INSTANCE;

    public Object parse(final Object object) {
      return checkNotNull(object);
    }

    public TypeToken<Object> type() {
      return TypeToken.of(Object.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<Object> objectParser() {
    return ObjectParser.INSTANCE;
  }

  private enum BooleanParser implements OptionParser<Boolean> {
    INSTANCE;

    public Boolean parse(final Object object) {
      return object instanceof Boolean ? (Boolean) object : Boolean.valueOf(object.toString());
    }

    public TypeToken<Boolean> type() {
      return TypeToken.of(Boolean.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<Boolean> booleanParser() {
    return BooleanParser.INSTANCE;
  }

  private enum ByteParser implements OptionParser<Byte> {
    INSTANCE;

    public Byte parse(final Object object) {
      return object instanceof Byte ? (Byte) object : Byte.valueOf(object.toString());
    }

    public TypeToken<Byte> type() {
      return TypeToken.of(Byte.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum ShortParser implements OptionParser<Short> {
    INSTANCE;

    public Short parse(final Object object) {
      return object instanceof Short ? (Short) object : Short.valueOf(object.toString());
    }

    public TypeToken<Short> type() {
      return TypeToken.of(Short.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum IntegerParser implements OptionParser<Integer> {
    INSTANCE;

    public Integer parse(final Object object) {
      return object instanceof Integer ? (Integer) object : Integer.valueOf(object.toString());
    }

    public TypeToken<Integer> type() {
      return TypeToken.of(Integer.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum LongParser implements OptionParser<Long> {
    INSTANCE;

    public Long parse(final Object object) {
      return object instanceof Long ? (Long) object : Long.valueOf(object.toString());
    }

    public TypeToken<Long> type() {
      return TypeToken.of(Long.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<Byte> byteParser() {
    return ByteParser.INSTANCE;
  }

  public static OptionParser<Short> shortParser() {
    return ShortParser.INSTANCE;
  }

  public static OptionParser<Integer> integerParser() {
    return IntegerParser.INSTANCE;
  }

  public static OptionParser<Long> longParser() {
    return LongParser.INSTANCE;
  }

  private enum FloatParser implements OptionParser<Float> {
    INSTANCE;

    public Float parse(final Object object) {
      return object instanceof Float ? (Float) object : Float.valueOf(object.toString());
    }

    public TypeToken<Float> type() {
      return TypeToken.of(Float.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum DoubleParser implements OptionParser<Double> {
    INSTANCE;

    public Double parse(final Object object) {
      return object instanceof Double ? (Double) object : Double.valueOf(object.toString());
    }

    public TypeToken<Double> type() {
      return TypeToken.of(Double.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<Float> floatParser() {
    return FloatParser.INSTANCE;
  }

  public static OptionParser<Double> doubleParser() {
    return DoubleParser.INSTANCE;
  }

  private enum CharacterParser implements OptionParser<Character> {
    INSTANCE;

    public Character parse(final Object object) {
      if (object instanceof Character) {
        return (Character) object;
      }

      String value = object.toString();

      checkArgument(value.length() == 1);

      return Character.valueOf(value.charAt(0));
    }

    public TypeToken<Character> type() {
      return TypeToken.of(Character.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum StringParser implements OptionParser<String> {
    INSTANCE;

    public String parse(final Object object) {
      return object instanceof String ? (String) object : object.toString();
    }

    public TypeToken<String> type() {
      return TypeToken.of(String.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<Character> characterParser() {
    return CharacterParser.INSTANCE;
  }

  public static OptionParser<String> stringParser() {
    return StringParser.INSTANCE;
  }

  private enum BigIntegerParser implements OptionParser<BigInteger> {
    INSTANCE;

    public BigInteger parse(final Object object) {
      return object instanceof BigInteger ? (BigInteger) object : new BigInteger(object.toString());
    }

    public TypeToken<BigInteger> type() {
      return TypeToken.of(BigInteger.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum BigDecimalParser implements OptionParser<BigDecimal> {
    INSTANCE;

    public BigDecimal parse(final Object object) {
      return object instanceof BigDecimal ? (BigDecimal) object : new BigDecimal(object.toString());
    }

    public TypeToken<BigDecimal> type() {
      return TypeToken.of(BigDecimal.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<BigInteger> bigIntegerParser() {
    return BigIntegerParser.INSTANCE;
  }

  public static OptionParser<BigDecimal> bigDecimalParser() {
    return BigDecimalParser.INSTANCE;
  }

  private enum UnsignedIntegerParser implements OptionParser<UnsignedInteger> {
    INSTANCE;

    public UnsignedInteger parse(final Object object) {
      return object instanceof UnsignedInteger ? (UnsignedInteger) object : UnsignedInteger.valueOf(object.toString());
    }

    public TypeToken<UnsignedInteger> type() {
      return TypeToken.of(UnsignedInteger.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UnsignedLongParser implements OptionParser<UnsignedLong> {
    INSTANCE;

    public UnsignedLong parse(final Object object) {
      return object instanceof UnsignedLong ? (UnsignedLong) object : UnsignedLong.valueOf(object.toString());
    }

    public TypeToken<UnsignedLong> type() {
      return TypeToken.of(UnsignedLong.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<UnsignedInteger> unsignedIntegerParser() {
    return UnsignedIntegerParser.INSTANCE;
  }

  public static OptionParser<UnsignedLong> unsignedLongParser() {
    return UnsignedLongParser.INSTANCE;
  }

  private enum PathParser implements OptionParser<Path> {
    INSTANCE;

    public Path parse(final Object object) {
      return object instanceof Path ? (Path) object : Paths.get(object.toString());
    }

    public TypeToken<Path> type() {
      return TypeToken.of(Path.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UriParser implements OptionParser<URI> {
    INSTANCE;

    public URI parse(final Object object) {
      return object instanceof URI ? (URI) object : newUri(object.toString());
    }

    public TypeToken<URI> type() {
      return TypeToken.of(URI.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  private enum UrlParser implements OptionParser<URL> {
    INSTANCE;

    public URL parse(final Object object) {
      return object instanceof URL ? (URL) object : newUrl(object.toString());
    }

    public TypeToken<URL> type() {
      return TypeToken.of(URL.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<Path> pathParser() {
    return PathParser.INSTANCE;
  }

  public static OptionParser<URI> uriParser() {
    return UriParser.INSTANCE;
  }

  public static OptionParser<URL> urlParser() {
    return UrlParser.INSTANCE;
  }

  private static final class ClassParser implements OptionParser<Class<?>>, Serializable {
    private static final long serialVersionUID = 0L;

    @SuppressWarnings("serial")
    private static final TypeToken<Class<?>> wildcardClassType = new TypeToken<Class<?>>() {};

    private final ClassResolver resolver;

    ClassParser(final ClassResolver resolver) {
      this.resolver = checkNotNull(resolver);
    }

    public Class<?> parse(final Object object) {
      try {
        return object instanceof Class ? (Class<?>) object : this.resolver.forName(object.toString());
      } catch (ClassNotFoundException failure) {
        throw propagate(failure);
      }
    }

    public TypeToken<Class<?>> type() {
      return wildcardClassType;
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<Class<?>> classParser() {
    return new ClassParser(ClassResolvers.getDefault());
  }

  public static OptionParser<Class<?>> classParser(final ClassResolver resolver) {
    return new ClassParser(resolver);
  }
}
