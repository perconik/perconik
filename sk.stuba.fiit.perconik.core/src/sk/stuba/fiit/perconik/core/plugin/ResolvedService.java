package sk.stuba.fiit.perconik.core.plugin;

import sk.stuba.fiit.perconik.core.services.Service;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class ResolvedService<S extends Service> {
  final S service;

  ResolvedService(final S service) {
    this.service = checkNotNull(service);
  }
}
