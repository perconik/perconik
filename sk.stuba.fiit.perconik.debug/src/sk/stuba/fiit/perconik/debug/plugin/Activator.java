package sk.stuba.fiit.perconik.debug.plugin;

import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugListeners;
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

	public static final class Startup implements IStartup
	{
		public Startup() throws Exception
		{
			Debug.print("Constructing %s ... done", this.getClass().getCanonicalName());
		}
		
		public final void earlyStartup()
		{
			Debug.print("Executing early startup %s ... done", this.getClass().getCanonicalName());
		}
	}

	@Override
	public final void start(final BundleContext context) throws Exception
	{
		this.console.put("Starting %s ... ", PLUGIN_ID);
		
		super.start(context);

		plugin = this;
		
		this.console.print("done");
	
		DebugListeners.registerAll();
		DebugListeners.printRegisteredMap(this.console);
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		DebugListeners.unregisterAll();
		DebugListeners.printRegisteredMap(this.console);

		this.console.put("Stopping %s ... ", PLUGIN_ID);
		
		plugin = null;

		super.stop(context);
		
		this.console.print("done");
	}
}
