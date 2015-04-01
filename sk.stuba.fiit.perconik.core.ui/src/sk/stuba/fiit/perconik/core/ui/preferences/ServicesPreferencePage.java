package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeoutException;

import javax.annotation.Nullable;

import com.google.common.base.Optional;
import com.google.common.base.StandardSystemProperty;
import com.google.common.util.concurrent.Service.State;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import sk.stuba.fiit.perconik.core.Nameable;
import sk.stuba.fiit.perconik.core.services.Service;
import sk.stuba.fiit.perconik.core.services.ServiceListener;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;
import sk.stuba.fiit.perconik.core.ui.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.core.runtime.Products;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.ElementComparers;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.MapContentProvider;
import sk.stuba.fiit.perconik.eclipse.jface.viewers.RegularTableViewer;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;
import sk.stuba.fiit.perconik.environment.Environment;
import sk.stuba.fiit.perconik.ui.Buttons;
import sk.stuba.fiit.perconik.ui.Groups;
import sk.stuba.fiit.perconik.ui.Labels;
import sk.stuba.fiit.perconik.ui.TableColumns;
import sk.stuba.fiit.perconik.ui.Tables;
import sk.stuba.fiit.perconik.ui.preferences.AbstractWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.concurrent.TimeValue;

import static java.lang.Integer.toHexString;
import static java.lang.String.format;
import static java.lang.System.identityHashCode;
import static java.util.Collections.emptyMap;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.util.concurrent.MoreExecutors.sameThreadExecutor;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

import static org.eclipse.jface.dialogs.MessageDialog.openError;

import static sk.stuba.fiit.perconik.core.plugin.Activator.awaitServices;
import static sk.stuba.fiit.perconik.core.plugin.Activator.defaultInstance;
import static sk.stuba.fiit.perconik.core.plugin.Activator.loadServices;
import static sk.stuba.fiit.perconik.core.plugin.Activator.loadedServices;
import static sk.stuba.fiit.perconik.core.plugin.Activator.unloadServices;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.firstNonNullOrEmpty;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCase;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.optionEquivalence;
import static sk.stuba.fiit.perconik.utilities.configuration.Configurables.rawOptionType;

public final class ServicesPreferencePage extends AbstractWorkbenchPreferencePage {
  static final TimeValue awaitServicesTimeout = TimeValue.of(12, SECONDS);

  static final TimeValue loadServicesTimeout = TimeValue.of(8, SECONDS);

  static final TimeValue unloadServicesTimeout = TimeValue.of(16, SECONDS);

  static final TimeValue stateTransitionDisplayPause = TimeValue.of(200, MILLISECONDS);

  Button load;

  Button unload;

  DetailsDialog detailsDialog;

  Label resourceLabel;

  Label listenerLabel;

  Button resourceButton;

  Button listenerButton;

  public ServicesPreferencePage() {}

  @Override
  public final void createControl(final Composite parent) {
    super.createControl(parent);

    this.updatePage();
  }

