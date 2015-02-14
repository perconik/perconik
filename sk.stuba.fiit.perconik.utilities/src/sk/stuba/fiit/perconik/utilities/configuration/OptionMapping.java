package sk.stuba.fiit.perconik.utilities.configuration;

public interface OptionMapping<T> {
  public String getKey();

  public T getDefaultValue();
}
