package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Shell;

import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;
import sk.stuba.fiit.perconik.core.preferences.ResourcePreferences;
import sk.stuba.fiit.perconik.core.ui.plugin.Activator;
import sk.stuba.fiit.perconik.utilities.configuration.Configurable;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

public final class ResourceOptionsDialog extends AbstractOptionsDialog<ResourcePreferences, ResourcePersistenceData> {
  public ResourceOptionsDialog(final Shell parent) {
    super(parent);
  }

  @Override
  String name() {
    return "resource";
  }

  private Options options() {
    return this.getPreferences().getResourceConfigurationData().get(this.getRegistration().getResourceName());
  }

  @Override
  void apply() {
    ResourcePreferences preferences = this.getPreferences();

    Map<String, Options> data = preferences.getResourceConfigurationData();
    String name = this.getRegistration().getResourceName();
    Options options = writeToOptions(this.options(), this.map);

    preferences.setResourceConfigurationData(updateData(data, name, options));
  }

  @Override
  void load(final ResourcePreferences preferences, final ResourcePersistenceData registration) {

    this.setResourceRegistration(registration);
  }

  public void setResourcePreferences(final ResourcePreferences preferences) {
    this.setPreferences(preferences);
  }

  public void setResourceRegistration(final ResourcePersistenceData registration) {
    this.setRegistration(registration);

    this.map = readFromOptions(this.options());

    if (!(registration.getResource() instanceof Configurable)) {
      this.updateStatus(new Status(IStatus.WARNING, Activator.PLUGIN_ID, IStatus.OK, "Resource is not configurable by default, it may ignore specified options", null));
    }
  }

  public ResourcePreferences getResourcePreferences() {
    return this.getPreferences();
  }

  public ResourcePersistenceData getResourceRegistration() {
    return this.getRegistration();
  }
}
