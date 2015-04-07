package sk.stuba.fiit.perconik.core.plugin;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import sk.stuba.fiit.perconik.core.services.ServiceSnapshot;

import static java.util.Arrays.asList;

import static com.google.common.base.Preconditions.checkNotNull;

final class ServicesLoader {
  private final ResourceExtentionProcessor resources;

  private final ListenerExtentionProcessor listeners;

  ServicesLoader() {
    this.resources = new ResourceExtentionProcessor();
    this.listeners = new ListenerExtentionProcessor();
  }

  private static void startServices(final long timeout, final TimeUnit unit) throws TimeoutException {
    ServiceSnapshot.take().servicesInStartOrder().startSynchronously(timeout, unit);
  }

  private static void stopServices(final long timeout, final TimeUnit unit) throws TimeoutException {
    ServiceSnapshot.take().servicesInStopOrder().stopSynchronously(timeout, unit);
  }

  List<ServiceSetup<?>> load(final Runnable hook, final long timeout, final TimeUnit unit) throws TimeoutException {
    checkNotNull(hook);
    checkNotNull(unit);

    ResourceServiceSetup resourceSetup = this.resources.process();
    ListenerServiceSetup listenerSetup = this.listeners.process();

    resourceSetup.setService();
    listenerSetup.setService();

    hook.run();

    startServices(timeout, unit);

    resourceSetup.registerObjects();
    listenerSetup.registerObjects();

    return asList(resourceSetup, listenerSetup);
  }

  List<ServiceSetup<?>> unload(final Runnable hook, final long timeout, final TimeUnit unit) throws TimeoutException {
    checkNotNull(hook);
    checkNotNull(unit);

    ResourceServiceSetup resources = this.resources.process();
    ListenerServiceSetup listeners = this.listeners.process();

    stopServices(timeout, unit);

    hook.run();

    listeners.unsetService();
    resources.unsetService();

    return asList(resources, listeners);
  }
}
