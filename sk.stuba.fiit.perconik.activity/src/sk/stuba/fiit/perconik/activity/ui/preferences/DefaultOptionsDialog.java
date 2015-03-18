package sk.stuba.fiit.perconik.activity.ui.preferences;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Ordering;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
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

import sk.stuba.fiit.perconik.activity.plugin.Activator;
import sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MapEntryDialog;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.ElementComparers;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.MapContentProvider;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.MapLabelProvider;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.RegularTableViewer;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.MapTableSorter;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.TableSorter;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;
import sk.stuba.fiit.perconik.ui.Buttons;
import sk.stuba.fiit.perconik.ui.Labels;
import sk.stuba.fiit.perconik.ui.TableColumns;
import sk.stuba.fiit.perconik.ui.Tables;
import sk.stuba.fiit.perconik.utilities.MoreMaps;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Maps.immutableEntry;
import static com.google.common.collect.Sets.newLinkedHashSet;

import static org.eclipse.jface.dialogs.IDialogConstants.CANCEL_LABEL;
import static org.eclipse.jface.dialogs.IDialogConstants.PROCEED_LABEL;
import static org.eclipse.jface.dialogs.MessageDialog.openError;

import static sk.stuba.fiit.perconik.core.plugin.Activator.loadedServices;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toStringComparator;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.compound;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.customRawOptions;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.emptyOptions;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.inheritedRawOptions;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.knownRawOptions;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.optionEquivalence;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.rawOptionType;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.toMap;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.unknownRawOptions;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
final class DefaultOptionsDialog extends StatusDialog {
  private ActivityPreferences preferences;

  Map<String, Object> map;

  CheckboxTableViewer tableViewer;

  MapEntryDialog<String, Object> entryDialog;

  Button addButton;

  Button updateButton;

  Button removeButton;

  Button restoreButton;

  DefaultOptionsDialog(final Shell parent) {
    super(parent);

    this.preferences = null;
    this.map = null;
  }

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

    TableColumn keyColumn = TableColumns.create(table, tableLayout, "Key", gc, 1);
    TableColumn valueColumn = TableColumns.create(table, tableLayout, "Value", gc, 1);

    gc.dispose();

    LocalMapTableSorter keySorter = new LocalMapTableSorter(table, Ordering.from(toStringComparator()).onResultOf(MoreMaps.<Entry<String, Object>, String>toKeyFunction()));
    LocalMapTableSorter valueSorter = new LocalMapTableSorter(table, Ordering.from(toStringComparator()).onResultOf(MoreMaps.<Entry<String, Object>, Object>toValueFunction()).compound(keySorter.getComparator()));

    keySorter.attach(keyColumn);
    valueSorter.attach(valueColumn);

    this.tableViewer = new RegularTableViewer(table);

    this.tableViewer.setComparer(ElementComparers.fromEquivalence(rawOptionType(), optionEquivalence()));
    this.tableViewer.setContentProvider(new MapContentProvider());
    this.tableViewer.setLabelProvider(new MapLabelProvider());

