package sk.stuba.fiit.perconik.activity.serializers.refactor;

import java.util.Set;

import org.eclipse.ltk.core.refactoring.resource.DeleteResourcesDescriptor;

import sk.stuba.fiit.perconik.activity.serializers.resource.PathSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class DeleteResourcesDescriptorSerializer extends AbstractRefactoringDescriptorSerializer<DeleteResourcesDescriptor> {
  public DeleteResourcesDescriptorSerializer(final Option ... options) {
    super(options);
  }

  public DeleteResourcesDescriptorSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putDeleteResourcesDescriptor(final StructuredContent content, final DeleteResourcesDescriptor descriptor, final Set<Option> options) {
    PathSerializer serializer = new PathSerializer(options);

    content.put(key("sources"), serializer.serialize(asList(descriptor.getResourcePaths())));

    content.put(key("isDeleteContents"), descriptor.isDeleteContents());
  }
}
