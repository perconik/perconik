package sk.stuba.fiit.perconik.core.preferences;

import java.util.Map;

public interface Configuration {
  public void fromMap(Map<String, Object> map);

  public Map<String, Object> toMap();
}
