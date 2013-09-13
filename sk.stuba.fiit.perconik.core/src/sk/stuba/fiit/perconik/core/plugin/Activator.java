package sk.stuba.fiit.perconik.core.plugin;

import java.util.Set;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IStartup;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.core.services.ServiceSnapshot;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;
import com.google.common.collect.Sets;

/**
 * The <code>Activator</code> class controls the plug-in life cycle.
 * 
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
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
	private static volatile Activator plugin;

	volatile boolean processed;
	
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
		synchronized (Activator.class)
		{
			return plugin;
		}
	}

	public static final Set<String> extensionContributors()
	{
		Set<String> contributors = Sets.newHashSet();
		
		for (String point: ExtensionPoints.all)
		{
			for (IConfigurationElement element: Platform.getExtensionRegistry().getConfigurationElementsFor(point))
			{
				contributors.add(element.getContributor().getName());
			}
		}
		
		return contributors;
	}

	/**
	 * Waits blocking until all supplied extensions are processed.
	 * @throws NullPointerException if the shared instance is not constructed
	 */
	public static final void waitForExtensions()
	{
		Activator plugin;
		
		do
		{
			plugin = getDefault();
		}
		while (plugin == null || !plugin.processed);
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
		 * Processes supplied extensions and starts core services.
		 */
		public final void earlyStartup()
		{
			ServicesLoader loader = new ServicesLoader();
			
			loader.load();
			
			getDefault().processed = true;
		}
	}

	@Override
	public final void start(final BundleContext context) throws Exception
	{
		this.processed = false;
		
		super.start(context);

		synchronized (Activator.class)
		{
			plugin = this;
		}
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		ServiceSnapshot.take().servicesInStopOrder().stopAndWait();
		
		synchronized (Activator.class)
		{
			plugin = null;
		}

		super.stop(context);
		
		this.processed = false;
	}
}
