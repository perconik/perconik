package sk.stuba.fiit.perconik.eclipse.jface.preference;

import org.eclipse.jface.preference.IPreferenceStore;

import sk.stuba.fiit.perconik.utilities.configuration.Options;

public interface PreferenceStoreOptions extends Options {
  public IPreferenceStore getPreferenceStore();
}
