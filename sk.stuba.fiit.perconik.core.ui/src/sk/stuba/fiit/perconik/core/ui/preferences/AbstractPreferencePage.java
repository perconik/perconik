package sk.stuba.fiit.perconik.core.ui.preferences;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;

import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.persistence.AnnotableRegistration;
import sk.stuba.fiit.perconik.core.persistence.MarkableRegistration;
import sk.stuba.fiit.perconik.core.persistence.RegistrationMarker;
import sk.stuba.fiit.perconik.core.ui.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.CollectionContentProvider;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.SortingViewerComparator;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.SetTableSorter;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.TableSorter;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;
import sk.stuba.fiit.perconik.ui.Buttons;
import sk.stuba.fiit.perconik.ui.Tables;
import sk.stuba.fiit.perconik.ui.Widgets;
import sk.stuba.fiit.perconik.ui.preferences.AbstractWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotations;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newLinkedHashSet;

import static org.eclipse.jface.dialogs.IDialogConstants.CANCEL_LABEL;
import static org.eclipse.jface.dialogs.IDialogConstants.PROCEED_LABEL;
import static org.eclipse.jface.dialogs.MessageDialog.openError;
import static org.eclipse.jface.dialogs.MessageDialog.openInformation;

import static sk.stuba.fiit.perconik.core.plugin.Activator.loadedServices;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toUpperCaseFirst;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
abstract class AbstractPreferencePage<P, R extends AnnotableRegistration & MarkableRegistration & RegistrationMarker<R>> extends AbstractWorkbenchPreferencePage {
  private P preferences;

  Set<R> registrations;

  AtomicBoolean restoreOptions;

  CheckboxTableViewer tableViewer;

  AbstractOptionsDialog<P, R> optionsDialog;

  Button addButton;

  Button removeButton;

  Button registerButton;

  Button unregisterButton;

  Button importButton;

  Button exportButton;

  Button refreshButton;

  Button optionsButton;

  Button notesButton;

  AbstractPreferencePage() {
    this.preferences = null;
    this.registrations = null;
    this.restoreOptions = new AtomicBoolean(false);
  }

  abstract String name();

  private static String pluralize(final String s) {
    return s + "s";
  }

  abstract Class<R> type();

  final R cast(final Object o) {
    return this.type().cast(o);
  }

  @Override
  public final void createControl(final Composite parent) {
    super.createControl(parent);

    this.performRefresh();
  }

  @Override
  protected final Control createContents(final Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);

    GridLayout parentLayout = new GridLayout();
    parentLayout.numColumns = 2;
    parentLayout.marginHeight = 0;
    parentLayout.marginWidth = 0;
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
    tableGrid.widthHint = 720;
    tableGrid.heightHint = this.convertHeightInCharsToPixels(10);
    tableComposite.setLayout(tableLayout);
    tableComposite.setLayoutData(tableGrid);

