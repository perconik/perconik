package sk.stuba.fiit.perconik.core.ui.preferences;

import java.text.Collator;
import java.util.Map;
import java.util.Map.Entry;

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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import sk.stuba.fiit.perconik.core.persistence.Registration;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;
import sk.stuba.fiit.perconik.ui.utilities.Buttons;
import sk.stuba.fiit.perconik.ui.utilities.Tables;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;

import static java.lang.String.valueOf;
import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;

import static org.eclipse.jface.dialogs.MessageDialog.openInformation;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.toUpperCaseFirst;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractOptionsDialog<R extends Registration> extends StatusDialog {
  private R registration;

  Map<String, Object> options;

  CheckboxTableViewer tableViewer;

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

    Table table = Tables.create(tableComposite, SWT.CHECK | SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

    GC gc = new GC(this.getShell());
    gc.setFont(JFaceResources.getDialogFont());

    Tables.createColumn(table, tableLayout, "Key", gc, 1);
    Tables.createColumn(table, tableLayout, "Value", gc, 1);

    gc.dispose();

    this.tableViewer = new CheckboxTableViewer(table);

    this.tableViewer.setContentProvider(new MapContentProvider());
    this.tableViewer.setLabelProvider(new MapLabelProvider());
    this.tableViewer.setComparator(new MapViewerComparator());

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

    Dialog.applyDialogFont(composite);

    innerParent.layout();

    return composite;
  }

  final void updateTable() {
    this.tableViewer.setInput(this.options);
    this.tableViewer.refresh();
  }

  final void updateButtons() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    int selectionCount = selection.size();
    int itemCount = this.tableViewer.getTable().getItemCount();

    this.updateButton.setEnabled(selectionCount == 1);
    this.removeButton.setEnabled(selectionCount > 0 && selectionCount <= itemCount);
  }

  private static final class MapContentProvider implements IStructuredContentProvider {
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

  private static final class MapLabelProvider  extends LabelProvider implements ITableLabelProvider {
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

  private static final class MapViewerComparator extends ViewerComparator {
    MapViewerComparator() {}

    @Override
    public int compare(final Viewer viewer, final Object a, final Object b) {
      if ((a instanceof Entry) && (b instanceof Entry)) {
        return Collator.getInstance().compare(valueOf(((Entry<?, ?>) a).getKey()), valueOf(((Entry<?, ?>) b).getKey()));
      }

      return super.compare(viewer, a, b);
    }
  }

  void performAdd() {
    // TODO
  }

  void performUpdate() {
    // TODO
  }

  void performRemove() {
    // TODO
  }

  void performRestore() {
    openInformation(this.getShell(), "Restore " + toUpperCaseFirst(this.name()) + " Options", "Operation not yet supported.");
  }

  abstract void load(R registration);

  // TODO
  private void loadInternal(final R registration) {
    this.load(registration);

    this.updateTable();
    this.updateButtons();
  }

  final void setRegistration(final R registration) {
    this.registration = requireNonNull(registration);
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
