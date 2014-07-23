package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.Listener;

import static com.google.common.base.Preconditions.checkNotNull;

abstract class AbstractWrapper<L extends Listener> extends Adapter implements Wrapper<L> {
  final L listener;

  AbstractWrapper(final L listener) {
    this.listener = checkNotNull(listener);
  }

  public final L forListener() {
    return this.listener;
  }
}
