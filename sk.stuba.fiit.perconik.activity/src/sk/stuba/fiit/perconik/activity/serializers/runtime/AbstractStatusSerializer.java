package sk.stuba.fiit.perconik.activity.serializers.runtime;

import org.eclipse.core.runtime.IStatus;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.runtime.StatusSerializer.putStatus;

abstract class AbstractStatusSerializer<T extends IStatus> extends AbstractConfigurableSerializer<T> {
  AbstractStatusSerializer(final Option ... options) {
    super(options);
  }

  AbstractStatusSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T status) {
    putObjectIdentity(content, status);
    putStatus(content, status, this.options);
  }
}
