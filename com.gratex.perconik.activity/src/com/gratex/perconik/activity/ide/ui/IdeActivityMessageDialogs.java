package com.gratex.perconik.activity.ide.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference;
import sk.stuba.fiit.perconik.eclipse.jface.dialogs.MessageDialogWithPreference.Preference;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;
import com.gratex.perconik.activity.ide.preferences.IdeActivityPreferences;

public final class IdeActivityMessageDialogs
{
	private IdeActivityMessageDialogs()
	{
		throw new AssertionError();
	}

	public static void openError(final String key, final String message)
	{
		openError(key, message, "Always display warning on service failure");
	}
	
	public static void openError(final String key, final String message, final String toggle)
	{
		final Runnable dialog = new Runnable()
		{
			public final void run()
			{
				IWorkbenchWindow window = Workbenches.getActiveWindow();
				
				Shell shell = window != null ? window.getShell() : Display.getDefault().getActiveShell(); 

				String title = "UACA proxy error";

				Preference preference = Preference.usingToggleState(IdeActivityPreferences.getPreferenceStore(), key);  
		
				MessageDialogWithPreference.openError(shell, title, message, toggle, preference).setBlockOnOpen(true);
			}
		};
		
		Display.getDefault().asyncExec(dialog);
	}
}
