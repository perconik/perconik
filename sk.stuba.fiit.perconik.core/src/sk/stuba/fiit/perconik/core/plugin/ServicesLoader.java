package sk.stuba.fiit.perconik.core.plugin;

import java.util.List;

import sk.stuba.fiit.perconik.core.services.ServiceSnapshot;

import static java.util.Arrays.asList;

final class ServicesLoader {
  private final ResourceExtentionProcessor resources;

  private final ListenerExtentionProcessor listeners;

  ServicesLoader() {
    this.resources = new ResourceExtentionProcessor();
    this.listeners = new ListenerExtentionProcessor();
  }

  private static void startServices() {
    ServiceSnapshot.take().servicesInStartOrder().startSynchronously();
  }

  private static void stopServices() {
    ServiceSnapshot.take().servicesInStopOrder().stopSynchronously();
  }

  List<ServiceSetup<?>> load(final Runnable hook) {
    ResourceServiceSetup resourceSetup = this.resources.process();
    ListenerServiceSetup listenerSetup = this.listeners.process();

    resourceSetup.setService();
    listenerSetup.setService();

    hook.run();

    startServices();

    resourceSetup.registerObjects();
    listenerSetup.registerObjects();

    return asList(resourceSetup, listenerSetup);
  }

  List<ServiceSetup<?>> unload() {
    ResourceServiceSetup resources = this.resources.process();
    ListenerServiceSetup listeners = this.listeners.process();

    stopServices();

    listeners.unsetService();
    resources.unsetService();

    return asList(resources, listeners);
  }
}
