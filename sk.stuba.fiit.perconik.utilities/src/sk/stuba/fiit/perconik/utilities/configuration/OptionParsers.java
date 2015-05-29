package sk.stuba.fiit.perconik.utilities.configuration;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Splitter;
import com.google.common.base.Supplier;
import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedLong;
import com.google.common.reflect.TypeToken;

import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolvers;

import static java.lang.Integer.parseInt;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.trimLeading;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.trimTrailing;
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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
      return toStringHelper(this).add("type", this.type()).toString();
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

  private enum InetSocketAddressParser implements OptionParser<InetSocketAddress> {
    INSTANCE;

    public InetSocketAddress parse(final Object object) {
      if (object instanceof InetSocketAddress) {
        return (InetSocketAddress) object;
      }

      Iterator<String> parts = Splitter.on(':').limit(2).split(object.toString()).iterator();

      return new InetSocketAddress(parts.next(), parseInt(parts.next()));
    }

    public TypeToken<InetSocketAddress> type() {
      return TypeToken.of(InetSocketAddress.class);
    }

    @Override
    public String toString() {
      return toStringHelper(this).add("type", this.type()).toString();
    }
  }

  public static OptionParser<InetSocketAddress> inetSocketAddressParser() {
    return InetSocketAddressParser.INSTANCE;
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
      return toStringHelper(this).add("type", this.type()).add("resolver", this.resolver).toString();
    }
  }

  public static OptionParser<Class<?>> classParser() {
    return new ClassParser(ClassResolvers.getDefault());
  }

  public static OptionParser<Class<?>> classParser(final ClassResolver resolver) {
    return new ClassParser(resolver);
  }

  private static abstract class CollectionParser<C extends Collection<E>, E> implements OptionParser<C>, Serializable {
    private static final long serialVersionUID = 0L;

    private final OptionParser<? extends E> elementParser;

    private final String elementSeparator;

    private final String prefix;

    private final String suffix;

    CollectionParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
      this.elementParser = checkNotNull(elementParser);
      this.elementSeparator = checkNotNull(elementSeparator);
      this.prefix = checkNotNull(prefix);
      this.suffix = checkNotNull(suffix);
    }

    abstract C newCollection();

    public C parse(final Object object) {
      C result = this.newCollection();

      if (object instanceof Iterable) {
        Iterable<?> inputs = (Iterable<?>) object;

        for (Object input: inputs) {
          result.add(this.elementParser.parse(input));
        }

        return result;
      }

      String value = trimTrailing(trimLeading(object.toString(), this.prefix), this.suffix);

      for (String input: Splitter.on(this.elementSeparator).split(value)) {
        result.add(this.elementParser.parse(input));
      }

      return result;
    }

    public TypeToken<C> type() {
      @SuppressWarnings("serial")
      TypeToken<C> type = new TypeToken<C>(this.getClass()) {};

      return type;
    }

    @Override
    public String toString() {
      ToStringHelper helper = toStringHelper(this);

      helper.add("type", this.type());
      helper.add("elementParser", this.elementParser);
      helper.add("elementSeparator", this.elementSeparator);
      helper.add("prefix", this.prefix);
      helper.add("suffix", this.suffix);

      return helper.toString();
    }
  }

  @SuppressWarnings("serial")
  private static final class CollectionParsers {
    static final String COLLECTION_PREFIX = "[";

    static final String COLLECTION_SUFFIX = "]";

    static final String ELEMENT_SEPARATOR = ", ";

    private CollectionParsers() {}

    static <C extends Collection<E>, E> CollectionParser<C, E> collectionParser(final Supplier<? extends C> supplier, final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
      return new CollectionParser<C, E>(elementParser, elementSeparator, prefix, suffix) {
        @Override
        C newCollection() {
          return supplier.get();
        }
      };
    }

    static <E> CollectionParser<ArrayList<E>, E> arrayListParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
      return new CollectionParser<ArrayList<E>, E>(elementParser, elementSeparator, prefix, suffix) {
        @Override
        ArrayList<E> newCollection() {
          return newArrayList();
        }
      };
    }

    static <E> CollectionParser<LinkedList<E>, E> linkedListParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
      return new CollectionParser<LinkedList<E>, E>(elementParser, elementSeparator, prefix, suffix) {
        @Override
        LinkedList<E> newCollection() {
          return newLinkedList();
        }
      };
    }

    static <E> CollectionParser<HashSet<E>, E> hashSetParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
      return new CollectionParser<HashSet<E>, E>(elementParser, elementSeparator, prefix, suffix) {
        @Override
        HashSet<E> newCollection() {
          return newHashSet();
        }
      };
    }

    static <E> CollectionParser<LinkedHashSet<E>, E> linkedHashSetParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
      return new CollectionParser<LinkedHashSet<E>, E>(elementParser, elementSeparator, prefix, suffix) {
        @Override
        LinkedHashSet<E> newCollection() {
          return newLinkedHashSet();
        }
      };
    }
  }

  public static <C extends Collection<E>, E> OptionParser<C> collectionParser(final Supplier<? extends C> supplier, final OptionParser<E> elementParser) {
    return CollectionParsers.collectionParser(supplier, elementParser, CollectionParsers.ELEMENT_SEPARATOR, CollectionParsers.COLLECTION_PREFIX, CollectionParsers.COLLECTION_SUFFIX);
  }

  public static <C extends Collection<E>, E> OptionParser<C> collectionParser(final Supplier<? extends C> supplier, final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
    return CollectionParsers.collectionParser(supplier, elementParser, elementSeparator, prefix, suffix);
  }

  public static <E> OptionParser<ArrayList<E>> arrayListParser(final OptionParser<E> elementParser) {
    return CollectionParsers.arrayListParser(elementParser, CollectionParsers.ELEMENT_SEPARATOR, CollectionParsers.COLLECTION_PREFIX, CollectionParsers.COLLECTION_SUFFIX);
  }

  public static <E> OptionParser<ArrayList<E>> arrayListParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
    return CollectionParsers.arrayListParser(elementParser, elementSeparator, prefix, suffix);
  }

  public static <E> OptionParser<LinkedList<E>> linkedListParser(final OptionParser<E> elementParser) {
    return CollectionParsers.linkedListParser(elementParser, CollectionParsers.ELEMENT_SEPARATOR, CollectionParsers.COLLECTION_PREFIX, CollectionParsers.COLLECTION_SUFFIX);
  }

  public static <E> OptionParser<LinkedList<E>> linkedListParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
    return CollectionParsers.linkedListParser(elementParser, elementSeparator, prefix, suffix);
  }

  public static <E> OptionParser<HashSet<E>> hashSetParser(final OptionParser<E> elementParser) {
    return CollectionParsers.hashSetParser(elementParser, CollectionParsers.ELEMENT_SEPARATOR, CollectionParsers.COLLECTION_PREFIX, CollectionParsers.COLLECTION_SUFFIX);
  }

  public static <E> OptionParser<HashSet<E>> hashSetParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
    return CollectionParsers.hashSetParser(elementParser, elementSeparator, prefix, suffix);
  }

  public static <E> OptionParser<LinkedHashSet<E>> linkedHashSetParser(final OptionParser<E> elementParser) {
    return CollectionParsers.linkedHashSetParser(elementParser, CollectionParsers.ELEMENT_SEPARATOR, CollectionParsers.COLLECTION_PREFIX, CollectionParsers.COLLECTION_SUFFIX);
  }

  public static <E> OptionParser<LinkedHashSet<E>> linkedHashSetParser(final OptionParser<? extends E> elementParser, final String elementSeparator, final String prefix, final String suffix) {
    return CollectionParsers.linkedHashSetParser(elementParser, elementSeparator, prefix, suffix);
  }

  private static abstract class MapParser<M extends Map<K, V>, K, V> implements OptionParser<M>, Serializable {
    private static final long serialVersionUID = 0L;

    private final OptionParser<? extends K> keyParser;

    private final OptionParser<? extends V> valueParser;

    private final String entrySeparator;

    private final String keyValueSeparator;

    private final String prefix;

    private final String suffix;

    MapParser(final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser, final String entrySeparator, final String keyValueSeparator, final String prefix, final String suffix) {
      this.keyParser = checkNotNull(keyParser);
      this.valueParser = checkNotNull(valueParser);
      this.entrySeparator = checkNotNull(entrySeparator);
      this.keyValueSeparator = checkNotNull(keyValueSeparator);
      this.prefix = checkNotNull(prefix);
      this.suffix = checkNotNull(suffix);
    }

    abstract M newMap();

    public M parse(final Object object) {
      M result = this.newMap();

      if (object instanceof Map || object instanceof Iterable) {
        Iterable<?> inputs = object instanceof Map ? ((Map<?, ?>) object).entrySet() : (Iterable<?>) object;

        for (Object input: inputs) {
          if (input instanceof Entry) {
            Entry<?, ?> entry = (Entry<?, ?>) input;

            result.put(this.keyParser.parse(entry.getKey()), this.valueParser.parse(entry.getValue()));

            continue;
          }

          String value = input.toString();

          Iterator<?> parts = Splitter.on(this.keyValueSeparator).limit(2).split(value).iterator();

          result.put(this.keyParser.parse(parts.next()), this.valueParser.parse(parts.next()));
        }

        return result;
      }

      String value = trimTrailing(trimLeading(object.toString(), this.prefix), this.suffix);

      for (Entry<?, ?> input: Splitter.on(this.entrySeparator).withKeyValueSeparator(this.keyValueSeparator).split(value).entrySet()) {
        result.put(this.keyParser.parse(input.getKey()), this.valueParser.parse(input.getValue()));
      }

      return result;
    }

    public TypeToken<M> type() {
      @SuppressWarnings("serial")
      TypeToken<M> type = new TypeToken<M>(this.getClass()) {};

      return type;
    }

    @Override
    public String toString() {
      ToStringHelper helper = toStringHelper(this);

      helper.add("type", this.type());
      helper.add("keyParser", this.keyParser);
      helper.add("valueParser", this.valueParser);
      helper.add("entrySeparator", this.entrySeparator);
      helper.add("keyValueSeparator", this.keyValueSeparator);
      helper.add("prefix", this.prefix);
      helper.add("suffix", this.suffix);

      return helper.toString();
    }
  }

  @SuppressWarnings("serial")
  private static final class MapParsers {
    static final String MAP_PREFIX = "[";

    static final String MAP_SUFFIX = "]";

    static final String ENTRY_SEPARATOR = ", ";

    static final String KEY_VALUE_SEPARATOR = "=";

    private MapParsers() {}

    static <M extends Map<K, V>, K, V> MapParser<M, K, V> mapParser(final Supplier<? extends M> supplier, final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser, final String entrySeparator, final String keyValueSeparator, final String prefix, final String suffix) {
      return new MapParser<M, K, V>(keyParser, valueParser, entrySeparator, keyValueSeparator, prefix, suffix) {
        @Override
        M newMap() {
          return supplier.get();
        }
      };
    }

    static <K, V> MapParser<HashMap<K, V>, K, V> hashMapParser(final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser, final String entrySeparator, final String keyValueSeparator, final String prefix, final String suffix) {
      return new MapParser<HashMap<K, V>, K, V>(keyParser, valueParser, entrySeparator, keyValueSeparator, prefix, suffix) {
        @Override
        HashMap<K, V> newMap() {
          return newHashMap();
        }
      };
    }

    static <K, V> MapParser<LinkedHashMap<K, V>, K, V> linkedHashMapParser(final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser, final String entrySeparator, final String keyValueSeparator, final String prefix, final String suffix) {
      return new MapParser<LinkedHashMap<K, V>, K, V>(keyParser, valueParser, entrySeparator, keyValueSeparator, prefix, suffix) {
        @Override
        LinkedHashMap<K, V> newMap() {
          return newLinkedHashMap();
        }
      };
    }
  }

  public static <M extends Map<K, V>, K, V> OptionParser<M> mapParser(final Supplier<? extends M> supplier, final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser) {
    return MapParsers.mapParser(supplier, keyParser, valueParser, MapParsers.ENTRY_SEPARATOR, MapParsers.KEY_VALUE_SEPARATOR, MapParsers.MAP_PREFIX, MapParsers.MAP_SUFFIX);
  }

  public static <M extends Map<K, V>, K, V> OptionParser<M> mapParser(final Supplier<? extends M> supplier, final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser, final String entrySeparator, final String keyValueSeparator, final String prefix, final String suffix) {
    return MapParsers.mapParser(supplier, keyParser, valueParser, entrySeparator, keyValueSeparator, prefix, suffix);
  }

  public static <K, V> OptionParser<HashMap<K, V>> hashMapParser(final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser) {
    return MapParsers.hashMapParser(keyParser, valueParser, MapParsers.ENTRY_SEPARATOR, MapParsers.KEY_VALUE_SEPARATOR, MapParsers.MAP_PREFIX, MapParsers.MAP_SUFFIX);
  }

  public static <K, V> OptionParser<HashMap<K, V>> hashMapParser(final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser, final String entrySeparator, final String keyValueSeparator, final String prefix, final String suffix) {
    return MapParsers.hashMapParser(keyParser, valueParser, entrySeparator, keyValueSeparator, prefix, suffix);
  }

  public static <K, V> OptionParser<LinkedHashMap<K, V>> linkedHashMapParser(final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser) {
    return MapParsers.linkedHashMapParser(keyParser, valueParser, MapParsers.ENTRY_SEPARATOR, MapParsers.KEY_VALUE_SEPARATOR, MapParsers.MAP_PREFIX, MapParsers.MAP_SUFFIX);
  }

  public static <K, V> OptionParser<LinkedHashMap<K, V>> linkedHashMapParser(final OptionParser<? extends K> keyParser, final OptionParser<? extends V> valueParser, final String entrySeparator, final String keyValueSeparator, final String prefix, final String suffix) {
    return MapParsers.linkedHashMapParser(keyParser, valueParser, entrySeparator, keyValueSeparator, prefix, suffix);
  }
}
