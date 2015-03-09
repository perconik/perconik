package sk.stuba.fiit.perconik.core.plugin;

import sk.stuba.fiit.perconik.core.services.Service;

import static java.util.Objects.requireNonNull;

abstract class ServiceSetup<S extends Service> {
  final S service;

  ServiceSetup(final S service) {
    this.service = requireNonNull(service);
  }

  abstract void setService();

  abstract void unsetService();

  abstract void registerObjects();
}
