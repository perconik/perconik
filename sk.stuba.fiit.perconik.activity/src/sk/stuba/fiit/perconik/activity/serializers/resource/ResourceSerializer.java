package sk.stuba.fiit.perconik.activity.serializers.resource;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;

import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.core.resources.ResourceType;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class ResourceSerializer extends AbstractResourceSerializer<IResource> {
  public ResourceSerializer(final Option ... options) {
    super(options);
  }

  public ResourceSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putResource(final StructuredContent content, final IResource resource, final Iterable<Option> options) {
    content.put(key("type"), ResourceType.valueOf(resource.getType()).toString().toLowerCase());
    content.put(key("name"), resource.getName());

    PathSerializer serializer = new PathSerializer(options);

    content.put(key("path", "local"), serializer.serialize(resource.getLocation()));
    content.put(key("path", "project"), serializer.serialize(resource.getProjectRelativePath()));
    content.put(key("path", "workspace"), serializer.serialize(resource.getFullPath()));

    content.put(key("uri"), resource.getLocationURI());

    content.put(key("raw", "path", "local"), serializer.serialize(resource.getRawLocation()));
    content.put(key("raw", "uri"), resource.getRawLocationURI());

    content.put(key("timestamp", "local"), resource.getLocalTimeStamp());
    content.put(key("timestamp", "modification"), resource.getModificationStamp());

    content.put(key("exists"), resource.exists());

    content.put(key("isAccessible"), resource.isAccessible());
    content.put(key("isDerived"), resource.isDerived());
    content.put(key("isLinked"), resource.isLinked());
    content.put(key("isPhantom"), resource.isPhantom());
    content.put(key("isVirtual"), resource.isVirtual());

    ResourceAttributes attributes = resource.getResourceAttributes();

    if (attributes != null) {
      content.put(key("isArchive"), attributes.isArchive());
      content.put(key("isExecutable"), attributes.isExecutable());
      content.put(key("isHidden"), attributes.isHidden());
      content.put(key("isReadOnly"), attributes.isReadOnly());
      content.put(key("isSymbolicLink"), attributes.isSymbolicLink());
    }
  }
}
