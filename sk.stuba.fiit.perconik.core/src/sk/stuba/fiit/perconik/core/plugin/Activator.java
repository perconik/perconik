package sk.stuba.fiit.perconik.core.plugin;

import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.services.ServiceSnapshot;
import sk.stuba.fiit.perconik.core.services.Services;
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

	volatile boolean processed;
	
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
	
	public static final void waitForExtensions()
	{
		synchronized (plugin)
		{
			while (plugin == null || !plugin.processed) {}
		}
	}
	
	public static final class Startup implements IStartup
	{
		public Startup()
		{
		}
		
		public final void earlyStartup()
		{
			ResourceExtentionProcessor processor0 = new ResourceExtentionProcessor();
			ListenerExtentionProcessor processor = new ListenerExtentionProcessor();
			
			ResolvedResources data0 = processor0.process();
			ResolvedListeners data = processor.process();
			
			Services.setResourceService(data0.service);
			Services.setListenerService(data.service);
			
			ServiceSnapshot.take().servicesInStartOrder().startAndWait();
			
			Resources.registerAll(data0.supplier);
			Listeners.registerAll(data.supplier);
			
			getDefault().processed = true;
		}
	}

	@Override
	public final void start(final BundleContext context) throws Exception
	{
		this.processed = false;
		
		super.start(context);

		plugin = this;
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		ServiceSnapshot.take().servicesInStopOrder().stopAndWait();
		
		plugin = null;

		super.stop(context);
		
		this.processed = false;
	}
}
