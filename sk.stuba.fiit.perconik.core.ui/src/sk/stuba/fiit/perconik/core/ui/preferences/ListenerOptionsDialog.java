package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Map;

import org.eclipse.swt.widgets.Shell;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;
import sk.stuba.fiit.perconik.core.preferences.ListenerPreferences;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

public final class ListenerOptionsDialog extends AbstractOptionsDialog<ListenerPreferences, ListenerPersistenceData> {
  public ListenerOptionsDialog(final Shell parent) {
    super(parent);
  }

  @Override
  String name() {
    return "listener";
  }

  private Options options() {
    return this.getPreferences().getListenerConfigurationData().get(this.getRegistration().getListenerClass());
  }

  @Override
  void apply() {
    ListenerPreferences preferences = this.getPreferences();

    Map<Class<? extends Listener>, Options> data = preferences.getListenerConfigurationData();
    Class<? extends Listener> implementation = this.getRegistration().getListenerClass();
    Options options = writeToOptions(this.options(), this.map);

    preferences.setListenerConfigurationData(updateData(data, implementation, options));
  }

  @Override
  void load(final ListenerPreferences preferences, final ListenerPersistenceData registration) {
    this.setListenerPreferences(preferences);
    this.setListenerRegistration(registration);
  }

  public void setListenerPreferences(final ListenerPreferences preferences) {
    this.setPreferences(preferences);
  }

  public void setListenerRegistration(final ListenerPersistenceData registration) {
    this.setRegistration(registration);
    this.updateStatusBy(registration.getListener());

    this.map = readFromOptions(this.options());
  }

  public ListenerPreferences getListenerPreferences() {
    return this.getPreferences();
  }

  public ListenerPersistenceData getListenerRegistration() {
    return this.getRegistration();
  }
}
