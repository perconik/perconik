package sk.stuba.fiit.perconik.osgi.framework;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Iterables;

import org.eclipse.core.runtime.Platform;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import sk.stuba.fiit.perconik.utilities.MoreMaps;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

import static java.util.Arrays.asList;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayListWithCapacity;

/**
 * Static utility methods pertaining to {@code Bundle} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Bundles {
  private Bundles() {}

  public static Bundle forClass(final Class<?> type) {
    return FrameworkUtil.getBundle(type);
  }

  public static Bundle forName(final String name) throws BundleNotFoundException {
    checkArgument(!name.isEmpty());

    Bundle bundle = Platform.getBundle(name);

    if (bundle != null) {
      return bundle;
    }

    throw new BundleNotFoundException(name + " not found");
  }

  public static List<Bundle> forNames(final String ... names) throws BundleNotFoundException {
    return forNames(asList(names));
  }

  public static List<Bundle> forNames(final Iterable<String> names) throws BundleNotFoundException {
    List<Bundle> bundles = newArrayListWithCapacity(Iterables.size(names));

    for (String name: names) {
      bundles.add(forName(name));
    }

    return bundles;
  }

  public static ClassResolver newClassResolver(final Bundle bundle) {
    return new BundleClassResolver(bundle);
  }

  public static List<ClassResolver> newClassResolvers(final Bundle ... bundles) {
    return newClassResolvers(asList(bundles));
  }

  public static List<ClassResolver> newClassResolvers(final Iterable<Bundle> bundles) {
    List<ClassResolver> resolvers = newArrayListWithCapacity(Iterables.size(bundles));

    for (Bundle bundle: bundles) {
      resolvers.add(newClassResolver(bundle));
    }

    return resolvers;
  }

  public static Map<String, String> getHeaders(final Bundle bundle) {
    return MoreMaps.fromDictionary(bundle.getHeaders());
  }

  public static String getName(final Bundle bundle) {
    return bundle.getHeaders().get("Bundle-Name");
  }
}
