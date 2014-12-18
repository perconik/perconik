package sk.stuba.fiit.perconik.core.ui.preferences;

import org.eclipse.swt.widgets.Shell;

import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;

public final class ListenerOptionsDialog extends AbstractOptionsDialog<ListenerPersistenceData> {
  public ListenerOptionsDialog(final Shell parent) {
    super(parent);
  }

  @Override
  String name() {
    return "listener";
  }

  @Override
  void load(final ListenerPersistenceData data) {
    this.setListenerRegistration(data);
  }

  public void setListenerRegistration(final ListenerPersistenceData data) {
    this.setRegistration(data);

    // TODO this.options = ;
  }

  public ListenerPersistenceData getListenerRegistration() {
    return this.getRegistration();
  }
}
