package sk.stuba.fiit.perconik.eclipse.core.commands;

import javax.annotation.Nullable;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.commands.ICommandService;
import com.google.common.base.Preconditions;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

public final class Commands
{
	private Commands()
	{
		throw new AssertionError();
	}
	
	public static final ICommandService getCommandService()
	{
		return getCommandService(Workbenches.getWorkbench());
	}
	
	public static final ICommandService getCommandService(@Nullable final IWorkbench workbench)
	{
		if (workbench == null)
		{
			return null;
		}
		
		return (ICommandService) workbench.getAdapter(ICommandService.class);
	}
	
	public static final ICommandService waitForCommandService()
	{
		return waitForCommandService(Workbenches.waitForWorkbench());
	}
	
	public static final ICommandService waitForCommandService(final IWorkbench workbench)
	{
		Preconditions.checkNotNull(workbench);
		
		ICommandService service;
		
		while ((service = getCommandService(workbench)) == null) {}
		
		return service;
	}
}
