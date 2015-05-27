package sk.stuba.fiit.perconik.utilities.configuration;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import sk.stuba.fiit.perconik.utilities.Optionals;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.not;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.asList;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.filterEntries;

import static sk.stuba.fiit.perconik.utilities.MoreMaps.newHashMap;
import static sk.stuba.fiit.perconik.utilities.MoreSets.newHashSet;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Configurables {
  @SuppressWarnings("serial")
  private static final TypeToken<Entry<String, Object>> rawOptionType = new TypeToken<Entry<String, Object>>() {};

  @SuppressWarnings("serial")
  private static final TypeToken<OptionMapping<?>> wildcardMappingType = new TypeToken<OptionMapping<?>>() {};

  @SuppressWarnings("serial")
  private static final TypeToken<OptionAccessor<?>> wildcardAccessorType = new TypeToken<OptionAccessor<?>>() {};

  private Configurables() {}

  public static MapOptions defaults(final Class<?> schema) {
    return defaults(mappings(schema));
  }

  public static MapOptions defaults(final Class<?> schema, final Class<?> type) {
    return defaults(mappings(schema, type));
  }

  public static MapOptions defaults(final Class<?> schema, final TypeToken<?> type) {
    return defaults(mappings(schema, type));
  }

  public static MapOptions defaults(final Iterable<? extends OptionMapping<?>> mappings) {
    Builder<String, Object> builder = ImmutableMap.builder();

    for (OptionMapping<?> mapping: mappings) {
      Object value = mapping.getDefaultValue();

      if (value != null) {
        builder.put(mapping.getKey(), value);
      }
    }

    return MapOptions.from(builder.build());
  }

  public static List<OptionMapping<?>> mappings(final Class<?> schema) {
    return mappings(schema, Optionals.<OptionMapping<?>, OptionMapping<?>>fromNonnullFunction());
  }

  public static <T> List<OptionMapping<T>> mappings(final Class<?> schema, final Class<T> type) {
    return mappings(schema, TypeToken.of(type));
  }

  public static <T> List<OptionMapping<T>> mappings(final Class<?> schema, final TypeToken<T> type) {
    return mappings(schema, mappingFunction(mappingOf(type), type));
  }

  public static <M extends OptionMapping<?>> List<M> mappings(final Class<?> schema, final Function<? super OptionMapping<?>, Optional<M>> function) {
    List<M> mappings = newArrayList();

    for (Field field: schema.getFields()) {
      int modifiers = field.getModifiers();

      if (!isPublic(modifiers) || !isStatic(modifiers)) {
        continue;
      }

      if (!OptionMapping.class.isAssignableFrom(field.getType())) {
        continue;
      }

      try {
        OptionMapping<?> mapping = (OptionMapping<?>) field.get(null);

        checkState(mapping != null, "%s.%s is null", schema.getName(), field.getName());

        Optional<? extends M> result = function.apply(mapping);

        if (result.isPresent()) {
          mappings.add(result.get());
        }
      } catch (IllegalAccessException failure) {
        propagate(failure);
      }
    }

    return mappings;
  }

  public static List<OptionAccessor<?>> accessors(final Class<?> schema) {
    // potentially unsafe raw cast to List is correct and safe in this case
    return List.class.cast(accessors(schema, Object.class));
  }

  public static <T> List<OptionAccessor<T>> accessors(final Class<?> schema, final Class<T> type) {
    return accessors(schema, TypeToken.of(type));
  }

  public static <T> List<OptionAccessor<T>> accessors(final Class<?> schema, final TypeToken<T> type) {
    return mappings(schema, mappingFunction(accessorOf(type), type));
  }

  public static <M extends OptionMapping<T>, T> Function<OptionMapping<?>, Optional<M>> mappingFunction(final TypeToken<? extends M> map, final Class<T> type) {
    return mappingFunction(map, TypeToken.of(type));
  }

  public static <M extends OptionMapping<T>, T> Function<OptionMapping<?>, Optional<M>> mappingFunction(final TypeToken<? extends M> map, final TypeToken<T> type) {
    return new Function<OptionMapping<?>, Optional<M>>() {
      public Optional<M> apply(@Nonnull final OptionMapping<?> raw) {
        if (map.getRawType().isInstance(raw) && type.isAssignableFrom(raw.getType())) {
          // cast is safe since types were checked already
          @SuppressWarnings("unchecked")
          M mapping = (M) raw;

          return of(mapping);
        }

        return absent();
      }
    };
  }

  public static <M extends Map<String, Object>> M values(final Iterable<? extends OptionAccessor<?>> accessors, final Options source, final M destination) {
    return values(accessors, newReader(source), destination);
  }

  public static <M extends Map<String, Object>> M values(final Iterable<? extends OptionAccessor<?>> accessors, final Options source, final Supplier<? extends M> destination) {
    return values(accessors, source, destination.get());
  }

  public static <M extends Map<String, Object>> M values(final Iterable<? extends OptionAccessor<?>> accessors, final OptionsReader source, final M destination) {
    for (OptionAccessor<?> accessor: accessors) {
      destination.put(accessor.getKey(), accessor.getValue(source));
    }

    return destination;
  }

  public static <M extends Map<String, Object>> M values(final Iterable<? extends OptionAccessor<?>> accessors, final OptionsReader source, final Supplier<? extends M> destination) {
    return values(accessors, source, destination.get());
  }

  public static <M extends Map<String, Object>> M rawValues(final Iterable<? extends OptionAccessor<?>> accessors, final Options source, final M destination) {
    return rawValues(accessors, newReader(source), destination);
  }

  public static <M extends Map<String, Object>> M rawValues(final Iterable<? extends OptionAccessor<?>> accessors, final Options source, final Supplier<? extends M> destination) {
    return rawValues(accessors, source, destination.get());
  }

  public static <M extends Map<String, Object>> M rawValues(final Iterable<? extends OptionAccessor<?>> accessors, final OptionsReader source, final M destination) {
    for (OptionAccessor<?> accessor: accessors) {
      destination.put(accessor.getKey(), accessor.getRawValue(source));
    }

    return destination;
  }

  public static <M extends Map<String, Object>> M rawValues(final Iterable<? extends OptionAccessor<?>> accessors, final OptionsReader source, final Supplier<? extends M> destination) {
    return rawValues(accessors, source, destination.get());
  }

  public static <T> OptionMapping<T> option(final TypeToken<T> type, final String key) {
    return option(type, key, null);
  }

  public static <T> OptionMapping<T> option(final TypeToken<T> type, final String key, @Nullable final T defaultValue) {
    return new SimpleOptionMapping<>(type, key, defaultValue);
  }

  public static <T> OptionAccessor<T> option(final OptionParser<T> parser, final String key) {
    return option(parser, key, null);
  }

  public static <T> OptionAccessor<T> option(final OptionParser<T> parser, final String key, @Nullable final T defaultValue) {
    return new RegularOptionAccessor<>(parser.type(), parser, key, defaultValue);
  }

  public static <T> OptionAccessor<T> option(final OptionParser<? extends T> parser, final OptionMapping<T> mapping) {
    return option(mapping.getType(), parser, mapping.getKey(), mapping.getDefaultValue());
  }

  public static <T> OptionAccessor<T> option(final TypeToken<T> type, final OptionParser<? extends T> parser, final String key) {
    return option(type, parser, key, null);
  }

  public static <T> OptionAccessor<T> option(final TypeToken<T> type, final OptionParser<? extends T> parser, final String key, @Nullable final T defaultValue) {
    return new RegularOptionAccessor<>(type, parser, key, defaultValue);
  }

  public static <E extends Entry<String, Object>> Equivalence<E> optionEquivalence() {
    @SuppressWarnings("unchecked")
    Equivalence<E> equivalence = (Equivalence<E>) OptionEquivalence.INSTANCE;

    return equivalence;
  }

  public static Equivalence<String> optionKeyEquivalence() {
    return OptionEquivalence.KeyEquivalence.INSTANCE;
  }

  public static <T> Equivalence<T> optionValueEquivalence() {
    @SuppressWarnings("unchecked")
    Equivalence<T> equivalence = (Equivalence<T>) OptionEquivalence.ValueEquivalence.INSTANCE;

    return equivalence;
  }

  public static OptionsReader newReader(final Options options) {
    return new RegularOptionsReader(options);
  }

  public static OptionsWriter newWriter(final Options options) {
    return new RegularOptionsWriter(options);
  }

  public static MapOptions emptyOptions() {
    return MapOptions.empty();
  }

  public static Options compound(final Options primary, final Options secondary) {
    return new CompoundOptions(primary, secondary);
  }

  public static Options compound(final Options primary, final Options secondary, final Options ... rest) {
    return new CompoundOptions(asList(primary, secondary, rest));
  }

  public static Options compound(final Iterable<? extends Options> options) {
    return new CompoundOptions(options);
  }

  public static Options fromMap(@Nullable final Map<String, Object> map) {
    return map != null ? MapOptions.from(map) : emptyOptions();
  }

  public static Map<String, Object> toMap(@Nullable final Options options) {
    return options != null ? options.toMap() : Collections.<String, Object>emptyMap();
  }

  static Predicate<Entry<String, Object>> rawOptionKeyMatcher(final Equivalence<String> keyEquivalence, final Iterable<String> matchingKeys) {
    final Set<Equivalence.Wrapper<String>> matches = newHashSet(keyEquivalence, matchingKeys);

    return new Predicate<Entry<String, Object>>() {
      public boolean apply(@Nonnull final Entry<String, Object> rawOption) {
        return matches.contains(keyEquivalence.wrap(rawOption.getKey()));
      }
    };
  }

  public static Map<String, Object> knownRawOptions(final Map<String, Object> rawOptions, final Iterable<String> knownKeys) {
    return knownRawOptions(rawOptions, knownKeys, optionKeyEquivalence());
  }

  public static Map<String, Object> knownRawOptions(final Map<String, Object> rawOptions, final Iterable<String> knownKeys, final Equivalence<String> keyEquivalence) {
    return filterEntries(rawOptions, rawOptionKeyMatcher(keyEquivalence, knownKeys));
  }

  public static Map<String, Object> unknownRawOptions(final Map<String, Object> rawOptions, final Iterable<String> knownKeys) {
    return unknownRawOptions(rawOptions, knownKeys, optionKeyEquivalence());
  }

  public static Map<String, Object> unknownRawOptions(final Map<String, Object> rawOptions, final Iterable<String> knownKeys, final Equivalence<String> keyEquivalence) {
    return filterEntries(rawOptions, not(rawOptionKeyMatcher(keyEquivalence, knownKeys)));
  }

  static Predicate<Entry<String, Object>> rawOptionMatcher(final Equivalence<String> keyEquivalence, final Equivalence<Object> valueEquivalence, final Map<String, Object> matchingRawOptions) {
    final Map<Equivalence.Wrapper<String>, Object> matches = newHashMap(keyEquivalence, matchingRawOptions.entrySet());

    return new Predicate<Entry<String, Object>>() {
      public boolean apply(@Nonnull final Entry<String, Object> rawOption) {
        Object value = matches.get(keyEquivalence.wrap(rawOption.getKey()));

        return valueEquivalence.equivalent(rawOption.getValue(), value);
      }
    };
  }

  public static Map<String, Object> inheritedRawOptions(final Map<String, Object> rawOptions, final Map<String, Object> parentRawOptions) {
    return inheritedRawOptions(rawOptions, parentRawOptions, optionKeyEquivalence(), optionValueEquivalence());
  }

  public static Map<String, Object> inheritedRawOptions(final Map<String, Object> rawOptions, final Map<String, Object> parentRawOptions, final Equivalence<String> keyEquivalence, final Equivalence<Object> valueEquivalence) {
    return filterEntries(rawOptions, rawOptionMatcher(keyEquivalence, valueEquivalence, parentRawOptions));
  }

  public static Map<String, Object> customRawOptions(final Map<String, Object> rawOptions, final Map<String, Object> parentRawOptions) {
    return customRawOptions(rawOptions, parentRawOptions, optionKeyEquivalence(), optionValueEquivalence());
  }

  public static Map<String, Object> customRawOptions(final Map<String, Object> rawOptions, final Map<String, Object> parentRawOptions, final Equivalence<String> keyEquivalence, final Equivalence<Object> valueEquivalence) {
    return filterEntries(rawOptions, not(rawOptionMatcher(keyEquivalence, valueEquivalence, parentRawOptions)));
  }

  public static <T> TypeToken<OptionMapping<T>> mappingOf(final Class<T> type) {
    return mappingOf(TypeToken.of(type));
  }

  public static <T> TypeToken<OptionMapping<T>> mappingOf(final TypeToken<T> type) {
    @SuppressWarnings("serial")
    TypeToken<OptionMapping<T>> token = new TypeToken<OptionMapping<T>>() {}.where(new TypeParameter<T>() {}, type);

    return token;
  }

  public static <T> TypeToken<OptionAccessor<T>> accessorOf(final Class<T> type) {
    return accessorOf(TypeToken.of(type));
  }

  public static <T> TypeToken<OptionAccessor<T>> accessorOf(final TypeToken<T> type) {
    @SuppressWarnings("serial")
    TypeToken<OptionAccessor<T>> token = new TypeToken<OptionAccessor<T>>() {}.where(new TypeParameter<T>() {}, type);

    return token;
  }

  public static TypeToken<Entry<String, Object>> rawOptionType() {
    return rawOptionType;
  }

  public static TypeToken<OptionMapping<?>> wildcardMappingType() {
    return wildcardMappingType;
  }

  public static TypeToken<OptionAccessor<?>> wildcardAccessorType() {
    return wildcardAccessorType;
  }
}
