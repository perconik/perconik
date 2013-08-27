package sk.stuba.fiit.perconik.preferences.plugin;

import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.eclipse.ui.IShutdown;
import sk.stuba.fiit.perconik.eclipse.ui.plugin.UserInterfacePlugin;

/**
 * The <code>Activator</code> class controls the plug-in life cycle.
 * 
 * @author Pavol Zbell
 */
public final class Activator extends UserInterfacePlugin
{
	/**
	 * The plug-in identifier.
	 */
	public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.preferences";

	/**
	 * The shared instance.
	 */
	private static Activator plugin;

	final PreferencesLoader loader;
	
	/**
	 * The constructor
	 */
	public Activator()
	{
		this.loader = PreferencesLoader.create();
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
		public final void earlyStartup()
		{
			getDefault().loader.load();
		}
	}

	public static final class Shutdown implements IShutdown
	{
		public final void earlyShutdown()
		{
			getDefault().loader.unload();
		}
	}
	
	@Override
	public final void start(final BundleContext context) throws Exception
	{
		super.start(context);

		plugin = this;
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		new Shutdown().earlyShutdown();
		
		plugin = null;

		super.stop(context);
	}
}
