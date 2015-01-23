package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Ordering;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import sk.stuba.fiit.perconik.core.Registrable;
import sk.stuba.fiit.perconik.core.persistence.Registration;
import sk.stuba.fiit.perconik.core.ui.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.core.runtime.StatusSeverity;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;
import sk.stuba.fiit.perconik.ui.utilities.Buttons;
import sk.stuba.fiit.perconik.ui.utilities.Tables;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;
import sk.stuba.fiit.perconik.utilities.MoreMaps;
import sk.stuba.fiit.perconik.utilities.configuration.Configurable;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Maps.immutableEntry;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newLinkedHashMap;

import static org.eclipse.jface.dialogs.MessageDialog.openError;
import static org.eclipse.jface.dialogs.MessageDialog.openInformation;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.toStringLocalizedComparator;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toUpperCaseFirst;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractOptionsDialog<P, R extends Registration> extends StatusDialog {
  private P preferences;

  private R registration;

  Map<String, Object> map;

  CheckboxTableViewer tableViewer;

  MapEntryDialog<String, Object> entryDialog;

  Button addButton;

  Button updateButton;

  Button removeButton;

  Button restoreButton;

  AbstractOptionsDialog(final Shell parent) {
    super(parent);
  }

  abstract String name();

  @Override
  protected Control createDialogArea(final Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);

    GridLayout parentLayout = new GridLayout();
    parentLayout.numColumns = 2;
    parentLayout.marginHeight = 5;
    parentLayout.marginWidth = 5;
    composite.setLayout(parentLayout);
    composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    Composite innerParent = new Composite(composite, SWT.NONE);

    GridLayout innerLayout = new GridLayout();
    innerLayout.numColumns = 2;
    innerLayout.marginHeight = 0;
    innerLayout.marginWidth = 0;
    innerParent.setLayout(innerLayout);

    GridData innerGrid = new GridData(GridData.FILL_BOTH);
    innerGrid.horizontalSpan = 2;
    innerParent.setLayoutData(innerGrid);

    Composite tableComposite = new Composite(innerParent, SWT.NONE);
    TableColumnLayout tableLayout = new TableColumnLayout();

    GridData tableGrid = new GridData(GridData.FILL_BOTH);
    tableGrid.widthHint = 360;
    tableGrid.heightHint = this.convertHeightInCharsToPixels(10);
    tableComposite.setLayout(tableLayout);
    tableComposite.setLayoutData(tableGrid);

    Table table = Tables.create(tableComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

    GC gc = new GC(this.getShell());
    gc.setFont(JFaceResources.getDialogFont());

    TableColumn keyColumn = Tables.createColumn(table, tableLayout, "Key", gc, 1);
    TableColumn valueColumn = Tables.createColumn(table, tableLayout, "Value", gc, 1);

    gc.dispose();

    LocalMapTableSorter keySorter = new LocalMapTableSorter(table, Ordering.from(toStringLocalizedComparator()).onResultOf(MoreMaps.<Entry<String, Object>, String>toKeyFunction()));
    LocalMapTableSorter valueSorter = new LocalMapTableSorter(table, Ordering.from(toStringLocalizedComparator()).onResultOf(MoreMaps.<Entry<String, Object>, Object>toValueFunction()).compound(keySorter.getComparator()));

    keySorter.attach(keyColumn);
    valueSorter.attach(valueColumn);

    this.tableViewer = new CheckboxTableViewer(table);

    this.tableViewer.setContentProvider(new MapContentProvider());
    this.tableViewer.setLabelProvider(new MapLabelProvider());

    this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      public void selectionChanged(final SelectionChangedEvent e) {
        updateButtons();
      }
    });

    this.tableViewer.addCheckStateListener(new ICheckStateListener() {
      public void checkStateChanged(final CheckStateChangedEvent e) {
        updateButtons();
      }
    });

    Composite buttons = new Composite(innerParent, SWT.NONE);

    buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
    parentLayout = new GridLayout();
    parentLayout.marginHeight = 0;
    parentLayout.marginWidth = 0;
    buttons.setLayout(parentLayout);

    this.addButton = Buttons.create(buttons, "Add", new WidgetListener() {
      public void handleEvent(final Event e) {
        performAdd();
      }
    });

    this.updateButton = Buttons.create(buttons, "Update", new WidgetListener() {
      public void handleEvent(final Event e) {
        performUpdate();
      }
    });

    this.removeButton = Buttons.create(buttons, "Remove", new WidgetListener() {
      public void handleEvent(final Event e) {
        performRemove();
      }
    });

    Widgets.createButtonSeparator(buttons);

    this.restoreButton = Buttons.create(buttons, "Restore", new WidgetListener() {
      public void handleEvent(final Event e) {
        performRestore();
      }
    });

    this.entryDialog = MapEntryDialog.forObjectEntry(this.getShell());

    this.loadInternal(this.preferences, this.registration);

    Dialog.applyDialogFont(composite);

    innerParent.layout();

    return composite;
  }

  final void updateButtons() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    int selectionCount = selection.size();
    int itemCount = this.tableViewer.getTable().getItemCount();

    this.updateButton.setEnabled(selectionCount == 1);
    this.removeButton.setEnabled(selectionCount > 0 && selectionCount <= itemCount);
  }

  final void updateTable() {
    this.tableViewer.setInput(this.map);
    this.tableViewer.refresh();
  }

  final void sortTable() {
    TableSorter.automaticSort(this.tableViewer.getTable());
  }

  class LocalMapTableSorter extends MapTableSorter<String, Object> {
    LocalMapTableSorter(final Table table, @Nullable final Comparator<Entry<String, Object>> comparator) {
      super(table, comparator);
    }

    @Override
    final Map<String, Object> loadMap() {
      return AbstractOptionsDialog.this.map;
    }

    @Override
    final void updateMap(final Map<String, Object> map) {
      AbstractOptionsDialog.this.map = map;

      updateTable();
    }
  }

  abstract Options options(P preferences, R registration);

  private void openOptionDialog(final Entry<String, Object> entry) {
    MapEntryDialog<String, Object> dialog = this.entryDialog;

    dialog.setEntry(entry);

    dialog.open();

    if (dialog.getReturnCode() == Window.OK) {
      Entry<String, Object> result = this.entryDialog.getEntry();

      if (entry != null) {
        this.map.put(result.getKey(), result.getValue());

        this.updateButtons();
        this.updateTable();
        this.sortTable();
      }
    }
  }

  void performAdd() {
    this.openOptionDialog(immutableEntry("", (Object) ""));
  }

  void performUpdate() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    @SuppressWarnings("unchecked")
    Entry<String, Object> entry = (Entry<String, Object>) selection.getFirstElement();

    this.openOptionDialog(entry);
  }

  void performRemove() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    for (Object item: selection.toList()) {
      @SuppressWarnings("unchecked")
      Entry<String, Object> entry = (Entry<String, Object>) item;

      this.map.remove(entry.getKey());
    }

    this.updateButtons();
    this.updateTable();
    this.sortTable();
  }

  void performRestore() {
    openInformation(this.getShell(), "Restore " + toUpperCaseFirst(this.name()) + " Options", "Operation not yet supported.");
  }

  final void configure() {
    this.applyInternal();
  }

  abstract void apply();

  abstract void load(P preferences, R registration);

  private void applyInternal() {
    try {
      this.apply();
    } catch (RuntimeException failure) {
      String title = "Options";
      String message = "Failed to apply options.";

      openError(this.getShell(), title, message + " See error log for more details.");

      Activator.defaultInstance().getConsole().error(failure, message);
    }
  }

  private void loadInternal(final P preferences, final R registration) {
    this.load(preferences, registration);

    this.updateButtons();
    this.updateTable();
    this.sortTable();
  }

  final void updateStatusBy(final Registrable registrable) {
    StatusSeverity severity;
    String message;

    if (registrable instanceof Configurable) {
      severity = StatusSeverity.INFO;
      message = toUpperCaseFirst(this.name()) + " is configurable by default, but may require to be reregistered to apply specified options";
    } else {
      severity = StatusSeverity.WARNING;
      message = toUpperCaseFirst(this.name()) + " is not configurable by default, it may completely ignore specified options";
    }

    this.updateStatus(new Status(severity.getValue(), Activator.PLUGIN_ID, IStatus.OK, message, null));

  }

  static final <K> Map<K, Options> updateData(final Map<K, Options> data, final K key, final Options options) {
    Map<K, Options> update = newHashMap(data);

    update.put(key, options);

    return update;
  }

  static final Map<String, Object> readFromOptions(@Nullable final Options options) {
    return options != null ? newLinkedHashMap(options.toMap()) : new LinkedHashMap<String, Object>();
  }

  static final Options writeToOptions(@Nullable final Options options, final Map<String, Object> map) {
    requireNonNull(map);

    if (options != null) {
      options.fromMap(map);

      return options;
    }

    return MapOptions.from(map);
  }

  final void setPreferences(final P preferences) {
    this.preferences = requireNonNull(preferences);
  }

  final void setRegistration(final R registration) {
    this.registration = requireNonNull(registration);
  }

  final P getPreferences() {
    return this.preferences;
  }

  final R getRegistration() {
    return this.registration;
  }

  @Override
  protected IDialogSettings getDialogBoundsSettings() {
    return DialogSettings.getOrCreateSection(Activator.defaultInstance().getDialogSettings(), AbstractOptionsDialog.class.getName());
  }

  @Override
  public boolean isHelpAvailable() {
    return false;
  }

  @Override
  protected boolean isResizable() {
    return true;
  }
}
