package sk.stuba.fiit.perconik.activity.ui.preferences;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

import sk.stuba.fiit.perconik.activity.preferences.ActivityPreferences;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.WidgetListener;
import sk.stuba.fiit.perconik.ui.Buttons;
import sk.stuba.fiit.perconik.ui.Groups;
import sk.stuba.fiit.perconik.ui.Labels;
import sk.stuba.fiit.perconik.ui.preferences.AbstractWorkbenchPreferencePage;

import static sk.stuba.fiit.perconik.core.plugin.Activator.loadedServices;

public final class ActivityPreferencePage extends AbstractWorkbenchPreferencePage {
  Button defaultOptionsButton;

  DefaultOptionsDialog defaultOptionsDialog;

  public ActivityPreferencePage() {}

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

    composite.setLayout(GridLayoutFactory.fillDefaults().create());

    Group defaultOptionsGroup = Groups.create(composite, "Listener Default Options");

    defaultOptionsGroup.setLayout(new GridLayout(2, false));

    Labels.create(defaultOptionsGroup, "Adjust default options for all activity listeners");

    this.defaultOptionsButton = Buttons.create(defaultOptionsGroup, "Edit", new WidgetListener() {
      public void handleEvent(final Event event) {
        performDefaultOptions();
      }
    });

    this.defaultOptionsDialog = new DefaultOptionsDialog(this.getShell());

    Dialog.applyDialogFont(composite);

    return composite;
  }

  void updatePage() {
    this.updateMessage();
    this.updateButtons();
  }

  void updateMessage() {
    if (loadedServices()) {
      this.setErrorMessage(null);
    } else {
      this.setErrorMessage("Core services not loaded");
    }
  }

  void updateButtons() {
    boolean loaded = loadedServices();

    this.defaultOptionsButton.setEnabled(loaded);
  }

  void performDefaultOptions() {
    DefaultOptionsDialog dialog = this.defaultOptionsDialog;

    dialog.setTitle("Listener Default Options");
    dialog.setActivityPreferences(ActivityPreferences.getShared());

    dialog.open();

    if (dialog.getReturnCode() == Window.OK) {
      dialog.configure();
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
