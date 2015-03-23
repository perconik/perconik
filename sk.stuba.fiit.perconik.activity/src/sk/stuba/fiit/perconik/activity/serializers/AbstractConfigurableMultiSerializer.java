package sk.stuba.fiit.perconik.activity.serializers;

import java.util.Set;

import static com.google.common.collect.ImmutableSet.copyOf;

public abstract class AbstractConfigurableMultiSerializer<T> extends AbstractMultiSerializer<T> implements ConfigurableSerializer<T> {
  protected final Set<Option> options;

  protected AbstractConfigurableMultiSerializer(final Option ... options) {
    this(copyOf(options));
  }

  protected AbstractConfigurableMultiSerializer(final Iterable<Option> options) {
    this.options = copyOf(options);
  }
}
