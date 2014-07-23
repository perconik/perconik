package sk.stuba.fiit.perconik.core.services.resources;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;

final class Unsafe {
  private Unsafe() {
    throw new AssertionError();
  }

  static final <L extends Listener> Resource<L> cast(final Class<L> type, final Resource<?> resource) {
    @SuppressWarnings("serial")
    TypeToken<Resource<L>> token = new TypeToken<Resource<L>>() {}.where(new TypeParameter<L>() {}, TypeToken.of(type));

    return (Resource<L>) token.getRawType().cast(resource);
  }
}
