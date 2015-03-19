package sk.stuba.fiit.perconik.activity.serializers.refactor;

import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;
import org.eclipse.ltk.core.refactoring.resource.DeleteResourcesDescriptor;
import org.eclipse.ltk.core.refactoring.resource.MoveResourcesDescriptor;
import org.eclipse.ltk.core.refactoring.resource.RenameResourceDescriptor;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.activity.serializers.refactor.DeleteResourcesDescriptorSerializer.putDeleteResourcesDescriptor;
import static sk.stuba.fiit.perconik.activity.serializers.refactor.MoveResourcesDescriptorSerializer.putMoveResourcesDescriptor;
import static sk.stuba.fiit.perconik.activity.serializers.refactor.RefactoringDescriptorSerializer.putRefactoringDescriptor;
import static sk.stuba.fiit.perconik.activity.serializers.refactor.RenameResourceDescriptorSerializer.putRenameResourceDescriptor;

abstract class AbstractRefactoringDescriptorSerializer<T extends RefactoringDescriptor> extends AbstractConfigurableMultiSerializer<T> {
  AbstractRefactoringDescriptorSerializer(final Option ... options) {
    super(options);
  }

  AbstractRefactoringDescriptorSerializer(final Iterable<Option> options) {
    super(options);
  }

  @Override
  protected final void put(final StructuredContent content, final T descriptor) {
    putObjectIdentity(content, descriptor);
    putRefactoringDescriptor(content, descriptor);

    if (descriptor instanceof MoveResourcesDescriptor) {
      putMoveResourcesDescriptor(content, (MoveResourcesDescriptor) descriptor, this.options);
    }

    if (descriptor instanceof RenameResourceDescriptor) {
      putRenameResourceDescriptor(content, (RenameResourceDescriptor) descriptor, this.options);
    }

    if (descriptor instanceof DeleteResourcesDescriptor) {
      putDeleteResourcesDescriptor(content, (DeleteResourcesDescriptor) descriptor, this.options);
    }
  }
}
