package sk.stuba.fiit.perconik.activity.data.core;

import java.util.List;
import java.util.Set;

import sk.stuba.fiit.perconik.activity.data.ObjectData;
import sk.stuba.fiit.perconik.activity.data.platform.BundleData;
import sk.stuba.fiit.perconik.activity.data.platform.PluginData;
import sk.stuba.fiit.perconik.core.plugin.Activator;

public class CoreData extends PluginData {
  protected ObjectData classResolver;

  protected Set<String> extensionContributors;

  protected List<BundleData> contributingBundles;

  protected ResourceServiceData resourceService;

  protected ListenerServiceData listenerService;

  public CoreData() {}

  protected CoreData(final Activator activator) {
    super(activator);
  }

  public void setClassResolver(final ObjectData classResolver) {
    this.classResolver = classResolver;
  }

  public void setExtensionContributors(final Set<String> extensionContributors) {
    this.extensionContributors = extensionContributors;
  }

  public void setContributingBundles(final List<BundleData> contributingBundles) {
    this.contributingBundles = contributingBundles;
  }

  public void setResourceService(final ResourceServiceData resourceService) {
    this.resourceService = resourceService;
  }

  public void setListenerService(final ListenerServiceData listenerService) {
    this.listenerService = listenerService;
  }

  public ObjectData getClassResolver() {
    return this.classResolver;
  }

  public Set<String> getExtensionContributors() {
    return this.extensionContributors;
  }

  public List<BundleData> getContributingBundles() {
    return this.contributingBundles;
  }

  public ResourceServiceData getResourceService() {
    return this.resourceService;
  }

  public ListenerServiceData getListenerService() {
    return this.listenerService;
  }
}
