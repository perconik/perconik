package sk.stuba.fiit.perconik.ui.preferences;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public abstract class AbstractWorkbenchPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
	protected AbstractWorkbenchPreferencePage()
	{
	}
	
	@Override
	public void init(final IWorkbench workbench)
	{
	}
	
	protected final void displayQuestion(final String title, final String message)
	{
		MessageDialog.openQuestion(this.getShell(), title, message);
	}

	protected final void displayNotice(final String title, final String message)
	{
		MessageDialog.openInformation(this.getShell(), title, message);
	}

	protected final void displayWarning(final String title, final String message)
	{
		MessageDialog.openWarning(this.getShell(), title, message);
	}

	protected final void displayError(final String title, final String message)
	{
		MessageDialog.openError(this.getShell(), title, message);
	}
}
