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

import jersey.repackaged.com.google.common.collect.Maps;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.persistence.Registrations;
import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;
import sk.stuba.fiit.perconik.core.preferences.ResourcePreferences;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.SortingViewerComparator;
import sk.stuba.fiit.perconik.ui.TableColumns;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static org.eclipse.jface.dialogs.MessageDialog.openError;

import static sk.stuba.fiit.perconik.osgi.framework.Versions.toVersion;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toStringComparator;

/**
 * Resources preference page.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourcesPreferencePage extends AbstractPreferencePage<ResourcePreferences, ResourcePersistenceData> {
  public ResourcesPreferencePage() {}

  @Override
  String name() {
    return "resource";
  }

  @Override
  Class<ResourcePersistenceData> type() {
    return ResourcePersistenceData.class;
  }

  @Override
  protected ResourceLabelProvider createContentProvider() {
    return new ResourceLabelProvider();
  }

  @Override
  protected ResourceOptionsDialog createOptionsDialog() {
    return new ResourceOptionsDialog(this.getShell());
  }

  @Override
  protected ResourceViewerComparator createViewerComparator() {
    return new ResourceViewerComparator();
  }

  @Override
  protected void createTableColumns(final Table table, final TableColumnLayout layout, final GC gc) {
    TableColumn resourceColumn = TableColumns.create(table, layout, "Resource name", gc, 4);
    TableColumn listenerColumn = TableColumns.create(table, layout, "Listener type", gc, 4);
    TableColumn versionColumn = TableColumns.create(table, layout, "Version", gc, 1);
    TableColumn notesColumn = TableColumns.create(table, layout, "Notes", gc, 1);

    LocalSetTableSorter resourceSorter = new LocalSetTableSorter(table, Ordering.from(toStringComparator()).onResultOf(new Function<ResourcePersistenceData, String>() {
      public String apply(final ResourcePersistenceData data) {
        return data.getResourceName();
      }
    }));

    LocalSetTableSorter listenerSorter = new LocalSetTableSorter(table, Ordering.from(toStringComparator()).onResultOf(new Function<ResourcePersistenceData, String>() {
      public String apply(final ResourcePersistenceData data) {
        return data.getListenerType().getName();
      }
    }).compound(resourceSorter.getComparator()));

    LocalSetTableSorter versionSorter = new LocalSetTableSorter(table, Ordering.natural().onResultOf(new Function<ResourcePersistenceData, Version>() {
      public Version apply(final ResourcePersistenceData data) {
        return toVersion(((ResourceLabelProvider) ResourcesPreferencePage.this.tableViewer.getLabelProvider()).getVersion(data));
      }
    }).compound(resourceSorter.getComparator()));

    LocalSetTableSorter notesSorter = new LocalSetTableSorter(table, Ordering.from(toStringComparator()).onResultOf(new Function<ResourcePersistenceData, String>() {
      public String apply(final ResourcePersistenceData data) {
        return ((ResourceLabelProvider) ResourcesPreferencePage.this.tableViewer.getLabelProvider()).getNotes(data);
      }
    }).compound(resourceSorter.getComparator()));

    resourceSorter.attach(resourceColumn);
    listenerSorter.attach(listenerColumn);
    versionSorter.attach(versionColumn);
    notesSorter.attach(notesColumn);
  }

  private static final class ResourceLabelProvider extends AbstractLabelProvider<ResourcePersistenceData> {
    ResourceLabelProvider() {}

    public String getColumnText(final Object element, final int column) {
      ResourcePersistenceData data = (ResourcePersistenceData) element;

      switch (column) {
        case 0:
          return data.getResourceName() + (data.isProvided() ? "" : " (unknown)");

        case 1:
          return data.getListenerType().getName();

        case 2:
          return this.getVersion(data);

        case 3:
          return this.getNotes(data);

        default:
          throw new IllegalStateException();
      }
    }
  }

  private static final class ResourceViewerComparator extends SortingViewerComparator {
    ResourceViewerComparator() {}

    @Override
    public int compare(final Viewer viewer, final Object a, final Object b) {
      if ((a instanceof ResourcePersistenceData) && (b instanceof ResourcePersistenceData)) {
        ResourcePersistenceData data = (ResourcePersistenceData) a;
        ResourcePersistenceData other = (ResourcePersistenceData) b;

        int result = toStringComparator().compare(data.getResourceName(), other.getResourceName());

        if (result != 0) {
          return result;
        }

        return toStringComparator().compare(data.getListenerType().getName(), other.getListenerType().getName());
      }

      return super.compare(viewer, a, b);
    }
  }

  @Override
  ResourcePreferences defaultPreferences() {
    return ResourcePreferences.getDefault();
  }

  @Override
  ResourcePreferences sharedPreferences() {
    return ResourcePreferences.getShared();
  }

  @Override
  Set<ResourcePersistenceData> registrations(final ResourcePreferences preferences) {
    return preferences.getResourcePersistenceData();
  }

  @Override
  void apply() {
    ResourcePreferences preferences = this.getPreferences();

    for (ResourcePersistenceData data: this.registrations) {
      if (data.isRegistered() && !data.hasRegistredMark() && !data.getResource().registered(Listener.class).isEmpty()) {
        StringBuilder message = new StringBuilder();

        message.append("Resource unregistration failed due to one or more listeners registered. ");
        message.append("Select only resources with currently no registered listeners or unregister all listeners from the resources to be unregistered first.\n\n");
        message.append(data.getResource());

        for (Listener listener: data.getResource().registered(Listener.class)) {
          message.append("\n  " + listener.getClass().getName());
        }

        openError(this.getShell(), "Resource unregistration", message.toString());

        this.performRefresh();

        return;
      }
    }

    Set<ResourcePersistenceData> data = Registrations.applyRegisteredMark(this.registrations);

    preferences.setResourcePersistenceData(data);

    if (this.restoreOptions.compareAndSet(true, false)) {
      preferences.setResourceConfigurationData(Maps.<String, Options>newHashMap());
    }
  }

  @Override
  void load(final ResourcePreferences preferences) {
    this.setResourcePreferences(preferences);
  }

  @Override
  void save() {
    this.getPreferences().flush();
  }

  public void setResourcePreferences(final ResourcePreferences preferences) {
    this.setPreferences(preferences);

    this.registrations = preferences.getResourcePersistenceData();
  }

  public ResourcePreferences getResourcePreferences() {
    return this.getPreferences();
  }
}
