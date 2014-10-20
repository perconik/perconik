package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.Listener;

import static com.google.common.base.Preconditions.checkNotNull;

final class StandardHookSupport<H extends Hook<T, L>, T, L extends Listener> extends AbstractHookSupport<H, T, L> {
  private final HookFactory<T, L> factory;

  StandardHookSupport(final HookFactory<T, L> factory) {
    this.factory = checkNotNull(factory);
  }

  static <H extends Hook<T, L>, T, L extends Listener> StandardHookSupport<H, T, L> using(final HookFactory<T, L> factory) {
    return new StandardHookSupport<>(factory);
  }

  public Hook<T, L> create(final L listener) {
    return this.factory.create(listener);
  }
}
