package sk.stuba.fiit.perconik.activity.serializers.refactor;

import org.eclipse.ltk.core.refactoring.RefactoringDescriptorProxy;

import sk.stuba.fiit.perconik.activity.serializers.AbstractConfigurableMultiSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.activity.serializers.Serializations.putObjectIdentity;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class RefactoringDescriptorProxySerializer extends AbstractConfigurableMultiSerializer<RefactoringDescriptorProxy> {
  public RefactoringDescriptorProxySerializer(final Option ... options) {
    super(options);
  }

  public RefactoringDescriptorProxySerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putRefactoringDescriptorProxy(final StructuredContent content, final RefactoringDescriptorProxy proxy) {
    content.put(key("timestamp"), proxy.getTimeStamp());
    content.put(key("description"), proxy.getDescription());

    content.put(key("project", "name"), proxy.getProject());
  }

  @Override
  protected void put(final StructuredContent content, final RefactoringDescriptorProxy proxy) {
    putObjectIdentity(content, proxy);
    putRefactoringDescriptorProxy(content, proxy);
  }
}
