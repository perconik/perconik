package sk.stuba.fiit.perconik.core.ui.preferences;

import org.eclipse.swt.widgets.Shell;

import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;

public final class ResourceOptionsDialog extends AbstractOptionsDialog<ResourcePersistenceData> {
  public ResourceOptionsDialog(final Shell parent) {
    super(parent);
  }

  @Override
  String name() {
    return "resource";
  }

  @Override
  void load(final ResourcePersistenceData data) {
    this.setResourceRegistration(data);
  }

  public void setResourceRegistration(final ResourcePersistenceData data) {
    this.setRegistration(data);

    // TODO this.options = ;
  }

  public ResourcePersistenceData getResourceRegistration() {
    return this.getRegistration();
  }
}
