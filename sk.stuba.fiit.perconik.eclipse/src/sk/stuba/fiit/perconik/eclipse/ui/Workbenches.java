package sk.stuba.fiit.perconik.eclipse.ui;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.environment.Environment;
import sk.stuba.fiit.perconik.environment.plugin.Activator;

public final class Workbenches
{
	private Workbenches()
	{
		throw new AssertionError();
	}
	
	public static final IWorkbench getWorkbench()
	{
		try
		{
			return PlatformUI.getWorkbench();
		}
		catch (IllegalStateException e)
		{
			if (Environment.debug)
			{
				PluginConsole.of(Activator.getDefault()).error("Workbench has not been created yet.", e);
			}
			
			return null;
		}
	}
	
	public static final IWorkbenchWindow getActiveWindow()
	{
		IWorkbench workbench = getWorkbench(); 
		
		if (workbench == null)
		{
			return null;
		}
		
		return workbench.getActiveWorkbenchWindow();
	}
	
	public static final IWorkbenchPage getActivePage()
	{
		IWorkbenchWindow window = getActiveWindow();
		
		if (window == null)
		{
			return null;
		}
		
		return window.getActivePage();
	}
	
	public static final IWorkbenchWindow waitForActiveWindow()
	{
		IWorkbenchWindow window;
		
		while ((window = getActiveWindow()) == null);
		
		return window;
	}

	public static final IWorkbenchPage waitForActivePage()
	{
		IWorkbenchPage page;
		
		while ((page = getActivePage()) == null);
		
		return page;
	}
}
