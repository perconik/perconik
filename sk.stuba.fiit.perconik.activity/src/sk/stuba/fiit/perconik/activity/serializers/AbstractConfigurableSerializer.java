package sk.stuba.fiit.perconik.activity.serializers;

import java.util.Set;

import static com.google.common.collect.ImmutableSet.copyOf;

public abstract class AbstractConfigurableSerializer<T> extends AbstractMultiSerializer<T> implements ConfigurableSerializer {
  protected final Set<Option> options;

  protected AbstractConfigurableSerializer(final Option ... options) {
    this(copyOf(options));
  }

  protected AbstractConfigurableSerializer(final Iterable<Option> options) {
    this.options = copyOf(options);
  }
}
