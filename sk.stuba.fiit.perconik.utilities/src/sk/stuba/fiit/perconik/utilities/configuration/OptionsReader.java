package sk.stuba.fiit.perconik.utilities.configuration;

public interface OptionsReader {
  public <T> T get(OptionParser<? extends T> parser, String key);

  public Object getRaw(String key);
}
