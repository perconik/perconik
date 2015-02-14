package sk.stuba.fiit.perconik.utilities.configuration;

import java.util.Map;

import com.google.common.collect.ForwardingObject;

public abstract class ForwardingOptions extends ForwardingObject implements Options {
  protected ForwardingOptions() {}

  @Override
  protected abstract Options delegate();

  public void fromMap(final Map<String, Object> map) {
    this.delegate().fromMap(map);
  }

  public Map<String, Object> toMap() {
    return this.delegate().toMap();
  }

  public Object put(final String key, final Object value) {
    return this.delegate().put(key, value);
  }

  public Object get(final String key) {
    return this.delegate().get(key);
  }
}
