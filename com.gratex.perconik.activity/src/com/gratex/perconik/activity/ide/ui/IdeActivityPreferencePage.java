package com.gratex.perconik.activity.ide.ui;

import java.net.URL;
import javax.xml.namespace.QName;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UriFieldEditor;
import sk.stuba.fiit.perconik.eclipse.jface.preference.UrlFieldEditor;
import com.gratex.perconik.activity.ide.IdeActivityDefaults;
import com.gratex.perconik.activity.ide.IdeActivityServices;
import com.gratex.perconik.activity.ide.plugin.Activator;

public final class IdeActivityPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	private UrlFieldEditor watcherUrl;
	
	private UriFieldEditor watcherNamespace;
	
	private StringFieldEditor watcherLocalPart;
	
	public IdeActivityPreferencePage()
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
		
		URL   url  = IdeActivityDefaults.watcherUrl;
		QName name = IdeActivityDefaults.watcherName;
		
		this.watcherUrl       = new UrlFieldEditor(url.toString(), "URL:", parent);
		this.watcherNamespace = new UriFieldEditor(name.getNamespaceURI().toString() , "Namespace:", parent);
		this.watcherLocalPart = new StringFieldEditor(name.getLocalPart(), "Local part:", parent);
		
		this.addField(prepare(this.watcherUrl));
		this.addField(prepare(this.watcherNamespace));
		this.addField(prepare(this.watcherLocalPart));
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
