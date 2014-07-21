package sk.stuba.fiit.perconik.preferences.plugin;

import org.eclipse.core.runtime.Plugin;

import org.osgi.framework.BundleContext;

/**
 * The <code>Activator</code> class controls the plug-in life cycle.
 *
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Activator extends Plugin
{
	/**
	 * The plug-in identifier.
	 */
	public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.preferences";

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
