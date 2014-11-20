package sk.stuba.fiit.perconik.activity.serializers.refactor;

import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;

import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.ltk.core.refactoring.RefactoringFlag;

import static com.google.common.collect.Iterables.transform;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.toLowerCaseFunction;

public final class RefactoringDescriptorSerializer extends AbstractRefactoringDescriptorSerializer<RefactoringDescriptor> {
  public RefactoringDescriptorSerializer(final Option ... options) {
    super(options);
  }

  public RefactoringDescriptorSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putRefactoringDescriptor(final StructuredContent content, final RefactoringDescriptor descriptor) {
    content.put(key("timestamp"), descriptor.getTimeStamp());
    content.put(key("identifier"), descriptor.getID());
    content.put(key("description"), descriptor.getDescription());
    content.put(key("comment"), descriptor.getComment());

    content.put(key("flags"), transform(RefactoringFlag.setOf(descriptor.getFlags()), toLowerCaseFunction()));

    content.put(key("project", "name"), descriptor.getProject());
  }
}
