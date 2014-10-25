package sk.stuba.fiit.perconik.activity.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class RegularEventListener extends AbstractEventListener {
  protected RegularEventListener(final Configuration configuration) {
    super(configuration);
  }

  public static final class RegularConfiguration extends AbstractConfiguration {
    RegularConfiguration(final Builder builder) {
      super(builder);
    }

    public static final class Builder extends AbstractBuilder<Builder> {
      public Builder() {
      }

      @Override
      protected Builder asSubtype() {
        return this;
      }

      @Override
      public RegularConfiguration build() {
        return new RegularConfiguration(this);
      }
    }

    public static Builder builder() {
      return new Builder();
    }
  }

  protected final class ConfigurationProbe extends AbstractConfigurationProbe {
    protected ConfigurationProbe() {}
  }

  public static final class BasicDisposalHook extends AbstractDisposalHook {
    private final Logger logger;

    BasicDisposalHook(final Builder builder) {
      super(builder);

      this.logger = requireNonNull(builder.logger);
    }

    public static final class Builder extends AbstractBuilder<Builder> {
      Logger logger;

      protected Builder() {
      }

      @Override
      protected Builder asSubtype() {
        return this;
      }

      public Builder logger(final Logger logger) {
        this.logger = requireNonNull(logger);

        return this;
      }

      @Override
      public BasicDisposalHook build() {
        return new BasicDisposalHook(this);
      }
    }

    public static Builder builder() {
      return new Builder();
    }

    @Override
    protected void report(final Object reference, final Exception failure) {
      this.logger.log(Level.INFO, "Unable to dispose " + reference, failure);
    }
  }
}
