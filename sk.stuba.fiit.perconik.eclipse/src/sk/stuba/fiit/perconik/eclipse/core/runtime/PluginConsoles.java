package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.Plugin;

public final class PluginConsoles
{
	private PluginConsoles()
	{
		throw new AssertionError();
	}
	
	public static final PluginConsole create(final Plugin plugin)
	{
		return new DefaultPluginConsole(plugin, System.out);
	}
}
