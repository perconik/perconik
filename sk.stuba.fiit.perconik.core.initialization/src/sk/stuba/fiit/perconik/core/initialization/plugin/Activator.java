package sk.stuba.fiit.perconik.core.initialization.plugin;

import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.core.services.Services;
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
	public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.core.initialization";

	/**
	 * The shared instance.
	 */
	private static Activator plugin;

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

	public static final class Startup implements IStartup
	{
		public final void earlyStartup()
		{
			Display.getDefault().syncExec(Services.getResourceService().getResourceInitializer());
			Display.getDefault().syncExec(Services.getListenerService().getListenerInitializer());
		}
	}
	
	private static final void ensure() throws Exception
	{
		Platform.getBundle(sk.stuba.fiit.perconik.core.plugin.Activator.PLUGIN_ID).start();
		Platform.getBundle(sk.stuba.fiit.perconik.preferences.plugin.Activator.PLUGIN_ID).start();
		
		if (Environment.debug)
		{
			Platform.getBundle(sk.stuba.fiit.perconik.debug.plugin.Activator.PLUGIN_ID).start();
		}
	}

	@Override
	public final void start(final BundleContext context) throws Exception
	{
		ensure();
		
		super.start(context);

		plugin = this;
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		plugin = null;

		super.stop(context);
	}
}
