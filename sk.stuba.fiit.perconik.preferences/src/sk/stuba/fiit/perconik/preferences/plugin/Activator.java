package sk.stuba.fiit.perconik.preferences.plugin;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleContext;
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

	private PreferencesLoader loader;
	
	/**
	 * The constructor
	 */
	public Activator()
	{
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
		ensure();
		
		super.start(context);

		plugin = this;
		
		this.loader = PreferencesLoader.create();
		
		this.loader.load();
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		this.loader.unload();
		
		plugin = null;

		super.stop(context);
	}
}
