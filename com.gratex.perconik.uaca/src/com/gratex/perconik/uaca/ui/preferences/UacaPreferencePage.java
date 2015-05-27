package com.gratex.perconik.uaca.ui.preferences;

import com.google.common.base.Preconditions;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.gratex.perconik.uaca.SharedUacaProxy;
import com.gratex.perconik.uaca.preferences.UacaPreferences;

import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference.Preference;
import sk.stuba.fiit.perconik.eclipse.jface.preference.ExtendedBooleanFieldEditor;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UrlFieldEditor;
import sk.stuba.fiit.perconik.ui.Groups;

import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.applicationUrl;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.checkConnection;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.displayErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logErrors;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logNotices;
import static com.gratex.perconik.uaca.preferences.UacaPreferences.Keys.logRequests;

public final class UacaPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
  private UrlFieldEditor uacaUrlEditor;

  private ExtendedBooleanFieldEditor checkConnectionEditor;

  private ExtendedBooleanFieldEditor displayErrorsEditor;

  private BooleanFieldEditor logRequestsEditor;

  private BooleanFieldEditor logNoticesEditor;

  private BooleanFieldEditor logErrorsEditor;

  public UacaPreferencePage() {
    super(GRID);
  }

  public void init(final IWorkbench workbench) {}

  @Override
  protected void createFieldEditors() {
    Composite parent = this.getFieldEditorParent();

    GridDataFactory factory = GridDataFactory.fillDefaults().grab(true, false).span(2, 1);

    Group serviceGroup = Groups.create(parent, "Service", factory.create());

    this.uacaUrlEditor = new UrlFieldEditor(applicationUrl, "URL:", serviceGroup);

    this.uacaUrlEditor.setEmptyStringAllowed(false);
    this.uacaUrlEditor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);

    this.addField(this.uacaUrlEditor);

    Groups.updateMargins(serviceGroup);

    Group notificationGroup = Groups.create(parent, "Notification", factory.create());

    this.checkConnectionEditor = new ExtendedBooleanFieldEditor(checkConnection, "Verify service connection on confirmation", notificationGroup);
    this.displayErrorsEditor = new ExtendedBooleanFieldEditor(displayErrors, "Display error dialog on service failure", notificationGroup);

    this.addField(this.checkConnectionEditor);
    this.addField(this.displayErrorsEditor);

    Groups.updateMargins(notificationGroup);

    Group logGroup = Groups.create(parent, "Log", factory.create());

    this.logRequestsEditor = new BooleanFieldEditor(logRequests, "Write requests to Workspace Log on send (for debug only)", logGroup);
    this.logNoticesEditor = new BooleanFieldEditor(logNotices, "Write notices to Workspace Log on proxy management", logGroup);
    this.logErrorsEditor = new BooleanFieldEditor(logErrors, "Write errors to Error Log on service failure", logGroup);

    this.addField(this.logRequestsEditor);
    this.addField(this.logNoticesEditor);
    this.addField(this.logErrorsEditor);

    Groups.updateMargins(logGroup);
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return UacaPreferences.getShared().getPreferenceStore();
  }

  @Override
  public boolean performOk() {
    Preconditions.checkState(this.checkConnectionEditor != null);

    return super.performOk() && (this.checkConnectionEditor.getBooleanValue() ? this.checkConnection() : true);
  }

  boolean checkConnection() {
    Preconditions.checkState(this.checkConnectionEditor != null);
    Preconditions.checkState(this.uacaUrlEditor != null);

    try {
      SharedUacaProxy.checkConnection(this.uacaUrlEditor.getUrlValue());

      return true;
    } catch (Exception failure) {
      String title = "UACA Proxy Error";
      String message = failure.getMessage();
      String toggle = "Always verify service connection on confirmation";

      Preference preference = Preference.usingToggleState(this.getPreferenceStore(), this.checkConnectionEditor.getPreferenceName());

      boolean state = MessageDialogWithPreference.openError(this.getShell(), title, message, toggle, preference).getToggleState();

      this.checkConnectionEditor.getChangeControl().setSelection(state);

      return false;
    }
  }
}