    this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      public void selectionChanged(final SelectionChangedEvent e) {
        updateButtons();
      }
    });

    this.tableViewer.addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(final DoubleClickEvent event) {
        performUpdate();
      }
    });

    Composite buttons = new Composite(innerParent, SWT.NONE);

    buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
    parentLayout = new GridLayout();
    parentLayout.marginHeight = 0;
    parentLayout.marginWidth = 0;
    buttons.setLayout(parentLayout);

    this.addButton = Buttons.createCentering(buttons, "Add", new WidgetListener() {
      public void handleEvent(final Event e) {
        performAdd();
      }
    });

    this.updateButton = Buttons.createCentering(buttons, "Update", new WidgetListener() {
      public void handleEvent(final Event e) {
        performUpdate();
      }
    });

    this.removeButton = Buttons.createCentering(buttons, "Remove", new WidgetListener() {
      public void handleEvent(final Event e) {
        performRemove();
      }
    });

    Labels.createButtonSeparator(buttons);

    this.restoreButton = Buttons.createCentering(buttons, "Restore", new WidgetListener() {
      public void handleEvent(final Event e) {
        performRestore();
      }
    });

    this.entryDialog = new CustomMapEntryDialog<>(this.getShell());

    this.load(this.preferences);

    Dialog.applyDialogFont(composite);

    innerParent.layout();

    return composite;
  }

  Map<String, Object> knownDefaultOptions() {
    return knownRawOptions(this.map, toMap(defaultPreferences().getListenerDefaultOptions()).keySet());
  }

  Map<String, Object> unknownDefaultOptions() {
    return unknownRawOptions(this.map, toMap(defaultPreferences().getListenerDefaultOptions()).keySet());
  }

  Map<String, Object> inheritedDefaultOptions() {
    return inheritedRawOptions(this.map, toMap(defaultPreferences().getListenerDefaultOptions()));
  }

  Map<String, Object> customDefaultOptions() {
    return customRawOptions(this.map, toMap(defaultPreferences().getListenerDefaultOptions()));
  }

  void updateButtons() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    int selectionCount = selection.size();
    int itemCount = this.tableViewer.getTable().getItemCount();

    this.updateButton.setEnabled(selectionCount == 1);
    this.removeButton.setEnabled(selectionCount > 0 && selectionCount <= itemCount);
  }

  void updateTable() {
    assert this.tableViewer != null;

    this.tableViewer.setInput(this.map);
    this.tableViewer.refresh();

    if (this.map != null) {
      this.tableViewer.setAllGrayed(false);
      this.tableViewer.setGrayedElements(this.inheritedDefaultOptions().entrySet().toArray());
    }
  }

  void sortTable() {
    assert this.tableViewer != null;

    Table table = this.tableViewer.getTable();

    TableSorter.enable(table, this.map != null);
    TableSorter.automaticSort(table);
  }

  final class LocalMapTableSorter extends MapTableSorter<String, Object> {
    LocalMapTableSorter(final Table table, @Nullable final Comparator<Entry<String, Object>> comparator) {
      super(table, comparator);
    }

    @Override
    protected Map<String, Object> loadMap() {
      return DefaultOptionsDialog.this.map;
    }

    @Override
    protected void updateMap(final Map<String, Object> map) {
      DefaultOptionsDialog.this.map = map;

      updateTable();
    }
  }

  static ActivityPreferences defaultPreferences() {
    return ActivityPreferences.getDefault();
  }

  private void openOptionDialog(final Entry<String, Object> entry) {
    MapEntryDialog<String, Object> dialog = this.entryDialog;

    dialog.setEntry(entry);
    dialog.setTitle("Option dialog");

    dialog.open();

    if (dialog.getReturnCode() == Window.OK) {
      Entry<String, Object> result = this.entryDialog.getEntry();

      if (result != null) {
        this.map.put(result.getKey(), result.getValue());

        this.updateTable();
        this.sortTable();
        this.updateButtons();
      }
    }
  }

  void performAdd() {
    this.openOptionDialog(immutableEntry("", null));
  }

  void performUpdate() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    @SuppressWarnings("unchecked")
    Entry<String, Object> entry = (Entry<String, Object>) selection.getFirstElement();

    this.openOptionDialog(entry);
  }

  void performRemove() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    Set<String> known = this.knownDefaultOptions().keySet();
    Set<String> locked = newLinkedHashSet();

    for (Object item: selection.toList()) {
      Object key = ((Entry<?, ?>) item).getKey();

      if (!known.contains(key)) {
        requireNonNull(this.map.remove(key));
      } else {
        locked.add(key.toString());
      }
    }

    this.updateTable();
    this.sortTable();
    this.updateButtons();

    if (!locked.isEmpty()) {
      String title = "Remove Listener Default Options";
      String message = format("Some default listener options could not be removed since inherited from activity defaults.");

      MessageDialog.openInformation(this.getShell(), title, message);
    }
  }

  void performRestore() {
    String title = "Restore Listener Default Options";
    String message = format("PerConIK Activity is about to restore defaults for selected options. Listeners may require to be reregistered for options to take effect.");
    String toggle = format("Restore all configured options");

    MessageDialogWithToggle dialog = new MessageDialogWithToggle(this.getShell(), title, null, message, MessageDialog.WARNING, new String[] { PROCEED_LABEL, CANCEL_LABEL }, 1, toggle, false);

    if (dialog.open() == 1) {
      return;
    }

    Map<String, Object> defaults = defaultPreferences().getListenerDefaultOptions().toMap();

    if (!dialog.getToggleState()) {
      IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

      if (!selection.isEmpty()) {
        for (Object item: selection.toList()) {
          String key = ((Entry<?, ?>) item).getKey().toString();

          this.map.put(key, defaults.get(key));
        }
      } else {
        message = "No options selected and restore all unchecked.";

        MessageDialog.openError(this.getShell(), title, message);
      }
    } else {
      this.map = defaults;
    }

    this.updateTable();
    this.sortTable();
    this.updateButtons();
  }

  void configure() {
    this.apply();
    this.save();
  }

  private void apply() {
    try {
      ActivityPreferences preferences = this.getActivityPreferences();

      preferences.setListenerDefaultOptions(MapOptions.from(this.customDefaultOptions()));
    } catch (RuntimeException failure) {
      String title = "Default Listener Options";
      String message = "Failed to apply default listener options.";
      String reason = failure.getLocalizedMessage();

      message += !isNullOrEmpty(reason) ? ("\n\n" + reason + "\n\n") : " ";

      openError(this.getShell(), title, message + "See error log for more details.");

      Activator.defaultInstance().getConsole().error(failure, message);
    }
  }

  private void load(final ActivityPreferences preferences) {
    this.setActivityPreferences(preferences);

    this.updateTable();
    this.sortTable();
    this.updateButtons();
  }

  private void save() {
    if (!loadedServices()) {
      return;
    }

    try {
      this.getActivityPreferences().flush();
    } catch (RuntimeException failure) {
      String title = "Preferences";
      String message = "Failed to save preferences.";

      openError(this.getShell(), title, message + " See error log for more details.");

      Activator.defaultInstance().getConsole().error(failure, message);
    }
  }

  public void setActivityPreferences(final ActivityPreferences preferences) {
    this.preferences = requireNonNull(preferences);

    Options defaults = defaultPreferences().getListenerDefaultOptions();
    Options custom = firstNonNull(preferences.getListenerDefaultOptions(), emptyOptions());

    this.map = compound(defaults, custom).toMap();
  }

  public ActivityPreferences getActivityPreferences() {
    return this.preferences;
  }

  @Override
  protected IDialogSettings getDialogBoundsSettings() {
    return DialogSettings.getOrCreateSection(Activator.defaultInstance().getDialogSettings(), DefaultOptionsDialog.class.getName());
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
