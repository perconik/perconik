package sk.stuba.fiit.perconik.activity.serializers.ui.text;

import java.util.Set;

import org.eclipse.jdt.core.IClassFile;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.activity.serializers.resource.FileSerializer;
import sk.stuba.fiit.perconik.activity.serializers.resource.PathSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.jdt.core.dom.NodeType;
import sk.stuba.fiit.perconik.eclipse.jdt.ui.UnderlyingResource;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class UnderlyingResourceSerializer extends AbstractConfigurableMultiSerializer<UnderlyingResource<?>> {
  public UnderlyingResourceSerializer(final Option ... options) {
    super(options);
  }

  public UnderlyingResourceSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putUnderlyingResource(final StructuredContent content, final UnderlyingResource<?> resource, final Set<Option> options) {
    Object raw = resource.getRaw();

    content.put(key("raw"), identifyObject(raw));
    content.put(key("path"), new PathSerializer(options).serialize(resource.getPath().orNull()));
    content.put(key("file"), new FileSerializer(options).serialize(resource.getFile().orNull()));

    if (raw instanceof IClassFile) {
      IClassFile classFile = (IClassFile) raw;

      content.put(key("classFile", "type"), NodeType.valueOf(classFile.getElementType()).toString().toLowerCase());
      content.put(key("classFile", "name"), classFile.getElementName());

      content.put(key("classFile", "handle"), classFile.getHandleIdentifier());

      content.put(key("classFile", "isOpen"), classFile.isOpen());
      content.put(key("classFile", "isReadOnly"), classFile.isReadOnly());
    }
  }

  @Override
  protected void put(final StructuredContent content, final UnderlyingResource<?> resource) {
    putUnderlyingResource(content, resource, this.options);
  }
}
