package com.gratex.perconik.activity.ide.listeners;

import java.util.concurrent.Executor;
import org.eclipse.swt.widgets.Display;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayTask;
import sk.stuba.fiit.perconik.utilities.concurrent.PlatformExecutors;
import com.gratex.perconik.activity.ide.IdeConsole;
import com.gratex.perconik.services.uaca.ide.BaseIdeEventRequest;

/**
 * A base class for all IDE listeners. This listener documents available
 * data in data transfer objects of type {@link BaseIdeEventRequest} being
 * a common base for all other data transfer object types.
 * 
 * <p>Data available in a {@code BaseIdeEventRequest}:
 * 
 * <ul>
 *   <li>{@code appName} - IDE application name,
 *   for example {@code Eclipse Platform}.
 *   <li>{@code appVersion} - IDE application version,
 *   for example {@code 4.3.1.v20130911-1000}.
 *   <li>{@code projectName} - related project name. In case when the project
 *   name can not be determined, such as when not editable source code is
 *   selected, the project name is set to the accompanying library, mostly
 *   some JAR file, for example {@code "rt.jar"} for standard JRE system
 *   library. Note that the workspace name always is preserved.
 *   <li>{@code sessionId} - IDE application process identifier
 *   or {@code -1} if it can not be determined.
 *   <li>{@code solutionName} - related workspace name (workspace of the
 *   related project). The workspace name is the workspace root directory
 *   name.
 *   <li>{@code timestamp} - time when the event occurred, UTC time zone,
 *   precision set to hundredth seconds.
 * </ul>
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class IdeListener extends Adapter
{
	static final PluginConsole console = IdeConsole.getInstance();
	
	private static final Executor displayExecutor = DisplayExecutor.defaultSynchronous();

	private static final Executor sharedExecutor = PlatformExecutors.newLimitedThreadPool();

	IdeListener()
	{
	}
	
	static final void execute(final Runnable command)
	{
		sharedExecutor.execute(command);
	}

	static final <V> V execute(final DisplayTask<V> task)
	{
		return task.get(displayExecutor);
	}
	
	// TODO refactor all async operations to do minimal job in display threads
	// TODO rm
	@Deprecated
	static final void executeSafely(final Runnable command)
	{
		Display.getDefault().asyncExec(command);
	}
}
