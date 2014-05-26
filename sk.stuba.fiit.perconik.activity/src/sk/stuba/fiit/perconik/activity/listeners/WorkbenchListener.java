package sk.stuba.fiit.perconik.activity.listeners;

import org.eclipse.ui.IWorkbench;
import sk.stuba.fiit.perconik.activity.data.core.StandardCoreProbe;
import sk.stuba.fiit.perconik.activity.data.eclipse.StandardPlatformProbe;
import sk.stuba.fiit.perconik.activity.data.events.EventData;
import sk.stuba.fiit.perconik.activity.data.events.WorkbenchData;
import sk.stuba.fiit.perconik.activity.data.system.StandardSystemProbe;
import sk.stuba.fiit.perconik.core.annotations.Unsupported;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.plugin.Activator;

/**
 * TODO
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
@Unsupported
@Version("0.0.1")
public final class WorkbenchListener extends Listener implements sk.stuba.fiit.perconik.core.listeners.WorkbenchListener
{
	public WorkbenchListener()
	{
	}

	public enum Action
	{
		STARTUP,
		
		SHUTDOWN;

		@Override
		public final String toString()
		{
			return this.name().toLowerCase();
		}
	}
	
	final void process(EventData data)
	{
		System.out.println(data.toString(true));
		System.out.println(data.toMap(true));
	}
	
	final WorkbenchData build(final Action action)
	{
		WorkbenchData data = new WorkbenchData();
	
		data.setTime(currentTime());
		data.setAction(action.toString());

		data.set("core", new StandardCoreProbe().core());
		data.set("platform", new StandardPlatformProbe().platform());
		data.set("system", new StandardSystemProbe().system());
		
		return data;
	}
	
	@Override
	public final void postRegister()
	{
		Activator.waitForExtensions();
		
		process(build(Action.STARTUP));
	}

	public final boolean preShutdown(final IWorkbench workbench, boolean forced)
	{
		process(build(Action.SHUTDOWN));
		
		return true;
	}

	public final void postShutdown(final IWorkbench workbench)
	{
	}
}
