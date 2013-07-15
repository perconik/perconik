// TODO draft

/*

package sk.stuba.fiit.perconik.utilities;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public final class Workbenches
{
	private Workbenches()
	{
		throw new AssertionError();
	}
	
	public static IWorkbenchWindow getActiveWorkbenchWindow()
	{
		IWorkbench workbench = null;
		
		try
		{
			workbench = PlatformUI.getWorkbench();
		}
		catch (IllegalStateException e)
		{
			return null;
		}
		
		IWorkbenchWindow window = null;
		
		if (workbench != null)
		{
			window = workbench.getActiveWorkbenchWindow();
		}
		
		return window;
	}
}

*/
