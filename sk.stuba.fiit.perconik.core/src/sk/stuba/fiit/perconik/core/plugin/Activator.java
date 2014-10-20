package sk.stuba.fiit.perconik.core.plugin;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IStartup;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import sk.stuba.fiit.perconik.core.services.ServiceSnapshot;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.osgi.framework.BundleNotFoundException;
import sk.stuba.fiit.perconik.osgi.framework.Bundles;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolvers;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

/**
 * The <code>Activator</code> class controls the plug-in life cycle.
 *
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
 *
 * @author Pavol Zbell
 */
public final class Activator extends ExtendedPlugin {
  /**
   * The plug-in identifier.
   */
  public static final String PLUGIN_ID = "sk.stuba.fiit.perconik.core";

  /**
   * The shared instance.
   */
  private static volatile Activator plugin;

  /**
   * Indicates whether core plug-in extensions are processed or not.
   */
  volatile boolean processed;

  /**
   * The constructor.
   */
  public Activator() {}

  /**
   * Gets the shared instance.
   * @return the shared instance or {@code null}
   */
  public static Activator defaultInstance() {
    return plugin;
  }

  /**
   * Gets the shared console.
   * @return the shared console or {@code null}
   */
  public static PluginConsole defaultConsole() {
    Activator plugin = defaultInstance();

    return plugin != null ? plugin.getConsole() : null;
  }

  public static Set<String> extensionContributors() {
    Set<String> contributors = newHashSet();

    for (String point: ExtensionPoints.all) {
      for (IConfigurationElement element: Platform.getExtensionRegistry().getConfigurationElementsFor(point)) {
        contributors.add(element.getContributor().getName());
      }
    }

    return contributors;
  }

  public static List<Bundle> contributingBundles() {
    try {
      return Bundles.forNames(extensionContributors());
    } catch (BundleNotFoundException e) {
      throw propagate(e);
    }
  }

  public static ClassResolver classResolver() {
    List<ClassResolver> resolvers = newArrayList();

    resolvers.add(Bundles.newClassResolver(defaultInstance().getBundle()));
    resolvers.addAll(Bundles.newClassResolvers(contributingBundles()));

    return ClassResolvers.compose(resolvers);
  }

  /**
   * Waits blocking until all supplied extensions are processed.
   * @throws NullPointerException if the shared instance is not constructed
   */
  public static void waitForExtensions() {
    Activator plugin;

    do {
      plugin = defaultInstance();
    } while (plugin == null || !plugin.processed);
  }

  /**
   * Plug-in early startup.
   *
   * <p><b>Warning:</b> Users should not explicitly instantiate this class.
   *
   * @author Pavol Zbell
   * @since 1.0
   */
  public static final class Startup implements IStartup {
    /**
     * The constructor.
     */
    public Startup() {}

    /**
     * Processes supplied extensions and starts core services.
     */
    public void earlyStartup() {
      ServicesLoader loader = new ServicesLoader();

      loader.load();

      defaultInstance().processed = true;
    }
  }

  @Override
  public void start(final BundleContext context) throws Exception {
    this.processed = false;

    super.start(context);

    plugin = this;
  }

  @Override
  public void stop(final BundleContext context) throws Exception {
    ServiceSnapshot.take().servicesInStopOrder().stopSynchronously();

    plugin = null;

    super.stop(context);

    this.processed = false;
  }
}
