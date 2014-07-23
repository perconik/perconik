package sk.stuba.fiit.perconik.core.plugin;

import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;

import static com.google.common.base.Preconditions.checkNotNull;

final class ResolvedListeners extends ResolvedService<ListenerService> {
  final ListenerClassesSupplier supplier;

  ResolvedListeners(final ListenerService service, final ListenerClassesSupplier supplier) {
    super(service);

    this.supplier = checkNotNull(supplier);
  }
}
