package sk.stuba.fiit.perconik.core.plugin;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import sk.stuba.fiit.perconik.core.ListenerRegistrationException;
import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.ResourceRegistrationException;
import sk.stuba.fiit.perconik.core.listeners.WorkbenchListener;
import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;
import sk.stuba.fiit.perconik.eclipse.swt.widgets.DisplayExecutor;
import sk.stuba.fiit.perconik.osgi.framework.BundleNotFoundException;
import sk.stuba.fiit.perconik.osgi.framework.Bundles;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;
import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolvers;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.util.concurrent.Runnables.doNothing;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

import static sk.stuba.fiit.perconik.eclipse.ui.Workbenches.waitForWorkbench;

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
  private volatile boolean loaded;

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

  /**
   * Returns a set of extension contributors to this plug-in.
   */
  public static Set<String> extensionContributors() {
    Set<String> contributors = newHashSet();

    for (String point: ExtensionPoints.all) {
      for (IConfigurationElement element: Platform.getExtensionRegistry().getConfigurationElementsFor(point)) {
        contributors.add(element.getContributor().getName());
      }
    }

    return contributors;
  }

  /**
   * Returns a list of bundles contributing extensions to this plug-in.
   */
  public static List<Bundle> contributingBundles() {
    try {
      return Bundles.forNames(extensionContributors());
    } catch (BundleNotFoundException e) {
      throw propagate(e);
    }
  }

  /**
   * Returns primary {@link ClassResolver} utility to resolve unknown classes.
   */
  public static ClassResolver classResolver() {
    List<ClassResolver> resolvers = newArrayList();

    resolvers.add(Bundles.newClassResolver(defaultInstance().getBundle()));
    resolvers.addAll(Bundles.newClassResolvers(contributingBundles()));

    return ClassResolvers.compose(resolvers);
  }

  /**
   * Processes supplied extensions, loads and starts core services.
   * @throws Throwable if an error occurred
   */
  public static void loadServices() {
    loadServices(doNothing());
  }

  /**
   * Processes supplied extensions, loads and starts core services.
   * @param action executed after services load prior to start, not {@code null}
   * @throws Throwable if an error occurred
   */
  public static void loadServices(final Runnable hook) {
    Activator plugin = defaultInstance();

    checkNotNull(plugin, "Default instance not available");

    synchronized (plugin) {
      if (plugin.loaded) {
        throw new IllegalStateException("Core services already loaded");
      }

      try {
        new ServicesLoader().load(hook);

        plugin.loaded = true;
      } catch (Throwable failure) {
        propagate(failure);
      }
    }
  }

  /**
   * Processes supplied extensions, stops and unloads core services.
   * @throws Throwable if an error occurred
   */
  public static void unloadServices() {
    Activator plugin = defaultInstance();

    checkNotNull(plugin, "Default instance not available");

    synchronized (plugin) {
      if (!plugin.loaded) {
        throw new IllegalStateException("Core services not loaded yet");
      }

      try {
        new ServicesLoader().unload();

        plugin.loaded = false;
      } catch (Throwable failure) {
        propagate(failure);
      }
    }
  }

  /**
   * Determines whether all supplied extensions
   * are processed, core services loaded and started.
   */
  public static boolean loadedServices() {
    Activator plugin = defaultInstance();

    return plugin != null && plugin.loaded;
  }

  /**
   * Waits blocking until all supplied extensions
   * are processed, core services loaded and started.
   */
  public static void awaitServices() {
    while (!loadedServices()) {
      sleepUninterruptibly(20, MILLISECONDS);
    }
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
      try {
        loadServices();
      } catch (ResourceRegistrationException failure) {
        defaultConsole().error(failure, "Unexpected error during initial registration of resources");
      } catch (ListenerRegistrationException failure) {
        defaultConsole().error(failure, "Unexpected error during initial registration of listeners");
      } catch (Exception failure) {
        defaultConsole().error(failure, "Unexpected error during initial registration of resources and listeners");
      }

      try {
        dispatchPostStartup();
      } catch (Exception failure) {
        defaultConsole().error(failure, "Unexpected error during post startup event dispatch");
      }
    }

    private static void dispatchPostStartup() {
      DisplayExecutor.defaultAsynchronous().execute(new Runnable() {
        public void run() {
          IWorkbench workbench = waitForWorkbench();

          for (WorkbenchListener listener: Listeners.registered(WorkbenchListener.class)) {
            try {
              listener.postStartup(workbench);
            } catch (Exception failure) {
              defaultConsole().error(failure, "Unexpected error during post startup event dispatch on %s", listener);
            }
          }
        }
      });
    }
  }

  /**
   * Starts this plug-in.
   *
   * <p><b>Warning:</b> Users must never explicitly call this method.
   */
  @Override
  public void start(final BundleContext context) throws Exception {
    this.loaded = false;

    super.start(context);

    plugin = this;
  }

  /**
   * Stops this plug-in.
   *
   * <p><b>Warning:</b> Users must never explicitly call this method.
   */
  @Override
  public void stop(final BundleContext context) throws Exception {
    synchronized (this) {
      if (loadedServices()) {
        unloadServices();
      }
    }

    plugin = null;

    super.stop(context);

    this.loaded = false;
  }
}
