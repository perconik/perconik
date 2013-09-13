package sk.stuba.fiit.perconik.core.persistence.data;

import java.util.List;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import sk.stuba.fiit.perconik.core.plugin.Activator;
import sk.stuba.fiit.perconik.utilities.MoreThrowables;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

final class ClassResolver
{
	private static final List<Bundle> bundles; 
	
	static
	{
		ImmutableList.Builder<Bundle> builder = ImmutableList.builder();
		
		for (String name: Activator.extensionContributors())
		{
			Bundle bundle = Platform.getBundle(name);
			
			if (bundle != null)
			{
				builder.add(bundle);
			}
		}
		
		bundles = builder.build();
	}
	
	private ClassResolver()
	{
		throw new AssertionError();
	}
	
	static final Class<?> forName(final String name) throws ClassNotFoundException
	{
		Preconditions.checkArgument(!name.isEmpty());
		
		try
		{
			return Class.forName(name);
		}
		catch (ClassNotFoundException e)
		{
			return resolve(name, e);
		}
	}
	
	private static final Class<?> resolve(final String name, final ClassNotFoundException cause) throws ClassNotFoundException
	{
		List<Throwable> suppressions = Lists.newLinkedList();
		
		suppressions.add(cause);
		
		for (Bundle bundle: bundles)
		{
			try
			{
				return bundle.loadClass(name);
			}
			catch (Exception e)
			{
				suppressions.add(e);
			}
		}
		
		ClassNotFoundException failure = new ClassNotFoundException(name + " not found");
		
		throw MoreThrowables.initializeSuppressor(failure, Lists.reverse(suppressions));
	}
}
