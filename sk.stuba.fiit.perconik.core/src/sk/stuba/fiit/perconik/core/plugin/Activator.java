package sk.stuba.fiit.perconik.core.plugin;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author Pavol Zbell
 * @version 0.0.1
 */
public class Activator extends AbstractUIPlugin
{

	/**
	 * The plug-in identifier.
	 */
	public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.core";

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
	public static Activator getDefault()
	{
		return plugin;
	}

	public static final class Startup implements IStartup
	{
		public Startup() throws Exception
		{
			// TODO
		}
		
		public final void earlyStartup()
		{
			// TODO
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
		plugin = null;

		super.stop(context);
	}
}
