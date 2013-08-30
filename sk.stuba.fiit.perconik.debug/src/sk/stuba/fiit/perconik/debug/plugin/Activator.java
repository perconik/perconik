package sk.stuba.fiit.perconik.debug.plugin;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;
import sk.stuba.fiit.perconik.environment.Environment;

/**
 * The <code>Activator</code> class controls the plug-in life cycle.
 * 
 * @author Pavol Zbell
 */
public final class Activator extends ExtendedPlugin
{
	/**
	 * The plug-in identifier.
	 */
	public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.debug";

	/**
	 * The shared instance.
	 */
	private static Activator plugin;

	private DebugLoader loader;
	
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
	
	private static final void ensure() throws Exception
	{
		Platform.getBundle(sk.stuba.fiit.perconik.core.plugin.Activator.PLUGIN_ID).start();
	}
	
	@Override
	public final void start(final BundleContext context) throws Exception
	{
		// TODO
		//ensure();
		
		this.console.put("Starting %s ... ", PLUGIN_ID);
		
		super.start(context);

		plugin = this;
		
		this.console.print("done");
		
		// TODO
//		if (Environment.debug)
//		{
//			this.loader = DebugLoader.create();
//			
//			this.loader.load();
//		}
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		// TODO
//		if (Environment.debug)
//		{
//			this.loader.unload();
//		}
		
		this.console.put("Stopping %s ... ", PLUGIN_ID);
		
		plugin = null;

		super.stop(context);
		
		this.console.print("done");
	}
}
