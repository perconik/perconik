package sk.stuba.fiit.perconik.core.plugin;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.core.services.ServiceSnapshot;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;

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
	public static final Activator getDefault()
	{
		return plugin;
	}

	@Override
	public final void start(final BundleContext context) throws Exception
	{
		super.start(context);

		plugin = this;
		
		ServiceSnapshot.take().servicesInStartOrder().startAndWait();
		
		// TODO
		//new Test().execute(Platform.getExtensionRegistry());
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		ServiceSnapshot.take().servicesInStopOrder().stopAndWait();
		
		plugin = null;

		super.stop(context);
	}
}
