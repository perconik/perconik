package sk.stuba.fiit.perconik.activity.data.eclipse;

import org.eclipse.core.runtime.Plugin;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class PluginData extends AnyStructuredData {
  protected BundleData bundle;

  protected boolean debugMode;

  public PluginData() {}

  protected PluginData(final Plugin plugin) {
    if (plugin == null) {
      return;
    }

    this.setBundle(BundleData.of(plugin.getBundle()));
    this.setDebugMode(plugin.isDebugging());
  }

  public static PluginData of(final Plugin plugin) {
    return new PluginData(plugin);
  }

  public void setBundle(final BundleData bundle) {
    this.bundle = bundle;
  }

  public void setDebugMode(final boolean debugMode) {
    this.debugMode = debugMode;
  }

  public BundleData getBundle() {
    return this.bundle;
  }

  public boolean isDebugMode() {
    return this.debugMode;
  }
}
