package sk.stuba.fiit.perconik.core.utilities;

import sk.stuba.fiit.perconik.core.plugin.Activator;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public final class LogHelper
{
	public static final PluginConsole log = Activator.getDefault().getConsole(); 
	
	private LogHelper()
	{
		throw new AssertionError();
	}
}
