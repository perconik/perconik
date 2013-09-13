package sk.stuba.fiit.perconik.osgi.framework;

import java.util.Arrays;
import java.util.List;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import sk.stuba.fiit.perconik.utilities.reflection.ClassResolver;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Static utility methods pertaining to {@code Bundle} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Bundles
{
	private Bundles()
	{
		throw new AssertionError();
	}
	
	public static final Bundle forName(final String name) throws BundleNotFoundException
	{
		Preconditions.checkArgument(!name.isEmpty());
		
		Bundle bundle = Platform.getBundle(name);

		if (bundle != null)
		{
			return bundle;
		}
		
		throw new BundleNotFoundException(name + " not found");
	}
	
	public static final List<Bundle> forNames(final String ... names) throws BundleNotFoundException
	{
		return forNames(Arrays.asList(names));
	}
	
	public static final List<Bundle> forNames(final Iterable<String> names) throws BundleNotFoundException
	{
		List<Bundle> bundles = Lists.newArrayListWithCapacity(Iterables.size(names));
		
		for (String name: names)
		{
			bundles.add(forName(name));
		}
		
		return bundles;
	}
	
	public static final ClassResolver newClassResolver(Bundle bundle)
	{
		return new BundleClassResolver(bundle);
	}
	
	public static final List<ClassResolver> newClassResolvers(Bundle ... bundles)
	{
		return newClassResolvers(Arrays.asList(bundles));
	}
	
	public static final List<ClassResolver> newClassResolvers(Iterable<Bundle> bundles)
	{
		List<ClassResolver> resolvers = Lists.newArrayListWithCapacity(Iterables.size(bundles));
		
		for (Bundle bundle: bundles)
		{
			resolvers.add(newClassResolver(bundle));
		}
		
		return resolvers;
	}
}
