package com.gratex.perconik.activity;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import com.gratex.perconik.activity.plugin.Activator;

public final class ActivityPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	private UrlFieldEditor watcherUrl;
	
	public ActivityPreferencePage()
	{
	}

	public final void init(final IWorkbench workbench)
	{
		this.setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	private static final class UrlFieldEditor extends StringFieldEditor
	{
		UrlFieldEditor(final String name, final String label, final Composite parent)
		{
			super(name, label, parent);
			
			this.setEmptyStringAllowed(false);
			this.setValidateStrategy(StringFieldEditor.VALIDATE_ON_KEY_STROKE);
		}

		@Override
		protected final boolean doCheckState()
		{
			return this.getUrlValue() != null;
		}
		
		public final URL getUrlValue()
		{
			try
			{
				return new URL(this.getStringValue());
			}
			catch (MalformedURLException e)
			{
				return null;
			}
		}
	}
	
	@Override
	protected final void createFieldEditors()
	{
		Composite parent = this.getFieldEditorParent();
		
		this.watcherUrl = new UrlFieldEditor(ActivityPreferences.watcherUrl, "URL:", parent);
		
		this.addField(this.watcherUrl);
	}
	
	@Override
	public final boolean performOk()
	{
		try
		{
			ActivityServices.newWatcherService(this.watcherUrl.getUrlValue());
			ActivityServices.releaseWatcherService();
		}
		catch (Exception failure)
		{
			MessageDialog.openError(this.getShell(), "Service error", failure.getMessage());
		}
		
		return super.performOk();
	}
}
