package com.gratex.perconik.activity.ide.ui;

import static org.eclipse.jface.preference.BooleanFieldEditor.SEPARATE_LABEL;
import javax.xml.namespace.QName;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UriFieldEditor;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UrlFieldEditor;
import sk.stuba.fiit.perconik.ui.utilities.Widgets;
import com.gratex.perconik.activity.ide.IdeActivityServices;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences.Keys;
import com.gratex.perconik.activity.plugin.Activator;

public final class IdeActivityPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	private BooleanFieldEditor logErrors;
	
	private BooleanFieldEditor logEvents;
	
	private UrlFieldEditor watcherUrl;
	
	private UriFieldEditor watcherNamespace;
	
	private StringFieldEditor watcherLocalPart;
	
	public IdeActivityPreferencePage()
	{
		super(GRID);
	}

	public final void init(final IWorkbench workbench)
	{
		this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	private static final <E extends StringFieldEditor> E prepare(final E editor)
	{
		editor.setEmptyStringAllowed(false);
		editor.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		return editor;
	}
	
	@Override
	protected final void createFieldEditors()
	{
		this.watcherUrl       = new UrlFieldEditor(Keys.watcherUrl, "URL:", this.getFieldEditorParent());
		this.watcherNamespace = new UriFieldEditor(Keys.watcherNamespace, "Namespace:", this.getFieldEditorParent());
		this.watcherLocalPart = new StringFieldEditor(Keys.watcherLocalPart, "Local part:", this.getFieldEditorParent());

		Widgets.createFieldSeparator(this.getFieldEditorParent());
		
		this.logErrors = new BooleanFieldEditor(Keys.logErrors, "Log errors:", SEPARATE_LABEL, this.getFieldEditorParent());
		this.logEvents = new BooleanFieldEditor(Keys.logEvents, "Log events:", SEPARATE_LABEL, this.getFieldEditorParent());

		this.addField(prepare(this.watcherUrl));
		this.addField(prepare(this.watcherNamespace));
		this.addField(prepare(this.watcherLocalPart));

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
		if (this.watcherUrl != null && this.watcherNamespace != null && this.watcherLocalPart != null)
		{
			try
			{
				QName name = new QName(this.watcherNamespace.getStringValue(), this.watcherLocalPart.getStringValue());

				IdeActivityServices.createWatcherService(this.watcherUrl.getUrlValue(), name);
				IdeActivityServices.releaseWatcherService();
			}
			catch (Exception failure)
			{
				MessageDialog.openError(this.getShell(), "Service error", failure.getMessage());
			}
		}
		
		return super.performOk();
	}
}