  @Override
  protected Control createContent(final Composite parent) {
    this.initializeDialogUnits(parent);
    this.noDefaultAndApplyButton();

    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.marginWidth = 0;
    layout.marginHeight = this.convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
    layout.horizontalSpacing = this.convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
    layout.verticalSpacing = this.convertVerticalDLUsToPixels(10);
    composite.setLayout(layout);

    Group environmentGroup = Groups.create(composite, "Environment");

    environmentGroup.setLayout(new GridLayout(2, false));

    Labels.create(environmentGroup, environmentText());

    Optional<ResourceService> resourceService = resourceService();
    Optional<ListenerService> listenerService = listenerService();

    this.detailsDialog = new DetailsDialog(this.getShell());

    Group resourceGroup = Groups.create(composite, "Resource Service");
    Group listenerGroup = Groups.create(composite, "Listener Service");

    resourceGroup.setLayout(new GridLayout(2, false));
    listenerGroup.setLayout(new GridLayout(2, false));

    this.resourceLabel = Labels.create(resourceGroup, toState(resourceService));
    this.listenerLabel = Labels.create(listenerGroup, toState(listenerService));

    this.resourceButton = Buttons.createRegular(resourceGroup, "Details", new WidgetListener() {
      public void handleEvent(final Event event) {
        DetailsDialog dialog = ServicesPreferencePage.this.detailsDialog;

        dialog.setTitle("Resource Service Details");
        dialog.setComponents(toResourceComponents(resourceService()));

        dialog.open();
      }
    });

    this.listenerButton = Buttons.createRegular(listenerGroup, "Details", new WidgetListener() {
      public void handleEvent(final Event event) {
        DetailsDialog dialog = ServicesPreferencePage.this.detailsDialog;

        dialog.setTitle("Listener Service Details");
        dialog.setComponents(toListenerComponents(listenerService()));

        dialog.open();
      }
    });

    Dialog.applyDialogFont(composite);

    return composite;
  }

  @Override
  protected void contributeButtons(final Composite parent) {
    ((GridLayout) parent.getLayout()).numColumns += 2;

    this.load = Buttons.createCentering(parent, "Load", new WidgetListener() {
      public void handleEvent(final Event event) {
        performLoad();
      }
    });

    this.unload = Buttons.createCentering(parent, "Unload", new WidgetListener() {
      public void handleEvent(final Event event) {
        performUnload();
      }
    });

    this.registerServiceStateListeners();
    this.updateButtons();
  }

  static String environmentText() {
    SmartStringBuilder text = new SmartStringBuilder();

    IProduct product = Platform.getProduct();

    text.format("%s %s%n", StandardSystemProperty.JAVA_VM_NAME.value(), Environment.getJavaVersion());
    text.format("%s %s%n", product.getName(), Products.getVersion(product));
    text.format("PerConIK Core %s%n", defaultInstance().getBundle().getVersion());
    text.format("Debug plug-in %s%n", Environment.debug ? "enabled" : "disabled");

    return text.toString();
  }

  static Optional<ResourceService> resourceService() {
    try {
      return of(Services.getResourceService());
    } catch (UnsupportedOperationException e) {
      return absent();
    }
  }

  static Optional<ListenerService> listenerService() {
    try {
      return of(Services.getListenerService());
    } catch (UnsupportedOperationException e) {
      return absent();
    }
  }

  static abstract class ServiceStateListener<S extends Service> extends ServiceListener {
    final S service;

    ServiceStateListener(final S service) {
      this.service = checkNotNull(service);
    }
  }

  void registerServiceStateListeners() {
    Optional<ResourceService> resourceService = resourceService();
    Optional<ListenerService> listenerService = listenerService();

    if (resourceService.isPresent()) {
      ResourceService service = resourceService.get();

      service.addListener(new ServiceStateListener<ResourceService>(service) {
        @Override
        protected void transit(final State from, final State to, @Nullable final Throwable failure) {
          Optional<ResourceService> service = resourceService();

          if (service.isPresent() && identityHashCode(service.get()) == identityHashCode(this.service)) {
            setResourceTransition(from, to);

            sleepUninterruptibly(stateTransitionDisplayPause.duration(), stateTransitionDisplayPause.unit());
          }
        }
      }, sameThreadExecutor());
    }

    if (listenerService.isPresent()) {
      ListenerService service = listenerService.get();

      service.addListener(new ServiceStateListener<ListenerService>(service) {
        @Override
        protected void transit(final State from, final State to, @Nullable final Throwable failure) {
          Optional<ListenerService> service = listenerService();

          if (service.isPresent() && identityHashCode(service.get()) == identityHashCode(this.service)) {
            setListenerTransition(from, to);

            sleepUninterruptibly(stateTransitionDisplayPause.duration(), stateTransitionDisplayPause.unit());
          }
        }
      }, sameThreadExecutor());
    }
  }

