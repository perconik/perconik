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
import com.gratex.perconik.uaca.preferences.UacaPreferences.Keys;

import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference.Preference;
import sk.stuba.fiit.perconik.eclipse.jface.preference.ExtendedBooleanFieldEditor;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UrlFieldEditor;
import sk.stuba.fiit.perconik.ui.Groups;

public final class UacaPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
  private UrlFieldEditor uacaUrl;

  private ExtendedBooleanFieldEditor checkConnection;

  private ExtendedBooleanFieldEditor displayErrors;

  private BooleanFieldEditor logErrors;

  private BooleanFieldEditor logEvents;

  public UacaPreferencePage() {
    super(GRID);
  }

  public void init(final IWorkbench workbench) {
    this.setPreferenceStore(this.doGetPreferenceStore());
  }

  @Override
  protected void createFieldEditors() {
    Composite parent = this.getFieldEditorParent();

    GridDataFactory factory = GridDataFactory.fillDefaults().grab(true, false).span(2, 1);

    Group group = Groups.create(parent, "Service", factory.create());

    this.uacaUrl = new UrlFieldEditor(Keys.applicationUrl, "URL:", group);

    this.uacaUrl.setEmptyStringAllowed(false);
    this.uacaUrl.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);

    this.addField(this.uacaUrl);

    Groups.updateMargins(group);

    Group secureGroup = Groups.create(parent, "Notification", factory.create());

    this.checkConnection = new ExtendedBooleanFieldEditor(Keys.checkConnection, "Verify service connection on confirmation", secureGroup);
    this.displayErrors = new ExtendedBooleanFieldEditor(Keys.displayErrors, "Display warning on service failure", secureGroup);

    this.addField(this.checkConnection);
    this.addField(this.displayErrors);

    Groups.updateMargins(secureGroup);

    Group b = Groups.create(parent, "Log", factory.create());

    this.logErrors = new BooleanFieldEditor(Keys.logErrors, "Write errors to Error Log on service failure", b);
    this.logEvents = new BooleanFieldEditor(Keys.logEvents, "Write events to Workspace Log (for debug only)", b);

    this.addField(this.logErrors);
    this.addField(this.logEvents);

    Groups.updateMargins(b);
  }

  @Override
  protected IPreferenceStore doGetPreferenceStore() {
    return UacaPreferences.getShared().getPreferenceStore();
  }

  @Override
  public boolean performOk() {
    Preconditions.checkState(this.checkConnection != null);

    return super.performOk() && (this.checkConnection.getBooleanValue() ? this.checkConnection() : true);
  }

  boolean checkConnection() {
    Preconditions.checkState(this.checkConnection != null);
    Preconditions.checkState(this.uacaUrl != null);

    try {
      SharedUacaProxy.checkConnection(this.uacaUrl.getUrlValue());

      return true;
    } catch (Exception failure) {
      String title = "UACA proxy error";
      String message = failure.getMessage();
      String toggle = "Always verify service connection on confirmation";

      Preference preference = Preference.usingToggleState(this.getPreferenceStore(), this.checkConnection.getPreferenceName());

      boolean state = MessageDialogWithPreference.openError(this.getShell(), title, message, toggle, preference).getToggleState();

      this.checkConnection.getChangeControl().setSelection(state);

      return false;
    }
  }
}
