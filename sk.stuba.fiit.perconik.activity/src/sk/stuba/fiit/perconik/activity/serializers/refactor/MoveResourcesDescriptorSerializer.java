package sk.stuba.fiit.perconik.activity.serializers.refactor;

import java.util.Set;

import org.eclipse.ltk.core.refactoring.resource.MoveResourcesDescriptor;

import sk.stuba.fiit.perconik.activity.serializers.resource.PathSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class MoveResourcesDescriptorSerializer extends AbstractRefactoringDescriptorSerializer<MoveResourcesDescriptor> {
  public MoveResourcesDescriptorSerializer(final Option ... options) {
    super(options);
  }

  public MoveResourcesDescriptorSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putMoveResourcesDescriptor(final StructuredContent content, final MoveResourcesDescriptor descriptor, final Set<Option> options) {
    PathSerializer serializer = new PathSerializer(options);

    content.put(key("sources"), serializer.serialize(asList(descriptor.getResourcePathsToMove())));
    content.put(key("destination"), serializer.serialize(descriptor.getDestinationPath()));

    content.put(key("isUpdateReferences"), descriptor.isUpdateReferences());
  }
}
