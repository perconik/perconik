package sk.stuba.fiit.perconik.activity.data.core;

import sk.stuba.fiit.perconik.activity.data.eclipse.BundleData;
import sk.stuba.fiit.perconik.core.plugin.Activator;
import sk.stuba.fiit.perconik.core.services.Services;

public class StandardCoreProbe implements CoreProbe {
  public StandardCoreProbe() {}

  public CoreData core() {
    CoreData data = new CoreData(Activator.defaultInstance());

    data.setClassResolver(Activator.classResolver().toString());
    data.setExtensionContributors(Activator.extensionContributors());
    data.setContributingBundles(BundleData.of(Activator.contributingBundles()));

    data.setResourceService(ResourceServiceData.of(Services.getResourceService()));
    data.setListenerService(ListenerServiceData.of(Services.getListenerService()));

    return data;
  }
}
