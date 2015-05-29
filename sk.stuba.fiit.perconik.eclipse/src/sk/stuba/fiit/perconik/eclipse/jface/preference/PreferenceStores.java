package sk.stuba.fiit.perconik.eclipse.jface.preference;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Function;

import org.eclipse.jface.preference.IPreferenceStore;

public final class PreferenceStores {
  private PreferenceStores() {}

  public static final void put(final IPreferenceStore store, final Map<String, ?> preferences, final Function<Object, String> converter) {
    for (Entry<String, ?> entry: preferences.entrySet()) {
      store.putValue(entry.getKey(), converter.apply(entry.getValue()));
    }
  }

  public static final void set(final IPreferenceStore store, final Map<String, ?> preferences, final Function<Object, String> converter) {
    for (Entry<String, ?> entry: preferences.entrySet()) {
      store.setValue(entry.getKey(), converter.apply(entry.getValue()));
    }
  }

  public static final void setDefault(final IPreferenceStore store, final Map<String, ?> preferences, final Function<Object, String> converter) {
    for (Entry<String, ?> entry: preferences.entrySet()) {
      store.setDefault(entry.getKey(), converter.apply(entry.getValue()));
    }
  }
}
