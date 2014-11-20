package sk.stuba.fiit.perconik.activity.serializers.command;

import org.eclipse.core.commands.ParameterType;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class ParameterTypeSerializer extends AbstractConfigurableSerializer<ParameterType> {
  public ParameterTypeSerializer(final Option ... options) {
    super(options);
  }

  public ParameterTypeSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putParameterType(final StructuredContent content, final ParameterType type) {
    content.put(key("identifier"), type.getId());

    content.put(key("isDefined"), type.isDefined());
  }

  @Override
  protected void put(final StructuredContent content, final ParameterType type) {
    putObjectIdentity(content, type);
    putParameterType(content, type);
  }
}
