package sk.stuba.fiit.perconik.eclipse.jface.preference;

import javax.annotation.Nullable;

import org.eclipse.jface.preference.IPreferenceStore;

import sk.stuba.fiit.perconik.utilities.configuration.AbstractOptionsWriter;

public abstract class AbstractPreferenceStoreOptionsWriter extends AbstractOptionsWriter {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractPreferenceStoreOptionsWriter() {}

  @Override
  protected abstract PreferenceStoreOptions options();

  protected abstract String fromRawToString(Object value);

  @Override
  public Object putRaw(final String key, @Nullable final Object value) {
    IPreferenceStore store = this.options().getPreferenceStore();

    Object previous = store.getString(key);

    store.setValue(key, this.fromRawToString(value));

    return previous;
  }
}
