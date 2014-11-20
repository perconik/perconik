package sk.stuba.fiit.perconik.activity.serializers.command;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.ObjectUndoContext;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class UndoContextSerializer extends AbstractConfigurableSerializer<IUndoContext> {
  public UndoContextSerializer(final Option ... options) {
    super(options);
  }

  public UndoContextSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putUndoContext(final StructuredContent content, final IUndoContext context) {
    content.put(key("label"), context.getLabel());

    if (context instanceof ObjectUndoContext) {
      content.put(key("object"), identifyObject(((ObjectUndoContext) context).getObject()));
    }
  }

  @Override
  protected void put(final StructuredContent content, final IUndoContext context) {
    putObjectIdentity(content, context);
    putUndoContext(content, context);
  }
}
