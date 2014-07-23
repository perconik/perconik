package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.ListenerUnregistrationException;

import static sk.stuba.fiit.perconik.core.utilities.LogHelper.log;

final class StandardListenerService extends AbstractListenerService {
  StandardListenerService(final Builder builder) {
    super(builder);
  }

  public static final class Builder extends AbstractBuilder<Builder> {
    public Builder() {}

    @Override
    protected final Builder implementation() {
      return this;
    }

    @Override
    public final ListenerService build() {
      return new StandardListenerService(this);
    }
  }

  public static final Builder builder() {
    return new Builder();
  }

  @Override
  protected final void doStart() {
    this.notifyStarted();
  }

  @Override
  protected final void doStop() {
    try {
      this.manager.unregisterAll(sk.stuba.fiit.perconik.core.Listener.class);
    } catch (ListenerUnregistrationException failure) {
      log.error(failure, "Unexpected error during final unregistration of listeners");
    }

    this.notifyStopped();
  }
}
