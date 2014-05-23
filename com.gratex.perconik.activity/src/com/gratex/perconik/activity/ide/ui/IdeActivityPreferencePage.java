package com.gratex.perconik.activity.ide.ui;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference.Preference;
import sk.stuba.fiit.perconik.eclipse.jface.preference.ExtendedBooleanFieldEditor;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UrlFieldEditor;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;

import com.gratex.perconik.activity.ide.UacaProxy;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys;
import com.gratex.perconik.activity.plugin.Activator;

public final class IdeActivityPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	private BooleanFieldEditor logErrors;
	
	private BooleanFieldEditor logEvents;
	
	private UrlFieldEditor watcherUrl;
	
	private ExtendedBooleanFieldEditor checkConnection;
	
	private ExtendedBooleanFieldEditor displayErrors;

	public IdeActivityPreferencePage()
	{
		super(GRID);
	}

	public final void init(final IWorkbench workbench)
	{
		this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	static final <E extends StringFieldEditor> E prepare(final E editor)
	{
		editor.setEmptyStringAllowed(false);
		editor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		return editor;
	}
	
	@Override
	protected final void createFieldEditors()
	{
		this.watcherUrl       = new UrlFieldEditor(Keys.watcherUrl, "URL:", this.getFieldEditorParent());

		Widgets.createFieldSeparator(this.getFieldEditorParent());
		
		this.checkConnection = new ExtendedBooleanFieldEditor(Keys.checkConnection, "Verify service connection on confirmation", this.getFieldEditorParent());
		this.displayErrors   = new ExtendedBooleanFieldEditor(Keys.displayErrors, "Display warning on service failure", this.getFieldEditorParent());
		
		this.logErrors = new BooleanFieldEditor(Keys.logErrors, "Write errors to Error Log on service failure", this.getFieldEditorParent());
		this.logEvents = new BooleanFieldEditor(Keys.logEvents, "Log processed events (for debug only)", this.getFieldEditorParent());	

		this.addField(prepare(this.watcherUrl));

		this.addField(this.checkConnection);
		this.addField(this.displayErrors);

		this.addField(this.logErrors);
		this.addField(this.logEvents);
	}
	
	@Override
	protected final IPreferenceStore doGetPreferenceStore()
	{
		return IdeActivityPreferences.getPreferenceStore();
	}

	@Override
	public final boolean performOk()
	{
		return super.performOk() && (this.checkConnection.getBooleanValue() ? this.checkConnection() : true);
	}
	
	final boolean checkConnection()
	{
		try
		{
			UacaProxy.checkConnection(this.watcherUrl.getUrlValue().toString());
			
			return true;
		}
		catch (Exception failure)
		{
			String title   = "Activity watcher service error";
			String message = failure.getMessage();
			String toggle  = "Always verify service connection on confirmation";
			
			Preference preference = Preference.usingToggleState(this.getPreferenceStore(), this.checkConnection.getPreferenceName());
			
			boolean state = MessageDialogWithPreference.openError(this.getShell(), title, message, toggle, preference).getToggleState();

			this.checkConnection.getChangeControl().setSelection(state);
			
			return false;
		}
	}
}
