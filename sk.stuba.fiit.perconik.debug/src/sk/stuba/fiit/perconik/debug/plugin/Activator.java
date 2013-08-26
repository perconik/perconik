package sk.stuba.fiit.perconik.debug.plugin;

import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.eclipse.ui.IShutdown;
import sk.stuba.fiit.perconik.eclipse.ui.plugin.AbstractPlugin;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author Pavol Zbell
 * @version 0.0.1
 */
public class Activator extends AbstractPlugin
{
	/**
	 * The plug-in identifier.
	 */
	public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.debug";

	/**
	 * The shared instance.
	 */
	private static Activator plugin;

	final DebugLoader loader;
	
	/**
	 * The constructor.
	 */
	public Activator()
	{
		super(DebugConsole.factory());
		
		this.console.put("Constructing %s ... ", this.getClass().getName());
		
		this.loader = DebugLoader.create();
		
		this.console.print("done");
	}

	/**
	 * Gets the shared instance.
	 * 
	 * @return Returns the shared instance.
	 */
	public static final Activator getDefault()
	{
		return plugin;
	}
	
	private static abstract class Hook
	{
		Hook()
		{
			Debug.print("Constructing %s ... done", this.getClass().getName());
		}
	}

	public static final class Startup extends Hook implements IStartup
	{
		public final void earlyStartup()
		{
			Debug.print("Executing early startup %s:", this.getClass().getName());
			Debug.tab();
			
			getDefault().loader.load();

			Debug.untab();
			Debug.print("Early startup %s finished", this.getClass().getName());
			
			DebugResources.printRegistrations();
			DebugListeners.printRegistrations();
		}
	}

	public static final class Shutdown extends Hook implements IShutdown
	{
		public final void earlyShutdown()
		{
			Debug.print("Executing early shutdown %s:", this.getClass().getName());
			Debug.tab();

			getDefault().loader.unload();
			
			Debug.untab();
			Debug.print("Early shutdown %s finished", this.getClass().getName());
		}
	}

	@Override
	public final void start(final BundleContext context) throws Exception
	{
		this.console.put("Starting %s ... ", PLUGIN_ID);
		
		super.start(context);

		plugin = this;
		
		this.console.print("done");
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		new Shutdown().earlyShutdown();
		
		this.console.put("Stopping %s ... ", PLUGIN_ID);
		
		plugin = null;

		super.stop(context);
		
		this.console.print("done");
	}
}