  void updatePage() {
    this.updateMessage();
    this.updateStates();
    this.updateButtons();
  }

  void updateMessage() {
    if (loadedServices()) {
      this.setErrorMessage(null);
    } else {
      this.setErrorMessage("Core services not loaded");
    }
  }

  void updateStates() {
    this.setResourceState(resourceService());
    this.setListenerState(listenerService());
  }

  void updateButtons() {
    boolean loaded = loadedServices();

    this.load.setEnabled(!loaded);
    this.unload.setEnabled(loaded);

    this.resourceButton.setEnabled(loaded);
    this.listenerButton.setEnabled(loaded);
  }

  void performLoad() {
    try {
      checkState(loadedServices() == false, "Services already loaded");

      this.load.setEnabled(false);

      try {
        loadServices(new Runnable() {
          public void run() {
            registerServiceStateListeners();
          }
        }, loadServicesTimeout);

        try {
          awaitServices(awaitServicesTimeout);
        } catch (TimeoutException failure) {
          this.handleTimeout(failure, "awaiting");
        }
      } catch (TimeoutException failure) {
        this.handleTimeout(failure, "loading");
      }
    } catch (RuntimeException failure) {
      this.handleFailure(failure);
    }

    this.updatePage();
  }

  void performUnload() {
    try {
      checkState(loadedServices() == true, "Services already unloaded");

      this.unload.setEnabled(false);

      unloadServices(unloadServicesTimeout);
    } catch (TimeoutException failure) {
      this.handleTimeout(failure, "unloading");
    } catch (Throwable failure) {
      this.handleFailure(failure);
    }

    this.updatePage();
  }

  void handleTimeout(final TimeoutException failure, final String action) {
    String title = "Core Services";
    String message = format("Unexpected timeout while %s services.", action);

    openError(this.getShell(), title, message + " See error log for more details.");

    Activator.defaultInstance().getConsole().error(failure, message);
  }

  void handleFailure(final Throwable failure) {
    String title = "Core Services";
    String message = firstNonNullOrEmpty(failure.getMessage(), "Unexpected failure") + ".";

    openError(this.getShell(), title, message + " See error log for more details.");

    Activator.defaultInstance().getConsole().error(failure, message);
  }

  static final class DetailsDialog extends StatusDialog {
    Map<String, Nameable> components;

    CheckboxTableViewer tableViewer;

    DetailsDialog(final Shell parent) {
      super(parent);
    }

