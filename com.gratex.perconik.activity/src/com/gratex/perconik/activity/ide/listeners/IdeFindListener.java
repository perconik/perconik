package com.gratex.perconik.activity.ide.listeners;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.listeners.CommandExecutionListener;
import com.gratex.perconik.services.uaca.vs.IdeFindOperationDto;

/**
 * A listener of {@code IdeFindOperation} events. This listener creates
 * {@link IdeFindOperationDto} data transfer objects and passes them to
 * the <i>Activity Watcher Service</i> to be transferred into the
 * <i>User Activity Client Application</i> for further processing.
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Unsupported
public final class IdeFindListener extends IdeListener implements CommandExecutionListener
{
	public IdeFindListener()
	{
	}
	
	public final void preExecute(final String id, final ExecutionEvent event)
	{
	}

	public final void postExecuteSuccess(final String id, final Object result)
	{
	}

	public final void postExecuteFailure(final String id, final ExecutionException exception)
	{
	}

	public final void notDefined(final String id, final NotDefinedException exception)
	{
	}

	public final void notEnabled(final String id, final NotEnabledException exception)
	{
	}

	public final void notHandled(final String id, final NotHandledException exception)
	{
	}
}
