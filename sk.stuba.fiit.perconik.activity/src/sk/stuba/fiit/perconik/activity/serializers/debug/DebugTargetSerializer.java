package sk.stuba.fiit.perconik.activity.serializers.debug;

import java.util.Set;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class DebugTargetSerializer extends AbstractConfigurableSerializer<IDebugTarget> {
  public DebugTargetSerializer(final Option ... options) {
    super(options);
  }

  public DebugTargetSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putDebugTarget(final StructuredContent content, final IDebugTarget target, final Set<Option> options) {
    content.put(key("model", "identifier"), target.getModelIdentifier());

    try {
      content.put(key("name"), target.getName());
    } catch (DebugException ignore) {}

    content.put(key("process"), new ProcessSerializer(options).serialize(target.getProcess()));

    content.put(key("canDisconnect"), target.canDisconnect());
    content.put(key("canResume"), target.canResume());
    content.put(key("canSuspend"), target.canSuspend());
    content.put(key("canTerminate"), target.canTerminate());

    content.put(key("isDisconnected"), target.isDisconnected());
    content.put(key("isSuspended"), target.isSuspended());
    content.put(key("isTerminated"), target.isTerminated());
  }

  @Override
  protected void put(final StructuredContent content, final IDebugTarget target) {
    putObjectIdentity(content, target);
    putDebugTarget(content, target, this.options);
  }
}
