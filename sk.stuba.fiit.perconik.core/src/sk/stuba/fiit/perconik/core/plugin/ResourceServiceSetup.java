package sk.stuba.fiit.perconik.core.plugin;

import sk.stuba.fiit.perconik.core.Resources;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.resources.ResourceNamesSupplier;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;

import static java.util.Objects.requireNonNull;

final class ResourceServiceSetup extends ServiceSetup<ResourceService> {
  final ResourceNamesSupplier supplier;

  ResourceServiceSetup(final ResourceService service, final ResourceNamesSupplier supplier) {
    super(service);

    this.supplier = requireNonNull(supplier);
  }

  @Override
  void setService() {
    Services.setResourceService(this.service);
  }

  @Override
  void unsetService() {
    Services.unsetResourceService();
  }

  @Override
  void registerObjects() {
    Resources.registerAll(this.supplier);
  }
}
