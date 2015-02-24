package sk.stuba.fiit.perconik.utilities.configuration;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Equivalence;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.reflect.TypeToken;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;

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

  public static MapOptions defaults(final Class<?> schema, final Class<? extends OptionMapping<?>> type) {
    return defaults(mappings(schema, type));
  }

  public static MapOptions defaults(final Class<?> schema, final TypeToken<? extends OptionMapping<?>> type) {
    return defaults(mappings(schema, type));
  }

  public static MapOptions defaults(final Iterable<? extends OptionMapping<?>> mappings) {
    Builder<String, Object> builder = ImmutableMap.builder();

    for (OptionMapping<?> mapping: mappings) {
      builder.put(mapping.getKey(), mapping.getDefaultValue());
    }

    return MapOptions.from(builder.build());
  }

  public static List<OptionMapping<?>> mappings(final Class<?> schema) {
    return mappings(schema, wildcardMappingType);
  }

  public static <T extends OptionMapping<?>> List<T> mappings(final Class<?> schema, final Class<T> type) {
    return mappings(schema, TypeToken.of(type));
  }

  public static <T extends OptionMapping<?>> List<T> mappings(final Class<?> schema, final TypeToken<T> type) {
    List<T> mappings = newArrayList();

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

        if (type.isAssignableFrom(mapping.getClass())) {
          @SuppressWarnings("unchecked")
          T cast = (T) mapping;

          mappings.add(cast);
        }
      } catch (IllegalAccessException failure) {
        propagate(failure);
      }
    }

    return mappings;
  }

  public static Map<String, Object> values(final Iterable<? extends OptionAccessor<?>> accessors, final Options source, final Map<String, Object> destination) {
    return values(accessors, newReader(source), destination);
  }

  public static Map<String, Object> values(final Iterable<? extends OptionAccessor<?>> accessors, final OptionsReader source, final Map<String, Object> destination) {
    for (OptionAccessor<?> accessor: accessors) {
      destination.put(accessor.getKey(), accessor.getValue(source));
    }

    return destination;
  }

  public static Map<String, Object> rawValues(final Iterable<? extends OptionAccessor<?>> accessors, final Options source, final Map<String, Object> destination) {
    return rawValues(accessors, newReader(source), destination);
  }

  public static Map<String, Object> rawValues(final Iterable<? extends OptionAccessor<?>> accessors, final OptionsReader source, final Map<String, Object> destination) {
    for (OptionAccessor<?> accessor: accessors) {
      destination.put(accessor.getKey(), accessor.getRawValue(source));
    }

    return destination;
  }

  public static <T> OptionMapping<T> option(final String key) {
    return option(key, null);
  }

  public static <T> OptionMapping<T> option(final String key, @Nullable final T defaultValue) {
    return new SimpleOptionMapping<>(key, defaultValue);
  }

  public static <T> OptionAccessor<T> option(final OptionParser<? extends T> parser, final String key) {
    return option(parser, key, null);
  }

  public static <T> OptionAccessor<T> option(final OptionParser<? extends T> parser, final String key, @Nullable final T defaultValue) {
    return new RegularOptionAccessor<>(parser, key, defaultValue);
  }

  public static <T> OptionAccessor<T> option(final OptionParser<? extends T> parser, final OptionMapping<T> mapping) {
    return new RegularOptionAccessor<>(parser, mapping.getKey(), mapping.getDefaultValue());
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

  static Predicate<Entry<String, Object>> rawOptionKeyMatcher(final Equivalence<String> keyEquivalence, final Iterable<String> matchingKeys) {
    final Set<Equivalence.Wrapper<String>> matches = newHashSet(keyEquivalence, matchingKeys);

    return new Predicate<Entry<String, Object>>() {
      public boolean apply(final Entry<String, Object> rawOption) {
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
      public boolean apply(final Entry<String, Object> rawOption) {
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
