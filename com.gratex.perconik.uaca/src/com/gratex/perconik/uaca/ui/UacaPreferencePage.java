package com.gratex.perconik.uaca.ui;

import com.google.common.base.Preconditions;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.gratex.perconik.uaca.SharedUacaProxy;
import com.gratex.perconik.uaca.plugin.Activator;
import com.gratex.perconik.uaca.preferences.UacaPreferences;
import com.gratex.perconik.uaca.preferences.UacaPreferences.Keys;

import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference.Preference;
import sk.stuba.fiit.perconik.eclipse.jface.preference.ExtendedBooleanFieldEditor;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UrlFieldEditor;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;

public final class UacaPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
  private BooleanFieldEditor logErrors;

  private BooleanFieldEditor logEvents;

  private UrlFieldEditor uacaUrl;

  private ExtendedBooleanFieldEditor checkConnection;

  private ExtendedBooleanFieldEditor displayErrors;

  public UacaPreferencePage() {
    super(GRID);
  }

  public final void init(final IWorkbench workbench) {
    this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
  }

  static final <E extends StringFieldEditor> E prepare(final E editor) {
    editor.setEmptyStringAllowed(false);
    editor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);

    return editor;
  }

  @Override
  protected final void createFieldEditors() {
    this.uacaUrl = new UrlFieldEditor(Keys.uacaUrl, "URL:", this.getFieldEditorParent());

    Widgets.createFieldSeparator(this.getFieldEditorParent());

    this.checkConnection = new ExtendedBooleanFieldEditor(Keys.checkConnection, "Verify service connection on confirmation", this.getFieldEditorParent());
    this.displayErrors = new ExtendedBooleanFieldEditor(Keys.displayErrors, "Display warning on service failure", this.getFieldEditorParent());

    this.logErrors = new BooleanFieldEditor(Keys.logErrors, "Write errors to Error Log on service failure", this.getFieldEditorParent());
    this.logEvents = new BooleanFieldEditor(Keys.logEvents, "Log processed events (for debug only)", this.getFieldEditorParent());

    this.addField(prepare(this.uacaUrl));

    this.addField(this.checkConnection);
    this.addField(this.displayErrors);

    this.addField(this.logErrors);
    this.addField(this.logEvents);
  }

  @Override
  protected final IPreferenceStore doGetPreferenceStore() {
    return UacaPreferences.getShared().getPreferenceStore();
  }

  @Override
  public final boolean performOk() {
    Preconditions.checkState(this.checkConnection != null);

    return super.performOk() && (this.checkConnection.getBooleanValue() ? this.checkConnection() : true);
  }

  final boolean checkConnection() {
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
