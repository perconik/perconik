package sk.stuba.fiit.perconik.utilities.configuration;

import java.util.Map;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public interface Options {
  public void fromMap(Map<String, Object> map);

  public Map<String, Object> toMap();
}
