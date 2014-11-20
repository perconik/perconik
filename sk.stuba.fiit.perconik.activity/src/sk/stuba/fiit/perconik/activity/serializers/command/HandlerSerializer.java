package sk.stuba.fiit.perconik.activity.serializers.command;

import org.eclipse.core.commands.IHandler;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class HandlerSerializer extends AbstractConfigurableSerializer<IHandler> {
  public HandlerSerializer(final Option ... options) {
    super(options);
  }

  public HandlerSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putHandler(final StructuredContent content, final IHandler handler) {
    content.put(key("isEnabled"), handler.isEnabled());
    content.put(key("isHandled"), handler.isHandled());
  }

  @Override
  protected void put(final StructuredContent content, final IHandler handler) {
    putObjectIdentity(content, handler);
    putHandler(content, handler);
  }
}
