package sk.stuba.fiit.perconik.core.services.resources;

import sk.stuba.fiit.perconik.core.ResourceUnregistrationException;

import static sk.stuba.fiit.perconik.core.plugin.Activator.defaultConsole;

final class StandardResourceService extends AbstractResourceService {
  StandardResourceService(final Builder builder) {
    super(builder);
  }

  public static final class Builder extends AbstractBuilder<Builder> {
    public Builder() {}

    @Override
    protected Builder implementation() {
      return this;
    }

    @Override
    public ResourceService build() {
      return new StandardResourceService(this);
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
    } catch (ResourceUnregistrationException failure) {
      defaultConsole().error(failure, "Unexpected error during unregistration of resources");
    }

    this.notifyStopped();
  }
}
