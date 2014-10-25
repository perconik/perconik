package sk.stuba.fiit.perconik.core.services.listeners;

import sk.stuba.fiit.perconik.core.ListenerUnregistrationException;

import static sk.stuba.fiit.perconik.core.plugin.Activator.defaultConsole;

final class StandardListenerService extends AbstractListenerService {
  StandardListenerService(final Builder builder) {
    super(builder);
  }

  public static final class Builder extends AbstractBuilder<Builder> {
    public Builder() {}

    @Override
    protected Builder asSubtype() {
      return this;
    }

    @Override
    public ListenerService build() {
      return new StandardListenerService(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  protected void doStart() {
    this.notifyStarted();
  }

  @Override
  protected void doStop() {
    try {
      this.manager.unregisterAll(sk.stuba.fiit.perconik.core.Listener.class);
    } catch (ListenerUnregistrationException failure) {
      defaultConsole().error(failure, "Unexpected error during unregistration of listeners");
    }

    this.notifyStopped();
  }
}