    @Override
    protected Control createDialogArea(final Composite parent) {
      Composite composite = new Composite(parent, SWT.NONE);

      GridLayout parentLayout = new GridLayout();
      parentLayout.numColumns = 1;
      parentLayout.marginHeight = 5;
      parentLayout.marginWidth = 5;
      composite.setLayout(parentLayout);
      composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

      Composite tableComposite = new Composite(parent, SWT.NONE);
      TableColumnLayout tableLayout = new TableColumnLayout();

      GridData tableGrid = new GridData(GridData.FILL_BOTH);
      tableGrid.widthHint = 360;
      tableGrid.heightHint = this.convertHeightInCharsToPixels(10);
      tableComposite.setLayout(tableLayout);
      tableComposite.setLayoutData(tableGrid);

      Table table = Tables.create(tableComposite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

      GC gc = new GC(this.getShell());
      gc.setFont(JFaceResources.getDialogFont());

      TableColumns.create(table, tableLayout, "Component", gc, 1);
      TableColumns.create(table, tableLayout, "Implementation", gc, 4);
      TableColumns.create(table, tableLayout, "Name", gc, 4);
      TableColumns.create(table, tableLayout, "Hash", gc, 1);
      TableColumns.create(table, tableLayout, "Identity", gc, 1);

      gc.dispose();

      this.tableViewer = new RegularTableViewer(table);

      this.tableViewer.setComparer(ElementComparers.fromEquivalence(rawOptionType(), optionEquivalence()));
      this.tableViewer.setContentProvider(new MapContentProvider());
      this.tableViewer.setLabelProvider(new ComponentLabelProvider());

      this.updateTable();

      Dialog.applyDialogFont(composite);

      return composite;
    }

    final class ComponentLabelProvider extends LabelProvider implements ITableLabelProvider {
      ComponentLabelProvider() {}

      public String getColumnText(final Object element, final int column) {
        Entry<?, ?> entry = (Entry<?, ?>) element;
        String type = entry.getKey().toString();
        Nameable object = (Nameable) entry.getValue();

        switch (column) {
          case 0:
            return type;

          case 1:
            return object.getClass().getName();

          case 2:
            return object.getName();

          case 3:
            return toHexString(object.hashCode());

          case 4:
            return toHexString(identityHashCode(object));

          default:
            throw new IllegalStateException();
        }
      }

      public Image getColumnImage(final Object element, final int columnIndex) {
        return null;
      }
    }

    void updateTable() {
      this.tableViewer.setInput(this.components);
      this.tableViewer.refresh();
    }

    void setComponents(final Map<String, Nameable> components) {
      this.components = checkNotNull(components);
    }

    @Override
    protected IDialogSettings getDialogBoundsSettings() {
      return DialogSettings.getOrCreateSection(Activator.defaultInstance().getDialogSettings(), DetailsDialog.class.getName());
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

  static Map<String, Nameable> toResourceComponents(final Optional<? extends ResourceService> service) {
    if (service.isPresent()) {
      return toResourceComponents(service.get());
    }

    return emptyMap();
  }

  static Map<String, Nameable> toResourceComponents(final ResourceService service) {
    Map<String, Nameable> components = newHashMap();

    components.put("service", service);

    if (service.state() == State.RUNNING) {
      components.put("provider", service.getResourceProvider());
      components.put("manager", service.getResourceManager());
    }

    return components;
  }

  static Map<String, Nameable> toListenerComponents(final Optional<? extends ListenerService> service) {
    if (service.isPresent()) {
      return toListenerComponents(service.get());
    }

    return emptyMap();
  }

  static Map<String, Nameable> toListenerComponents(final ListenerService service) {
    Map<String, Nameable> components = newHashMap();

    components.put("service", service);

    if (service.state() == State.RUNNING) {
      components.put("provider", service.getListenerProvider());
      components.put("manager", service.getListenerManager());
    }

    return components;
  }

  static String toState(final Optional<? extends Service> service) {
    return service.isPresent() ? toState(service.get()) : "Unresolved setup";
  }

  static String toState(final Service service) {
    boolean loaded = loadedServices();

    return format("%s and %s…", loaded ? "Loaded" : "Unloaded", toLowerCase(service.state()));
  }

  static String toTransition(final State from, final State to) {
    boolean loaded = loadedServices();

    return format("%s and in transition from %s to %s…", loaded ? "Loaded" : "Unloaded", toLowerCase(from), toLowerCase(to));
  }

  void setResourceState(final Optional<ResourceService> service) {
    if (!this.resourceLabel.isDisposed()) {
      this.resourceLabel.setText(toState(service));
    }
  }

  void setListenerState(final Optional<ListenerService> service) {
    if (!this.listenerLabel.isDisposed()) {
      this.listenerLabel.setText(toState(service));
    }
  }

  void setResourceTransition(final State from, final State to) {
    if (!this.resourceLabel.isDisposed()) {
      this.resourceLabel.setText(toTransition(from, to));
    }
  }

  void setListenerTransition(final State from, final State to) {
    if (!this.listenerLabel.isDisposed()) {
      this.listenerLabel.setText(toTransition(from, to));
    }
  }

  @Override
  public Control getControl() {
    if (this.isContentCreated()) {
      this.updatePage();
    }

    return super.getControl();
  }
}
