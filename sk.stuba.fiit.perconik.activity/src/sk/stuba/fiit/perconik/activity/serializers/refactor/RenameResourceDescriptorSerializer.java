package sk.stuba.fiit.perconik.activity.serializers.refactor;

import java.util.Set;

import org.eclipse.ltk.core.refactoring.resource.RenameResourceDescriptor;

import sk.stuba.fiit.perconik.activity.serializers.resource.PathSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class RenameResourceDescriptorSerializer extends AbstractRefactoringDescriptorSerializer<RenameResourceDescriptor> {
  public RenameResourceDescriptorSerializer(final Option ... options) {
    super(options);
  }

  public RenameResourceDescriptorSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putRenameResourceDescriptor(final StructuredContent content, final RenameResourceDescriptor descriptor, final Set<Option> options) {
    PathSerializer serializer = new PathSerializer(options);

    content.put(key("source"), serializer.serialize(descriptor.getResourcePath()));
    content.put(key("name", "new"), descriptor.getNewName());

    content.put(key("isUpdateReferences"), descriptor.isUpdateReferences());
  }
}
