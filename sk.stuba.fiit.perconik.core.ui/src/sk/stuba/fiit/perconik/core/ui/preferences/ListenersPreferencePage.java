package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import org.osgi.framework.Version;
import org.osgi.service.prefs.BackingStoreException;

import jersey.repackaged.com.google.common.collect.Maps;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.ResourceNotRegistredException;
import sk.stuba.fiit.perconik.core.persistence.Registrations;
import sk.stuba.fiit.perconik.core.persistence.data.ListenerPersistenceData;
import sk.stuba.fiit.perconik.core.preferences.ListenerPreferences;
import sk.stuba.fiit.perconik.ui.Tables;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static org.eclipse.jface.dialogs.MessageDialog.openError;

import static sk.stuba.fiit.perconik.osgi.framework.Versions.toVersion;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toStringComparator;

/**
 * Listeners preference page.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ListenersPreferencePage extends AbstractPreferencePage<ListenerPreferences, ListenerPersistenceData> {
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
    TableColumn listenerColumn = Tables.createColumn(table, layout, "Listener implementation", gc, 4);
    TableColumn versionColumn = Tables.createColumn(table, layout, "Version", gc, 1);
    TableColumn notesColumn = Tables.createColumn(table, layout, "Notes", gc, 1);

    LocalSetTableSorter listenerSorter = new LocalSetTableSorter(table, Ordering.from(toStringComparator()).onResultOf(new Function<ListenerPersistenceData, String>() {
      public String apply(final ListenerPersistenceData data) {
        return data.getListenerClass().getName();
      }
    }));

    LocalSetTableSorter versionSorter = new LocalSetTableSorter(table, Ordering.natural().onResultOf(new Function<ListenerPersistenceData, Version>() {
      public Version apply(final ListenerPersistenceData data) {
        return toVersion(((ListenerLabelProvider) ListenersPreferencePage.this.tableViewer.getLabelProvider()).getVersion(data));
      }
    }).compound(listenerSorter.getComparator()));

    LocalSetTableSorter notesSorter = new LocalSetTableSorter(table, Ordering.from(toStringComparator()).onResultOf(new Function<ListenerPersistenceData, String>() {
      public String apply(final ListenerPersistenceData data) {
        return ((ListenerLabelProvider) ListenersPreferencePage.this.tableViewer.getLabelProvider()).getNotes(data);
      }
    }).compound(listenerSorter.getComparator()));

    listenerSorter.attach(listenerColumn);
    versionSorter.attach(versionColumn);
    notesSorter.attach(notesColumn);
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
          return this.getNotes(data);

        default:
          throw new IllegalStateException();
      }
    }
  }

  private static class ListenerViewerComparator extends SortingViewerComparator {
    ListenerViewerComparator() {}

    @Override
    public int compare(final Viewer viewer, final Object a, final Object b) {
      if ((a instanceof ListenerPersistenceData) && (b instanceof ListenerPersistenceData)) {
        ListenerPersistenceData data = (ListenerPersistenceData) a;
        ListenerPersistenceData other = (ListenerPersistenceData) b;

        return toStringComparator().compare(data.getListenerClass().getName(), other.getListenerClass().getName());
      }

      return super.compare(viewer, a, b);
    }
  }

  @Override
  ListenerPreferences defaultPreferences() {
    return ListenerPreferences.getDefault();
  }

  @Override
  ListenerPreferences sharedPreferences() {
    return ListenerPreferences.getShared();
  }

  @Override
  Set<ListenerPersistenceData> registrations(final ListenerPreferences preferences) {
    return preferences.getListenerPersistenceData();
  }

  @Override
  void apply() {
    ListenerPreferences preferences = this.getPreferences();

    try {
      Set<ListenerPersistenceData> data = Registrations.applyRegisteredMark(this.registrations);

      preferences.setListenerPersistenceData(data);
    } catch (ResourceNotRegistredException failure) {
      StringBuilder message = new StringBuilder();

      message.append("Listener registration failed due to one or more unregistered but required resources. ");
      message.append("Select only listeners with registered resources.\n\n");
      message.append(failure.getLocalizedMessage() + ".");

      openError(this.getShell(), "Listener registration", message.toString());

      this.performRefresh();

      return;
    }

    if (this.restoreOptions.compareAndSet(true, false)) {
      preferences.setListenerConfigurationData(Maps.<Class<? extends Listener>, Options>newHashMap());
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
