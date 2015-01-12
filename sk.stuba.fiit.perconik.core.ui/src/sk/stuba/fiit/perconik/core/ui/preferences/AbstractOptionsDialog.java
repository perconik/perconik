package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Ordering;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import sk.stuba.fiit.perconik.core.persistence.Registration;
import sk.stuba.fiit.perconik.core.ui.plugin.Activator;
import sk.stuba.fiit.perconik.core.ui.preferences.AbstractRegistrationPreferencePage.AbstractTableSorter;
import sk.stuba.fiit.perconik.eclipse.swt.SortDirection;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;
import sk.stuba.fiit.perconik.ui.utilities.Buttons;
import sk.stuba.fiit.perconik.ui.utilities.Tables;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;
import sk.stuba.fiit.perconik.utilities.MoreMaps;
import sk.stuba.fiit.perconik.utilities.configuration.MapOptions;
import sk.stuba.fiit.perconik.utilities.configuration.Options;

import static java.lang.String.valueOf;
import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Maps.immutableEntry;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newLinkedHashMap;

import static org.eclipse.jface.dialogs.MessageDialog.openError;
import static org.eclipse.jface.dialogs.MessageDialog.openInformation;

import static sk.stuba.fiit.perconik.utilities.MoreMaps.putAll;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.requireNonNullOrEmpty;
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

    abstract class LocalMapEntrySorter extends MapEntrySorter<String, Object> {
      LocalMapEntrySorter(final Table table) {
        super(table);
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

    LocalMapEntrySorter keySorter = new LocalMapEntrySorter(table) {
      @Override
      Comparator<Entry<String, Object>> comparator() {
        return Ordering.from(toStringLocalizedComparator()).onResultOf(MoreMaps.<Entry<String, Object>, String>toKeyFunction());
      }
    };

    LocalMapEntrySorter valueSorter = new LocalMapEntrySorter(table) {
      @Override
      Comparator<Entry<String, Object>> comparator() {
        return Ordering.from(toStringLocalizedComparator()).onResultOf(MoreMaps.<Entry<String, Object>, Object>toValueFunction());
      }
    };

    keySorter.attach(keyColumn, SortDirection.DOWN);
    valueSorter.attach(valueColumn, SortDirection.UP);

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

    keySorter.sort(keyColumn, SortDirection.UP);
    table.setSortColumn(null);

    return composite;
  }

  final void updateTable() {
    this.tableViewer.setInput(this.map);
    this.tableViewer.refresh();
  }

  final void updateButtons() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    int selectionCount = selection.size();
    int itemCount = this.tableViewer.getTable().getItemCount();

    this.updateButton.setEnabled(selectionCount == 1);
    this.removeButton.setEnabled(selectionCount > 0 && selectionCount <= itemCount);
  }

  static final class MapContentProvider implements IStructuredContentProvider {
    private Map<?, ?> data;

    MapContentProvider() {
      this.data = emptyMap();
    }

    public Object[] getElements(final Object input) {
      return this.data.entrySet().toArray();
    }

    public void inputChanged(final Viewer viewer, final Object from, final Object to) {
      this.data = (Map<?, ?>) to;
    }

    public void dispose() {
      this.data = null;
    }
  }

  static final class MapLabelProvider  extends LabelProvider implements ITableLabelProvider {
    MapLabelProvider() {}

    public String getColumnText(final Object element, final int column) {
      Entry<?, ?> entry = (Entry<?, ?>) element;

      switch (column) {
        case 0:
          return valueOf(entry.getKey());

        case 1:
          return valueOf(entry.getValue());

        default:
          throw new IllegalStateException();
      }
    }

    public Image getColumnImage(final Object element, final int columnIndex) {
      return null;
    }
  }

  static final class MapViewerComparator extends ViewerComparator {
    MapViewerComparator() {}

    @Override
    public int compare(final Viewer viewer, final Object a, final Object b) {
      if ((a instanceof Entry) && (b instanceof Entry)) {
        return toStringLocalizedComparator().compare(((Entry<?, ?>) a).getKey(), ((Entry<?, ?>) b).getKey());
      }

      return super.compare(viewer, a, b);
    }
  }

  static abstract class MapEntrySorter<K, V> extends AbstractTableSorter<Entry<K, V>> {
    MapEntrySorter(final Table table) {
      super(table);
    }

    @Override
    final Set<Entry<K, V>> loadData() {
      return this.loadMap().entrySet();
    }

    @Override
    final void updateData(final List<Entry<K, V>> data) {
      Map<K, V> update = newLinkedHashMap();

      putAll(update, data);

      this.updateMap(update);
    }

    abstract Map<K, V> loadMap();

    abstract void updateMap(Map<K, V> map);
  }

  static final class MapEntryDialog<K, V> extends StatusDialog {
    private final EntryConverter<K, V> converter;

    private Entry<K, V> entry;

    private Label keyLabel;

    private Text keyText;

    private Label valueLabel;

    private Text valueText;

    MapEntryDialog(final Shell parent, final EntryConverter<K, V> converter) {
      super(parent);

      this.setTitle("Option Dialog");

      this.converter = requireNonNull(converter);
    }

    static <K, V> MapEntryDialog<K, V> forObjectEntry(final Shell parent) {
      return new MapEntryDialog<>(parent, StringEntryConverter.<K, V>create());
    }

    static MapEntryDialog<String, String> forStringEntry(final Shell parent) {
      return forObjectEntry(parent);
    }

    interface EntryConverter<K, V> {
      public Entry<K, V> convert(Entry<K, V> original, String key, String value);
    }

    static abstract class AbstractEntryConverter<K, V> implements EntryConverter<K, V> {
      AbstractEntryConverter() {}

      public Entry<K, V> convert(final Entry<K, V> original, final String key, final String value) {
        return immutableEntry(this.key(original.getKey(), key), this.value(original.getValue(), value));
      }

      abstract K key(K original, String text);

      abstract V value(V original, String text);
    }

    static final class StringEntryConverter extends AbstractEntryConverter<Object, Object> {
      private static final StringEntryConverter INSTANCE = new StringEntryConverter();

      private StringEntryConverter() {}

      static <K, V> EntryConverter<K, V> create() {
        @SuppressWarnings("unchecked")
        EntryConverter<K, V> converter = (EntryConverter<K, V>) StringEntryConverter.INSTANCE;

        return converter;
      }

      @Override
      String key(final Object original, final String text) {
        return requireNonNullOrEmpty(text);
      }

      @Override
      String value(final Object original, final String text) {
        return requireNonNull(text);
      }
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
      Composite composite = new Composite(parent, SWT.NONE);

      composite.setLayout(new GridLayout(4, false));
      composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));

      this.keyLabel = new Label(composite, SWT.NONE);
      this.keyText = new Text(composite, SWT.BORDER);

      this.keyLabel.setText("Key");
      this.keyLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
      this.keyText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 3, 1));

      this.valueLabel = new Label(composite, SWT.NONE);
      this.valueText = new Text(composite, SWT.BORDER);

      this.valueLabel.setText("Value");
      this.valueLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true, 1, 1));
      this.valueText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 3, 1));

      ModifyListener validator = new ModifyListener() {
        public void modifyText(final ModifyEvent e) {
          update();
        }
      };

      this.keyText.addModifyListener(validator);
      this.valueText.addModifyListener(validator);

      Dialog.applyDialogFont(composite);

      this.load();

      this.keyText.setFocus();

      return composite;
    }

    @Override
    protected void okPressed() {
      this.entry = this.read();

      super.okPressed();
    }

    Entry<K, V> read() {
      return this.converter.convert(this.entry, this.keyText.getText(), this.valueText.getText());
    }

    void load() {
      this.keyText.setText(toText(this.entry.getKey()));
      this.valueText.setText(toText(this.entry.getValue()));
    }

    void update() {
      try {
        this.read();
        this.updateStatus(Status.OK_STATUS);
      } catch (RuntimeException failure) {
        this.updateStatus(new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Invalid option", failure));
      }
    }

    private static String toText(final Object value) {
      return value != null ? value.toString() : "";
    }

    public void setEntry(final Entry<K, V> entry) {
      this.entry = immutableEntry(entry.getKey(), entry.getValue());
    }

    public Entry<K, V> getEntry() {
      return this.entry;
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

  private void openOptionDialog(final Entry<String, Object> entry) {
    MapEntryDialog<String, Object> dialog = this.entryDialog;

    dialog.setEntry(entry);

    dialog.open();

    if (dialog.getReturnCode() == Window.OK) {
      Entry<String, Object> result = this.entryDialog.getEntry();

      if (entry != null) {
        this.map.put(result.getKey(), result.getValue());

        this.updateTable();
        this.updateButtons();
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

    this.updateTable();
    this.updateButtons();
  }

  void performRestore() {
    // TODO

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

    this.updateTable();
    this.updateButtons();
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
  public boolean isHelpAvailable() {
    return false;
  }

  @Override
  protected boolean isResizable() {
    return true;
  }
}
