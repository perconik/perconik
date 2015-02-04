package sk.stuba.fiit.perconik.utilities.configuration;

import java.util.Map;

public abstract class AbstractOptions implements Options {
  protected AbstractOptions() {}

  public void fromMap(final Map<String, Object> map) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return this.toMap().toString();
  }

  public Object put(final String key, final Object value) {
    throw new UnsupportedOperationException();
  }

  public Object get(final String key) {
    return this.toMap().get(key);
  }
}