    Table table = Tables.create(tableComposite, SWT.CHECK | SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

    GC gc = new GC(this.getShell());
    gc.setFont(JFaceResources.getDialogFont());

    this.createTableColumns(table, tableLayout, gc);

    gc.dispose();

    this.tableViewer = new CustomTableViewer(table);

    this.tableViewer.setContentProvider(new CollectionContentProvider());
    this.tableViewer.setLabelProvider(this.createContentProvider());

    this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      public void selectionChanged(final SelectionChangedEvent e) {
        updateButtons();
      }
    });

    this.tableViewer.addCheckStateListener(new ICheckStateListener() {
      public void checkStateChanged(final CheckStateChangedEvent e) {
        @SuppressWarnings("unchecked")
        R data = (R) e.getElement();

        if (data.isProvided()) {
          updateData(data, e.getChecked());
          updateButtons();
        } else {
          e.getCheckable().setChecked(data, data.hasRegistredMark());
        }
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

    this.removeButton = Buttons.create(buttons, "Remove", new WidgetListener() {
      public void handleEvent(final Event e) {
        performRemove();
      }
    });

    Widgets.createButtonSeparator(buttons);

    this.registerButton = Buttons.create(buttons, "Register", new WidgetListener() {
      public void handleEvent(final Event e) {
        performRegister();
      }
    });

    this.unregisterButton = Buttons.create(buttons, "Unregister", new WidgetListener() {
      public void handleEvent(final Event e) {
        performUnregister();
      }
    });

    Widgets.createButtonSeparator(buttons);

    this.importButton = Buttons.create(buttons, "Import", new WidgetListener() {
      public void handleEvent(final Event e) {
        performImport();
      }
    });

    this.exportButton = Buttons.create(buttons, "Export", new WidgetListener() {
      public void handleEvent(final Event e) {
        performExport();
      }
    });

    Widgets.createButtonSeparator(buttons);

    this.refreshButton = Buttons.create(buttons, "Refresh", new WidgetListener() {
      public void handleEvent(final Event e) {
        performRefresh();
      }
    });

    this.optionsButton = Buttons.create(buttons, "Options", new WidgetListener() {
      public void handleEvent(final Event e) {
        performOptions();
      }
    });

    this.notesButton = Buttons.create(buttons, "Notes", new WidgetListener() {
      public void handleEvent(final Event e) {
        performNotes();
      }
    });

    this.optionsDialog = this.createOptionsDialog();

    Dialog.applyDialogFont(composite);

    innerParent.layout();

    return composite;
  }

  protected abstract AbstractLabelProvider<R> createContentProvider();

  protected abstract AbstractOptionsDialog<P, R> createOptionsDialog();

  protected abstract SortingViewerComparator createViewerComparator();

  protected abstract void createTableColumns(final Table table, final TableColumnLayout layout, final GC gc);

  final Set<R> checkedData() {
    return Sets.filter(this.registrations, new Predicate<R>() {
      public boolean apply(@Nonnull final R registration) {
        return registration.hasRegistredMark();
      }
    });
  }

  final Set<R> unknownData() {
    return Sets.filter(this.registrations, new Predicate<R>() {
      public boolean apply(@Nonnull final R registration) {
        return !registration.isProvided();
      }
    });
  }

  final void updateData(final R registration, final boolean status) {
    List<R> registrations = newArrayList(this.registrations);

    this.updateData(registrations, registration, status);

    this.registrations = newLinkedHashSet(registrations);

    this.tableViewer.refresh();
  }

  final void updateSelectedData(final boolean status) {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    List<R> registrations = newArrayList(this.registrations);

    for (Object item: selection.toList()) {
      R registration = this.cast(item);

      if (registration.isProvided()) {
        this.updateData(registrations, registration, status);
      }
    }

    this.registrations = newLinkedHashSet(registrations);

    this.tableViewer.refresh();
  }

  private void updateData(final List<R> registrations, final R registration, final boolean status) {
    registrations.set(registrations.indexOf(registration), registration.markRegistered(status));

    this.tableViewer.setChecked(registration, status);
  }

  final void updatePage() {
    this.updateMessage();
    this.updateTable();
    this.sortTable();
    this.updateButtons();
  }

  final void updateMessage() {
    if (loadedServices()) {
      this.setErrorMessage(null);
    } else {
      this.setErrorMessage("Core services not loaded");
    }
  }

  final void updateButtons() {
    if (!loadedServices()) {
      this.getApplyButton().setEnabled(false);
      this.getDefaultsButton().setEnabled(false);

      this.addButton.setEnabled(false);
      this.removeButton.setEnabled(false);

      this.registerButton.setEnabled(false);
      this.unregisterButton.setEnabled(false);

      this.importButton.setEnabled(false);
      this.exportButton.setEnabled(false);

      this.refreshButton.setEnabled(true);
      this.optionsButton.setEnabled(false);
      this.notesButton.setEnabled(false);

      return;
    }

    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    int selectionCount = selection.size();
    int itemCount = this.tableViewer.getTable().getItemCount();

    boolean registrable = false;
    boolean unregistrable = false;

    if (selectionCount > 0) {
      for (Object item: selection.toList()) {
        R registration = this.cast(item);

        if (registration.isProvided()) {
          boolean registred = registration.hasRegistredMark();

          registrable |= !registred;
          unregistrable |= registred;
        }
      }
    }

    this.getApplyButton().setEnabled(true);
    this.getDefaultsButton().setEnabled(true);

    this.addButton.setEnabled(true);
    this.removeButton.setEnabled(selectionCount > 0 && selectionCount <= itemCount);

    this.registerButton.setEnabled(registrable);
    this.unregisterButton.setEnabled(unregistrable);

    this.importButton.setEnabled(true);
    this.exportButton.setEnabled(selectionCount > 0);

    this.refreshButton.setEnabled(true);
    this.optionsButton.setEnabled(selectionCount == 1 && !this.restoreOptions.get());
    this.notesButton.setEnabled(selectionCount == 1);
  }

  final void updateTable() {
    assert this.tableViewer != null;

    this.tableViewer.setInput(this.registrations);
    this.tableViewer.refresh();

    if (this.registrations != null) {
      this.tableViewer.setAllChecked(false);
      this.tableViewer.setAllGrayed(false);
      this.tableViewer.setCheckedElements(this.checkedData().toArray());
      this.tableViewer.setGrayedElements(this.unknownData().toArray());
    }
  }

  final void sortTable() {
    assert this.tableViewer != null;

    Table table = this.tableViewer.getTable();

    TableSorter.enable(table, this.registrations != null);
    TableSorter.automaticSort(table);
  }

  class LocalSetTableSorter extends SetTableSorter<R> {
    LocalSetTableSorter(final Table table, @Nullable final Comparator<? super R> comparator) {
      super(table, comparator);
    }

    @Override
    protected final Set<R> loadSet() {
      return AbstractPreferencePage.this.registrations;
    }

    @Override
    protected final void updateSet(final Set<R> set) {
      AbstractPreferencePage.this.registrations = set;

      updateTable();
    }
  }

  static abstract class AbstractLabelProvider<R extends AnnotableRegistration & MarkableRegistration & RegistrationMarker<R>> extends LabelProvider implements ITableLabelProvider {
    AbstractLabelProvider() {}

    public final String getNotes(final R registration) {
      if (!registration.isProvided()) {
        return "?";
      }

      return Annotations.toString(NoteFilter.apply(registration.getAnnotations()));
    }

    public final String getVersion(final R registration) {
      Version version = registration.getAnnotation(Version.class);

      return version != null ? version.value() : "?";
    }

    public Image getColumnImage(final Object element, final int column) {
      return null;
    }
  }

  private enum NoteFilter implements Predicate<Annotation> {
    INSTANCE;

    public static Iterable<Annotation> apply(final Iterable<Annotation> annotations) {
      return Iterables.filter(annotations, INSTANCE);
    }

    public boolean apply(@Nonnull final Annotation annotation) {
      return annotation.annotationType() != Version.class;
    }
  }

  void performAdd() {
    openInformation(this.getShell(), "Add " + toUpperCaseFirst(this.name()), "Operation not yet supported.");
  }

  void performRemove() {
    openInformation(this.getShell(), "Remove" + toUpperCaseFirst(this.name()), "Operation not yet supported.");
  }

  void performRegister() {
    this.updateSelectedData(true);
    this.updateButtons();
  }

  void performUnregister() {
    this.updateSelectedData(false);
    this.updateButtons();
  }

  void performImport() {
    openInformation(this.getShell(), "Import " + toUpperCaseFirst(this.name()), "Operation not yet supported.");
  }

  void performExport() {
    openInformation(this.getShell(), "Export " + toUpperCaseFirst(this.name()), "Operation not yet supported.");
  }

  void performRefresh() {
    if (this.registrations == null) {
      this.loadInternal(this.sharedPreferences());
    }

    if (!loadedServices()) {
      return;
    }

    for (R registration: newLinkedHashSet(this.registrations)) {
      this.updateData(registration, registration.isRegistered());
    }
  }

  void performOptions() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    R registration = this.cast(selection.toList().get(0));

    AbstractOptionsDialog<P, R> dialog = this.optionsDialog;

    dialog.setTitle("Options for " + ((ITableLabelProvider) this.tableViewer.getLabelProvider()).getColumnText(registration, 0));
    dialog.setPreferences(this.getPreferences());
    dialog.setRegistration(registration);

    dialog.open();

    if (dialog.getReturnCode() == Window.OK) {
      dialog.configure();
    }
  }

  void performNotes() {
    IStructuredSelection selection = (IStructuredSelection) this.tableViewer.getSelection();

    R registration = this.cast(selection.toList().get(0));

    String name = ((ITableLabelProvider) this.tableViewer.getLabelProvider()).getColumnText(registration, 0);
    String message = Annotations.toString(NoteFilter.apply(registration.getAnnotations()));

    openInformation(this.getShell(), "Notes for " + name, !message.isEmpty() ? message : "No notes available.");
  }

  abstract P defaultPreferences();

  abstract P sharedPreferences();

  abstract Set<R> registrations(P preferences);

  @Override
  public final boolean performOk() {
    this.applyInternal();
    this.saveInternal();

    return super.performOk();
  }

  @Override
  public final boolean performCancel() {
    this.loadInternal(this.sharedPreferences());

    return super.performCancel();
  }

  @Override
  protected final void performDefaults() {
    String title = format("Restore %s Defaults", toUpperCaseFirst(pluralize(this.name())));
    String message = format("PerConIK Core is about to restore default state of %s registrations. Configured options are not restored by default, see options dialog to restore effective options individually.", this.name());
    String toggle = format("Restore configured options for all %s", pluralize(this.name()));

    MessageDialogWithToggle dialog = new MessageDialogWithToggle(this.getShell(), title, null, message, MessageDialog.WARNING, new String[] { PROCEED_LABEL, CANCEL_LABEL }, 1, toggle, false);

    if (dialog.open() == 1) {
      return;
    }

    this.registrations = this.registrations(this.defaultPreferences());

    this.restoreOptions.set(dialog.getToggleState());

    this.updatePage();

    super.performDefaults();
  }

  @Override
  protected final void performApply() {
    this.performOk();
  }

  abstract void apply();

  abstract void load(P preferences);

  abstract void save();

  private void applyInternal() {
    if (!loadedServices()) {
      return;
    }

    this.apply();

    this.updatePage();
  }

  private void loadInternal(final P preferences) {
    if (loadedServices()) {
      this.load(preferences);
    }

    this.updatePage();
  }

  private void saveInternal() {
    if (!loadedServices()) {
      return;
    }

    try {
      this.save();
    } catch (RuntimeException failure) {
      String title = "Preferences";
      String message = "Failed to save preferences.";

      openError(this.getShell(), title, message + " See error log for more details.");

      Activator.defaultInstance().getConsole().error(failure, message);
    }
  }

  final void setPreferences(final P preferences) {
    this.preferences = requireNonNull(preferences);
  }

  final P getPreferences() {
    return this.preferences;
  }

  @Override
  public Control getControl() {
    if (!loadedServices() && this.isContentsCreated()) {
      this.preferences = null;
      this.registrations = null;

      this.updatePage();
    }

    return super.getControl();
  }
}
