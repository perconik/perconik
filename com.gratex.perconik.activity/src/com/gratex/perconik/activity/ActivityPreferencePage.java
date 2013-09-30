package com.gratex.perconik.activity;

import javax.xml.namespace.QName;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UriFieldEditor;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UrlFieldEditor;
import com.gratex.perconik.activity.plugin.Activator;

public final class ActivityPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	private UrlFieldEditor watcherUrl;
	
	private UriFieldEditor watcherNamespace;
	
	private StringFieldEditor watcherLocalPart;
	
	public ActivityPreferencePage()
	{
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
		Composite parent = this.getFieldEditorParent();
		
		this.watcherUrl       = new UrlFieldEditor(ActivityPreferences.watcherUrl, "URL:", parent);
		this.watcherNamespace = new UriFieldEditor(ActivityPreferences.watcherNamespace , "Namespace:", parent);
		this.watcherLocalPart = new StringFieldEditor(ActivityPreferences.watcherLocalPart , "Local part:", parent);
		
		this.addField(prepare(this.watcherUrl));
		this.addField(prepare(this.watcherNamespace));
		this.addField(prepare(this.watcherLocalPart));
	}
	
	@Override
	public final boolean performOk()
	{
		try
		{
			QName name = new QName(this.watcherNamespace.getStringValue(), this.watcherLocalPart.getStringValue());
			
			ActivityServices.newWatcherService(this.watcherUrl.getUrlValue(), name);
			ActivityServices.releaseWatcherService();
		}
		catch (Exception failure)
		{
			MessageDialog.openError(this.getShell(), "Service error", failure.getMessage());
		}
		
		return super.performOk();
	}
}
