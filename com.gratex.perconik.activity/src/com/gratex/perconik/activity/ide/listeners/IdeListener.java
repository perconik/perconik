package com.gratex.perconik.activity.ide.listeners;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import com.gratex.perconik.activity.ide.IdeApplication;
import com.gratex.perconik.activity.ide.plugin.Activator;
import com.gratex.perconik.services.uaca.vs.EventDto;
import com.gratex.perconik.services.uaca.vs.IdeEventDto;
import com.gratex.perconik.services.uaca.vs.IdeSlnPrjEventDto;

/**
 * A base class for all IDE listeners. This listener documents what
 * data is available in base abstract types of data transfer objects.
 * These abstract types, in type hierarchy order, are:
 * 
 * <ul>
 *   <li>{@link EventDto} - root of all events.
 *   <li>{@link IdeEventDto} - root of all IDE events.
 *   <li>{@link IdeSlnPrjEventDto} - root of all IDE events with known
 *   event related project and workspace.
 * </ul>
 * 
 * <p>Data available in an {@code EventDto}:
 * 
 * <ul>
 *   <li>{@code isMilestone} - activity milestone flag, set to {@code true}
 *   if the event is an instance of:
 *     <ul>
 *       <li>{@code IdeCheckinDto},
 *       <li>{@code IdeCodeOperationDto},
 *       <li>{@code IdeDocumentOperationDto} and document operation type is
 *       {@code ADD}, {@code REMOVE} or {@code SAVE},
 *       <li>{@code IdeProjectOperationDto},
 *       <li>{@code IdeStateChangeDto}.
 *     </ul>
 *   <li>{@code time} - time when the event occurred with time zone
 *   and precision set to hundredth seconds.
 * </ul>
 * 
 * <p>Data available in an {@code IdeEventDto}:
 * 
 * <ul>
 *   <li>{@code applicationName} - IDE application name,
 *   for example {@code Eclipse Platform}.
 *   <li>{@code applicationVersion} - IDE application version,
 *   for example {@code 4.3.1.v20130911-1000}.
 *   <li>{@code idePid} - IDE application process identifier
 *   or {@code -1} if it can not be determined.
 * </ul>
 * 
 * <p>Data available in an {@code IdeSlnPrjEventDto}:
 * 
 * <ul>
 *   <li>{@code projectName} - related project name.
 *   <li>{@code solutionName} - related workspace name (workspace of the
 *   related project). The workspace name is the workspace root directory
 *   name.
 * </ul>
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class IdeListener extends Adapter
{
	static final PluginConsole console = Activator.getDefault().getConsole();
	
	static final Executor executor = Executors.newCachedThreadPool();
	
	IdeListener()
	{
	}
	
	static final long currentTime()
	{
		return System.currentTimeMillis();
	}
	
	static final IEditorPart dereferenceEditor(final IEditorReference reference)
	{
		return reference.getEditor(false);
	}

	static final void execute(final Runnable command)
	{
		executor.execute(command);
	}

	static final void executeSafely(final Runnable command)
	{
		Display.getDefault().asyncExec(command);
	}

	static final boolean isDebug()
	{
		return IdeApplication.getInstance().isDebug();
	}
}
