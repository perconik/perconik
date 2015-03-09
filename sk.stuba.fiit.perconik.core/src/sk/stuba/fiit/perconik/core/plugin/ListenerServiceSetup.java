package sk.stuba.fiit.perconik.core.plugin;

import sk.stuba.fiit.perconik.core.Listeners;
import sk.stuba.fiit.perconik.core.services.Services;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerClassesSupplier;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;

import static java.util.Objects.requireNonNull;

final class ListenerServiceSetup extends ServiceSetup<ListenerService> {
  final ListenerClassesSupplier supplier;

  ListenerServiceSetup(final ListenerService service, final ListenerClassesSupplier supplier) {
    super(service);

    this.supplier = requireNonNull(supplier);
  }

  @Override
  void setService() {
    Services.setListenerService(this.service);
  }

  @Override
  void unsetService() {
    Services.unsetListenerService();
  }

  @Override
  void registerObjects() {
    Listeners.registerAll(this.supplier);
  }
}
