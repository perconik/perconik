package sk.stuba.fiit.perconik.core.ui.preferences;

import java.text.Collator;
import java.util.Set;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Table;

import org.osgi.service.prefs.BackingStoreException;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.persistence.Registrations;
import sk.stuba.fiit.perconik.core.persistence.data.ResourcePersistenceData;
import sk.stuba.fiit.perconik.core.preferences.ResourcePreferences;
import sk.stuba.fiit.perconik.ui.utilities.Tables;

import static org.eclipse.jface.dialogs.MessageDialog.openError;

/**
 * Resources preference page.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class ResourcesPreferencePage extends AbstractRegistrationPreferencePage<ResourcePreferences, ResourcePersistenceData> {
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
    Tables.createColumn(table, layout, "Resource name", gc, 4);
    Tables.createColumn(table, layout, "Listener type", gc, 4);
    Tables.createColumn(table, layout, "Version", gc, 1);
    Tables.createColumn(table, layout, "Notes", gc, 1);
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
          return this.getAnnotations(data);

        default:
          throw new IllegalStateException();
      }
    }
  }

  private static final class ResourceViewerComparator extends AbstractViewerComparator {
    ResourceViewerComparator() {}

    @Override
    public int compare(final Viewer viewer, final Object a, final Object b) {
      if ((a instanceof ResourcePersistenceData) && (b instanceof ResourcePersistenceData)) {
        ResourcePersistenceData data = (ResourcePersistenceData) a;
        ResourcePersistenceData other = (ResourcePersistenceData) b;

        int result = Collator.getInstance().compare(data.getResourceName(), other.getResourceName());

        if (result != 0) {
          return result;
        }

        return Collator.getInstance().compare(data.getListenerType().getName(), other.getListenerType().getName());
      }

      return super.compare(viewer, a, b);
    }
  }

  @Override
  Set<ResourcePersistenceData> defaults() {
    return ResourcePersistenceData.defaults();
  }

  @Override
  ResourcePreferences preferences() {
    return ResourcePreferences.getShared();
  }

  @Override
  void apply() {
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

    this.getPreferences().setResourcePersistenceData(data);
  }

  @Override
  void load(final ResourcePreferences preferences) {
    this.setResourcePreferences(preferences);
  }

  @Override
  void save() throws BackingStoreException {
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
