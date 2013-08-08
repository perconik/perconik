package sk.stuba.fiit.perconik.debug;

import java.util.Collections;
import java.util.List;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;
import sk.stuba.fiit.perconik.utilities.Strings;
import com.google.common.collect.Lists;

public final class DebugResources
{
	private DebugResources()
	{
		throw new AssertionError();
	}
	
	public static final String toString(final Resource<?> resource)
	{
		return Strings.toStringFallback(resource);
	}
	
	public static final void printRegistered(final PluginConsole console)
	{
		console.put(dumpRegisteredMap());
	}	

	static final String dumpRegisteredMap()
	{
		SmartStringBuilder builder = new SmartStringBuilder();
		
		builder.appendln("Registered resources:").tab();
		
		List<Resource<?>> resources = Lists.newArrayList(Resources.registered());

		if (!resources.isEmpty())
		{
			Collections.sort(resources, Strings.toStringComparator());
			
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
