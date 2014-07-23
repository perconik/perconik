package sk.stuba.fiit.perconik.core.plugin;

import sk.stuba.fiit.perconik.core.services.resources.ResourceNamesSupplier;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;

import static com.google.common.base.Preconditions.checkNotNull;

final class ResolvedResources extends ResolvedService<ResourceService> {
  final ResourceNamesSupplier supplier;

  ResolvedResources(final ResourceService service, final ResourceNamesSupplier supplier) {
    super(service);

    this.supplier = checkNotNull(supplier);
  }
}
