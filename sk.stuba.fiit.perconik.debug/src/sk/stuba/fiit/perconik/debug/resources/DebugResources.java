package sk.stuba.fiit.perconik.debug.resources;

import java.util.Set;
import sk.stuba.fiit.perconik.core.resources.Resource;
import sk.stuba.fiit.perconik.core.resources.Resources;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.Strings;

public final class DebugResources
{
	private DebugResources()
	{
		throw new AssertionError();
	}
	
	public static final String toString(final Resource<?> resource)
	{
		return Strings.toCanonicalString(resource);
	}
	
	public static final void printRegistered(final PluginConsole console)
	{
		console.put(dumpRegisteredMap());
	}	

	static final String dumpRegisteredMap()
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.appendln("Registered resources:").tab();
		
		Set<Resource<?>> resources = Resources.getResources();

		if (!resources.isEmpty())
		{
			for (Resource<?> resource: resources)
			{
				builder.appendln(toString(resource));
			}
		}
		else
		{
			builder.appendln("none");
		}
		
		return builder.toString();
	}
}
