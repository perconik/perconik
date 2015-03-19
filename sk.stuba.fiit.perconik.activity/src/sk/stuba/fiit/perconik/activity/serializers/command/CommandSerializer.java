package sk.stuba.fiit.perconik.activity.serializers.command;

import java.util.Set;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.common.NotDefinedException;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class CommandSerializer extends AbstractConfigurableMultiSerializer<Command> {
  public CommandSerializer(final Option ... options) {
    super(options);
  }

  public CommandSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putCommand(final StructuredContent content, final Command command, final Set<Option> options) {
    content.put(key("identifier"), command.getId());

    try {
      content.put(key("name"), command.getName());
      content.put(key("description"), command.getDescription());
    } catch (NotDefinedException ignore) {}

    try {
      content.put(key("category"), new CategorySerializer(options).serialize(command.getCategory()));
    } catch (NotDefinedException ignore) {}

    try {
      content.put(key("parameters"), new ParameterSerializer(options).serialize(asList(command.getParameters())));
    } catch (NotDefinedException ignore) {}

    try {
      content.put(key("returnType"), new ParameterTypeSerializer(options).serialize(command.getReturnType()));
    } catch (NotDefinedException ignore) {}

    content.put(key("handler"), new HandlerSerializer(options).serialize(command.getHandler()));

    content.put(key("isDefined"), command.isDefined());
    content.put(key("isEnabled"), command.isEnabled());
    content.put(key("isHandled"), command.isHandled());
  }

  @Override
  protected void put(final StructuredContent content, final Command command) {
    putObjectIdentity(content, command);
    putCommand(content, command, this.options);
  }
}
