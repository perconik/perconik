package sk.stuba.fiit.perconik.utilities.configuration;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import static com.google.common.collect.Maps.newLinkedHashMap;

final class CompoundOptions extends AbstractOptions implements Serializable {
  private static final long serialVersionUID = 0L;

  ImmutableList<Options> options;

  CompoundOptions(final Options primary, final Options secondary) {
    this.options = ImmutableList.of(primary, secondary);
  }

  CompoundOptions(final Iterable<? extends Options> options) {
    this.options = ImmutableList.copyOf(options);
  }

  public Map<String, Object> toMap() {
    Map<String, Object> map = newLinkedHashMap();

    for (Options options: this.options) {
      map.putAll(options.toMap());
    }

    return ImmutableMap.copyOf(map);
  }

  @Override
  public Object get(final String key) {
    for (Options options: this.options) {
      Object result = options.get(key);

      if (result != null) {
        return result;
      }
    }

    return null;
  }
}
