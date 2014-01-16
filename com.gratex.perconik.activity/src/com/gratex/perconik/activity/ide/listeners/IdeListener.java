package com.gratex.perconik.activity.ide.listeners;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import com.gratex.perconik.activity.ide.plugin.Activator;
import com.gratex.perconik.services.uaca.vs.EventDto;
import com.gratex.perconik.services.uaca.vs.IdeEventDto;
import com.gratex.perconik.services.uaca.vs.IdeSlnPrjEventDto;

/**
 * A base class for all IDE listeners. This listener documents what
 * possible data is available in base abstract types of data transfer
 * objects. These types, in extension hierarchy order, are:
 * 
 * <ul>
 *   <li>{@link EventDto} - root of all events.
 *   <li>{@link IdeEventDto} - root of all IDE events.
 *   <li>{@link IdeSlnPrjEventDto} - root of all IDE events with known
 *   workspace and project.
 * </ul>
 * 
 * <p> TODO document how DTOs are build and what data they contain
 * 
 * <p>Possible data available in an {@code EventDto}:
 * 
 * <ul>
 *   <li>{@code } - .
 * </ul>
 * 
 * <p>Possible data available in an {@code IdeEventDto}:
 * 
 * <ul>
 *   <li>{@code } - .
 * </ul>
 * 
 * <p>Possible data available in an {@code IdeSlnPrjEventDto}:
 * 
 * <ul>
 *   <li>{@code } - .
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
}
