package sk.stuba.fiit.perconik.eclipse.core.commands;

import javax.annotation.Nullable;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.handlers.IHandlerService;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;
import com.google.common.base.Preconditions;

/**
 * Static utility methods pertaining to Eclipse command handlers.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Handlers
{
	private Handlers()
	{
		throw new AssertionError();
	}

	/**
	 * Gets the handler service.
	 * @return the handler service or {@code null} if
	 *         the workbench has not been created yet
	 */
	public static final IHandlerService getHandlerService()
	{
		return getHandlerService(Workbenches.getWorkbench());
	}
	
	/**
	 * Gets the handler service.
	 * @param workbench the workbench, may be {@code null}
	 * @return the handler service or {@code null} if the workbench
	 *         is {@code null} or if the workbench has no handler service
	 */
	public static final IHandlerService getHandlerService(@Nullable final IWorkbench workbench)
	{
		if (workbench == null)
		{
			return null;
		}
		
		return (IHandlerService) workbench.getService(IHandlerService.class);
	}
	
	/**
	 * Waits for the handler service.
	 * This method blocks until there is an available handler service.
	 * @see #getHandlerService()
	 */
	public static final IHandlerService waitForHandlerService()
	{
		return waitForHandlerService(Workbenches.waitForWorkbench());
	}
	
	/**
	 * Waits for the handler service.
	 * This method blocks until there is an available handler service.
	 * @param workbench the workbench, can not be {@code null}
	 * @see #getHandlerService(IWorkbench)
	 */
	public static final IHandlerService waitForHandlerService(final IWorkbench workbench)
	{
		Preconditions.checkNotNull(workbench);
		
		IHandlerService service;
		
		while ((service = getHandlerService(workbench)) == null) {}
		
		return service;
	}
}
