package sk.stuba.fiit.perconik.utilities.configuration;

import com.google.common.reflect.TypeToken;

final class SimpleOptionMapping<T> extends AbstractOptionMapping<T> {
  SimpleOptionMapping(final TypeToken<T> type, final String key, final T defaultValue) {
    super(type, key, defaultValue);
  }
}
