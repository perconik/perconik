package sk.stuba.fiit.perconik.eclipse.jface.preference;

import javax.annotation.Nullable;

import org.eclipse.jface.preference.IPreferenceStore;

import sk.stuba.fiit.perconik.utilities.configuration.AbstractOptionsReader;

import static com.google.common.base.Strings.emptyToNull;

public abstract class AbstractPreferenceStoreOptionsReader extends AbstractOptionsReader {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractPreferenceStoreOptionsReader() {}

  @Override
  protected abstract PreferenceStoreOptions options();

  protected abstract Object fromStringToRaw(@Nullable String value);

  @Override
  public Object getRaw(final String key) {
    IPreferenceStore store = this.options().getPreferenceStore();

    return this.fromStringToRaw(emptyToNull(store.getString(key)));
  }
}
