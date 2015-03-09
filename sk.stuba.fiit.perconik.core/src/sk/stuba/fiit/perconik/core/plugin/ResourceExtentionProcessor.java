package sk.stuba.fiit.perconik.core.plugin;

import java.util.List;

import sk.stuba.fiit.perconik.core.resources.DefaultResources;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManager;
import sk.stuba.fiit.perconik.core.services.resources.ResourceManagerFactory;
import sk.stuba.fiit.perconik.core.services.resources.ResourceNamesSupplier;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProvider;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProviderFactory;
import sk.stuba.fiit.perconik.core.services.resources.ResourceProviders;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;
import sk.stuba.fiit.perconik.core.services.resources.ResourceServiceFactory;
import sk.stuba.fiit.perconik.core.services.resources.ResourceServices;

final class ResourceExtentionProcessor extends AbstractExtensionProcessor<ResourceServiceSetup> {
  ResourceExtentionProcessor() {
    super(ExtensionPoints.resourcesPoint, ExtensionPoints.resourcesTypes);
  }

  @Override
  ResourceServiceSetup processExtensions() {
    ResourceService service;

    boolean serviceSupplied = this.atLeastOneSupplied(ResourceServiceFactory.class);
    boolean componentsSupplied = this.atLeastOneSupplied(ResourceProviderFactory.class, ResourceManagerFactory.class);

    if (serviceSupplied) {
      if (componentsSupplied) {
        this.console().warning("Custom %s supplied, custom supplied components ignored", "resource service");
      }

      service = this.resolveResourceServiceFactories(this.getExtensions(ResourceServiceFactory.class));
    } else if (componentsSupplied) {
      ResourceService.Builder builder = ResourceServices.builder();

      builder.provider(this.resolveResourceProviderFactories(this.getExtensions(ResourceProviderFactory.class)));
      builder.manager(this.resolveResourceManagerFactories(this.getExtensions(ResourceManagerFactory.class)));

      service = builder.build();
    } else {
      service = DefaultResources.getDefaultResourceService();
    }

    ResourceNamesSupplier supplier = this.resolveResourceNamesSuppliers(this.getExtensions(ResourceNamesSupplier.class));

    return new ResourceServiceSetup(service, supplier);
  }

  private ResourceService resolveResourceServiceFactories(final List<ResourceServiceFactory> factories) {
    if (this.emptyOrNotSingletonWithWarning(factories, "resource service")) {
      return DefaultResources.getDefaultResourceService();
    }

    return this.createResourceService(factories.get(0));
  }

  private ResourceProvider resolveResourceProviderFactories(final List<ResourceProviderFactory> factories) {
    ResourceProvider provider = DefaultResources.getDefaultResourceProvider();

    for (ResourceProviderFactory factory: factories) {
      provider = this.createResourceProvider(factory, provider);
    }

    return provider;
  }

  private ResourceManager resolveResourceManagerFactories(final List<ResourceManagerFactory> factories) {
    if (this.emptyOrNotSingletonWithWarning(factories, "resource manager")) {
      return DefaultResources.getDefaultResourceManager();
    }

    return this.createResourceManager(factories.get(0));
  }

  private ResourceNamesSupplier resolveResourceNamesSuppliers(final List<ResourceNamesSupplier> suppliers) {
    if (this.emptyWithNotice(suppliers, "registered resources")) {
      return DefaultResources.getDefaultResourceNamesSupplier();
    }

    return ResourceProviders.merge(suppliers);
  }

  private ResourceService createResourceService(final ResourceServiceFactory factory) {
    return resultOf(new SafeGet<ResourceService>(factory, ResourceService.class) {
      @Override
      ResourceService get() {
        return factory.create();
      }
    });
  }

  private ResourceProvider createResourceProvider(final ResourceProviderFactory factory, final ResourceProvider parent) {
    return resultOf(new SafeGet<ResourceProvider>(factory, ResourceProvider.class) {
      @Override
      ResourceProvider get() {
        return factory.create(parent);
      }
    });
  }

  private ResourceManager createResourceManager(final ResourceManagerFactory factory) {
    return resultOf(new SafeGet<ResourceManager>(factory, ResourceManager.class) {
      @Override
      ResourceManager get() {
        return factory.create();
      }
    });
  }
}
