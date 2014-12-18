package sk.stuba.fiit.perconik.core.ui.preferences;

import java.text.Collator;
import java.util.Set;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Table;

import org.osgi.service.prefs.BackingStoreException;

import sk.stuba.fiit.perconik.core.ResourceNotRegistredException;
import sk.stuba.fiit.perconik.core.persistence.Registrations;
import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;
import sk.stuba.fiit.perconik.core.preferences.ListenerPreferences;
import sk.stuba.fiit.perconik.ui.utilities.Tables;

import static org.eclipse.jface.dialogs.MessageDialog.openError;

/**
 * Listeners preference page.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenersPreferencePage extends AbstractRegistrationPreferencePage<ListenerPreferences, ListenerPersistenceData> {
  public ListenersPreferencePage() {}

  @Override
  String name() {
    return "listener";
  }

  @Override
  Class<ListenerPersistenceData> type() {
    return ListenerPersistenceData.class;
  }

  @Override
  protected ListenerLabelProvider createContentProvider() {
    return new ListenerLabelProvider();
  }

  @Override
  protected ListenerOptionsDialog createOptionsDialog() {
    return new ListenerOptionsDialog(this.getShell());
  }

  @Override
  protected ListenerViewerComparator createViewerComparator() {
    return new ListenerViewerComparator();
  }

  @Override
  protected void createTableColumns(final Table table, final TableColumnLayout layout, final GC gc) {
    Tables.createColumn(table, layout, "Listener implementation", gc, 4);
    Tables.createColumn(table, layout, "Version", gc, 1);
    Tables.createColumn(table, layout, "Notes", gc, 1);
  }

  private static final class ListenerLabelProvider extends AbstractLabelProvider<ListenerPersistenceData> {
    ListenerLabelProvider() {}

    public String getColumnText(final Object element, final int column) {
      ListenerPersistenceData data = (ListenerPersistenceData) element;

      switch (column) {
        case 0:
          return data.getListenerClass().getName() + (data.isProvided() ? "" : " (unknown)");

        case 1:
          return this.getVersion(data);

        case 2:
          return this.getAnnotations(data);

        default:
          throw new IllegalStateException();
      }
    }
  }

  private static class ListenerViewerComparator extends AbstractViewerComparator {
    ListenerViewerComparator() {}

    @Override
    public int compare(final Viewer viewer, final Object a, final Object b) {
      if ((a instanceof ListenerPersistenceData) && (b instanceof ListenerPersistenceData)) {
        ListenerPersistenceData data = (ListenerPersistenceData) a;
        ListenerPersistenceData other = (ListenerPersistenceData) b;

        return Collator.getInstance().compare(data.getListenerClass().getName(), other.getListenerClass().getName());
      }

      return super.compare(viewer, a, b);
    }
  }

  @Override
  Set<ListenerPersistenceData> defaults() {
    return ListenerPersistenceData.defaults();
  }

  @Override
  ListenerPreferences preferences() {
    return ListenerPreferences.getShared();
  }

  @Override
  void apply() {
    try {
      Set<ListenerPersistenceData> data = Registrations.applyRegisteredMark(this.registrations);

      this.getPreferences().setListenerPersistenceData(data);
    } catch (ResourceNotRegistredException e) {
      StringBuilder message = new StringBuilder();

      message.append("Listener registration failed due to one or more unregistered but required resources. ");
      message.append("Select only listeners with registered resources.\n\n");
      message.append(e.getMessage() + ".");

      openError(this.getShell(), "Listener registration", message.toString());

      this.performRefresh();
    }
  }

  @Override
  void load(final ListenerPreferences preferences) {
    this.setListenerPreferences(preferences);
  }

  @Override
  void save() throws BackingStoreException {
    this.getPreferences().flush();
  }

  public void setListenerPreferences(final ListenerPreferences preferences) {
    this.setPreferences(preferences);

    this.registrations = preferences.getListenerPersistenceData();
  }

  public ListenerPreferences getListenerPreferences() {
    return this.getPreferences();
  }
}
