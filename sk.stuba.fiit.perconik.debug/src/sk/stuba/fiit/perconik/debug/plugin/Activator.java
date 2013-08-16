package sk.stuba.fiit.perconik.debug.plugin;

import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.debug.DebugServices;
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

	/**
	 * The constructor.
	 */
	public Activator()
	{
		this.console.print("Constructing %s ... done", this.getClass().getCanonicalName());
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
			Debug.print("Constructing %s ... done", this.getClass());
		}
	}

	public static final class Startup extends Hook implements IStartup
	{
		public final void earlyStartup()
		{
			Debug.print("Executing early startup %s", this.getClass());
			
			Debug.put("Wrapping resources into debug objects ... ");
			DebugResources.wrapAll();
			Debug.print("done");

			Debug.put("Wrapping core services into debug objects ... ");
			DebugServices.wrapAll();
			Debug.print("done");
			
			DebugResources.printRegistrations();
			
			DebugListeners.registerAll();
			DebugListeners.printRegistrations();
		}
	}
	
	public static final class Shutdown extends Hook 
	{
		public final void earlyShutdown()
		{
			Debug.print("Executing early shutdown %s", this.getClass());
			
			DebugListeners.unregisterAll();
			DebugListeners.printRegistrations();
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
