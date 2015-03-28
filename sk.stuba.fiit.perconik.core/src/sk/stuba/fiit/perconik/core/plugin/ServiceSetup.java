package sk.stuba.fiit.perconik.core.plugin;

import sk.stuba.fiit.perconik.core.services.Service;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class ServiceSetup<S extends Service> {
  final S service;

  ServiceSetup(final S service) {
    this.service = checkNotNull(service);
  }

  abstract void setService();

  abstract void unsetService();

  abstract void registerObjects();
}
