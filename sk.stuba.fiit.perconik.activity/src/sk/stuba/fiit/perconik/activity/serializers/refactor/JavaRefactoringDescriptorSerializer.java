package sk.stuba.fiit.perconik.activity.serializers.refactor;

import java.util.Set;

import org.eclipse.jdt.core.refactoring.descriptors.JavaRefactoringDescriptor;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

public final class JavaRefactoringDescriptorSerializer extends AbstractRefactoringDescriptorSerializer<JavaRefactoringDescriptor> {
  public JavaRefactoringDescriptorSerializer(final Option ... options) {
    super(options);
  }

  public JavaRefactoringDescriptorSerializer(final Iterable<Option> options) {
    super(options);
  }

  // TODO put significant fields of JavaRefactoringDescriptor into content here
  @SuppressWarnings("unused")
  static void putJavaRefactoringDescriptor(final StructuredContent content, final JavaRefactoringDescriptor descriptor, final Set<Option> options) {}
}
