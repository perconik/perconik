package sk.stuba.fiit.perconik.debug.plugin;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.debug.DebugListeners;
import sk.stuba.fiit.perconik.debug.DebugResources;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;
import sk.stuba.fiit.perconik.environment.Environment;

/**
 * The <code>Activator</code> class controls the plug-in life cycle.
 * 
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
 * 
 * @author Pavol Zbell
 * @since 1.0
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
	
	/**
	 * The constructor.
	 */
	public Activator()
	{
	}

	/**
	 * Gets the shared instance.
	 * @return the shared instance
	 */
	public static final Activator getDefault()
	{
		return plugin;
	}
	
	/**
	 * Plug-in early startup.
	 * 
	 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
	 * 
	 * @author Pavol Zbell
	 * @since 1.0
	 */
	public static final class Startup implements IStartup
	{
		/**
		 * The constructor.
		 */
		public Startup()
		{
		}

		/**
		 * Waits until core processes all extensions and
		 * then prints registration maps on the debug console.
		 */
		public final void earlyStartup()
		{
			final Runnable wait = new Runnable()
			{
				public final void run()
				{
					sk.stuba.fiit.perconik.core.plugin.Activator.waitForExtensions();
					
					DebugResources.printRegistrations();
					DebugListeners.printRegistrations();
				}
			};
			
			Display.getDefault().asyncExec(wait);
		}
	}
	
	@Override
	public final void start(final BundleContext context) throws Exception
	{
		if (Environment.debug) this.console.put("Starting %s ... ", PLUGIN_ID);
		
		super.start(context);

		plugin = this;
		
		if (Environment.debug) this.console.print("done");
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		if (Environment.debug) this.console.put("Stopping %s ... ", PLUGIN_ID);
		
		plugin = null;

		super.stop(context);
		
		if (Environment.debug) this.console.print("done");
	}
}
