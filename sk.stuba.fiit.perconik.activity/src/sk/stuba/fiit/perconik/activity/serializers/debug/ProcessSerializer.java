package sk.stuba.fiit.perconik.activity.serializers.debug;

import org.eclipse.debug.core.model.IProcess;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class ProcessSerializer extends AbstractConfigurableSerializer<IProcess> {
  public ProcessSerializer(final Option ... options) {
    super(options);
  }

  public ProcessSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putProcess(final StructuredContent content, final IProcess process) {
    content.put(key("label"), process.getLabel());

    content.put(key("canTerminate"), process.canTerminate());

    content.put(key("isTerminated"), process.isTerminated());
  }

  @Override
  protected void put(final StructuredContent content, final IProcess process) {
    putObjectIdentity(content, process);
    putProcess(content, process);
  }
}
