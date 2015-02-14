package sk.stuba.fiit.perconik.utilities.configuration;

final class SimpleOptionMapping<T> extends AbstractOptionMapping<T> {
  SimpleOptionMapping(final String key, final T defaultValue) {
    super(key, defaultValue);
  }
}
