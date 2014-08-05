package com.gratex.perconik.services.plugin;

import org.osgi.framework.BundleContext;

import sk.stuba.fiit.perconik.eclipse.core.runtime.ExtendedPlugin;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * <p><b>Warning:</b> Users should not explicitly instantiate this class.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class Activator extends ExtendedPlugin {
  /**
   * The plug-in identifier.
   */
  public static final String PLUGIN_ID = "com.gratex.perconik.services";

  /**
   * The shared instance.
   */
  private static Activator plugin;

  /**
   * The constructor.
   */
  public Activator() {}

  /**
   * Gets the shared instance.
   * @return the shared instance
   */
  public static final Activator getDefault() {
    return plugin;
  }

  @Override
  public final void start(final BundleContext context) throws Exception {
    super.start(context);

    plugin = this;
  }

  @Override
  public final void stop(final BundleContext context) throws Exception {
    plugin = null;

    super.stop(context);
  }
}
