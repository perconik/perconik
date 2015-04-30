package sk.stuba.fiit.perconik.eclipse.jface.preference;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.preference.IPreferenceStore;

public final class PreferenceStores {
  private PreferenceStores() {}

  public static final void put(final IPreferenceStore store, final Map<String, ?> preferences) {
    for (Entry<String, ?> entry: preferences.entrySet()) {
      store.putValue(entry.getKey(), entry.getValue().toString());
    }
  }

  public static final void set(final IPreferenceStore store, final Map<String, ?> preferences) {
    for (Entry<String, ?> entry: preferences.entrySet()) {
      store.setValue(entry.getKey(), entry.getValue().toString());
    }
  }

  public static final void setDefault(final IPreferenceStore store, final Map<String, ?> preferences) {
    for (Entry<String, ?> entry: preferences.entrySet()) {
      store.setDefault(entry.getKey(), entry.getValue().toString());
    }
  }
}
