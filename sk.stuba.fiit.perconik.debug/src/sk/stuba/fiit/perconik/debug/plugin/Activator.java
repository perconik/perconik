package sk.stuba.fiit.perconik.debug.plugin;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;
import sk.stuba.fiit.perconik.debug.Debug;

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
	public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.debug";

	/**
	 * The shared instance.
	 */
	private static Activator plugin;
	
	/**
	 * The plug-in console. 
	 */
	private final PluginConsole console;

	/**
	 * The constructor
	 */
	public Activator()
	{
		this.console = PluginConsole.of(this);
		
		this.console.print("Constructing %s ... done", this.getClass().getCanonicalName());
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
			Debug.put("Constructing %s ...", this.getClass().getCanonicalName());
// TODO rm
//			String name = Defaults.settingsPath().getFileName().toString();
//			
//			this.settings = ClassPathResourceLoader.getDefault().loadSettings(name);
			
			Debug.print("done");
		}
		
		public final void earlyStartup()
		{
			Debug.put("Early startup %s ...", this.getClass().getCanonicalName());
			
			System.out.println("Hello world from earlyStartup");
// TODO rm
//			try
//			{
//				for (String name: this.settings.getAsArray("loggers"))
//				{
//					Loggers.register((Logger) Class.forName(name).newInstance());
//				}
//			}
//			catch (Exception e)
//			{
//				throw new IllegalStateException(e);
//			}
			
			Debug.print("done");
		}
	}

	@Override
	public final void start(final BundleContext context) throws Exception
	{
		this.console.put("Starting %s ...", PLUGIN_ID);
		
		super.start(context);

		plugin = this;
		
		this.console.print("done");
	}

	@Override
	public final void stop(final BundleContext context) throws Exception
	{
		this.console.put("Stopping %s ...", PLUGIN_ID);
		
		plugin = null;

		super.stop(context);
		
		this.console.print("done");
	}
}
