package sk.stuba.fiit.perconik.activity.serializers.resource;

import org.eclipse.core.runtime.IPath;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class PathSerializer extends AbstractConfigurableSerializer<IPath> {
  public PathSerializer(final Option ... options) {
    super(options);
  }

  public PathSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected void put(final StructuredContent content, final IPath path) {
    content.put(key("device"), path.getDevice());
    content.put(key("segments"), path.segments());
    content.put(key("extension"), path.getFileExtension());

    content.put(key("value", "standard"), path.toString());
    content.put(key("value", "platform"), path.toOSString());
    content.put(key("value", "portable"), path.toPortableString());

    content.put(key("isAbsolute"), path.isAbsolute());
    content.put(key("isEmpty"), path.isEmpty());
    content.put(key("isRoot"), path.isRoot());
    content.put(key("isUnc"), path.isUNC());
  }
}
