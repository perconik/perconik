package sk.stuba.fiit.perconik.utilities.configuration;

import java.lang.reflect.Field;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.reflect.TypeToken;

import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.asList;
import static com.google.common.collect.Lists.newArrayList;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Configurables {
  @SuppressWarnings("serial")
  private static final TypeToken<OptionMapping<?>> mappingType = new TypeToken<OptionMapping<?>>() {};

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
    return mappings(schema, mappingType);
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
}
