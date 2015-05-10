package sk.stuba.fiit.perconik.eclipse.jface.preference;

import org.eclipse.jface.preference.IPreferenceStore;

import sk.stuba.fiit.perconik.utilities.configuration.AbstractOptionsReader;

public abstract class AbstractPreferenceStoreOptionsReader extends AbstractOptionsReader {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractPreferenceStoreOptionsReader() {}

  @Override
  protected abstract PreferenceStoreOptions options();

  protected abstract Object fromStringToRaw(String value);

  @Override
  public Object getRaw(final String key) {
    IPreferenceStore store = this.options().getPreferenceStore();

    return this.fromStringToRaw(store.getString(key));
  }
}
