package sk.stuba.fiit.perconik.core.plugin;

import java.util.List;

import sk.stuba.fiit.perconik.core.listeners.DefaultListeners;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManager;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerManagerFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProvider;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviderFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerProviders;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerServiceFactory;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerServices;

final class ListenerExtentionProcessor extends AbstractExtensionProcessor<ListenerServiceSetup> {
  ListenerExtentionProcessor() {
    super(ExtensionPoints.listenersPoint, ExtensionPoints.listenersTypes);
  }

  @Override
  ListenerServiceSetup processExtensions() {
    ListenerService service;

    boolean serviceSupplied = this.atLeastOneSupplied(ListenerServiceFactory.class);
    boolean componentsSupplied = this.atLeastOneSupplied(ListenerProviderFactory.class, ListenerManagerFactory.class);

    if (serviceSupplied) {
      if (componentsSupplied) {
        this.console().warning("Custom %s supplied, custom supplied components ignored", "listener service");
      }

      service = this.resolveListenerServiceFactories(this.getExtensions(ListenerServiceFactory.class));
    } else if (componentsSupplied) {
      ListenerService.Builder builder = ListenerServices.builder();

      builder.provider(this.resolveListenerProviderFactories(this.getExtensions(ListenerProviderFactory.class)));
      builder.manager(this.resolveListenerManagerFactories(this.getExtensions(ListenerManagerFactory.class)));

      service = builder.build();
    } else {
      service = DefaultListeners.createListenerService();
    }

    ListenerClassesSupplier supplier = this.resolveListenerClassesSuppliers(this.getExtensions(ListenerClassesSupplier.class));

    return new ListenerServiceSetup(service, supplier);
  }

  private ListenerService resolveListenerServiceFactories(final List<ListenerServiceFactory> factories) {
    if (this.emptyOrNotSingletonWithWarning(factories, "listener service")) {
      return DefaultListeners.createListenerService();
    }

    return this.createListenerService(factories.get(0));
  }

  private ListenerProvider resolveListenerProviderFactories(final List<ListenerProviderFactory> factories) {
    ListenerProvider provider = DefaultListeners.createListenerProvider();

    for (ListenerProviderFactory factory: factories) {
      provider = this.createListenerProvider(factory, provider);
    }

    return provider;
  }

  private ListenerManager resolveListenerManagerFactories(final List<ListenerManagerFactory> factories) {
    if (this.emptyOrNotSingletonWithWarning(factories, "listener manager")) {
      return DefaultListeners.createListenerManager();
    }

    return this.createListenerManager(factories.get(0));
  }

  private ListenerClassesSupplier resolveListenerClassesSuppliers(final List<ListenerClassesSupplier> suppliers) {
    if (this.emptyWithNotice(suppliers, "registered listeners")) {
      return DefaultListeners.createListenerClassesSupplier();
    }

    return ListenerProviders.merge(suppliers);
  }

  private ListenerService createListenerService(final ListenerServiceFactory factory) {
    return resultOf(new SafeGet<ListenerService>(factory, ListenerService.class) {
      @Override
      ListenerService get() {
        return factory.create();
      }
    });
  }

  private ListenerProvider createListenerProvider(final ListenerProviderFactory factory, final ListenerProvider parent) {
    return resultOf(new SafeGet<ListenerProvider>(factory, ListenerProvider.class) {
      @Override
      ListenerProvider get() {
        return factory.create(parent);
      }
    });
  }

  private ListenerManager createListenerManager(final ListenerManagerFactory factory) {
    return resultOf(new SafeGet<ListenerManager>(factory, ListenerManager.class) {
      @Override
      ListenerManager get() {
        return factory.create();
      }
    });
  }
}
