package sk.stuba.fiit.perconik.activity.serializers.command;

import java.util.Set;

import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.ITypedParameter;
import org.eclipse.core.commands.ParameterType;
import org.eclipse.core.commands.ParameterValuesException;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class ParameterSerializer extends AbstractConfigurableSerializer<IParameter> {
  public ParameterSerializer(final Option ... options) {
    super(options);
  }

  public ParameterSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putParameter(final StructuredContent content, final IParameter parameter, final Set<Option> options) {
    content.put(key("identifier"), parameter.getId());
    content.put(key("name"), parameter.getName());

    if (parameter instanceof ITypedParameter) {
      ParameterType type = ((ITypedParameter) parameter).getParameterType();

      content.put(key("type"), new ParameterTypeSerializer(options).serialize(type));
    }

    try {
      content.put(key("values"), parameter.getValues().getParameterValues());
    } catch (ParameterValuesException ignoree) {}

    content.put(key("isOptional"), parameter.isOptional());
  }

  @Override
  protected void put(final StructuredContent content, final IParameter parameter) {
    putObjectIdentity(content, parameter);
    putParameter(content, parameter, this.options);
  }
}
