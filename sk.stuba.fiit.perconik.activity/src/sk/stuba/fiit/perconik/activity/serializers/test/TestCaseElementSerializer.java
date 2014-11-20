package sk.stuba.fiit.perconik.activity.serializers.test;

import java.util.Set;

import org.eclipse.jdt.junit.model.ITestCaseElement;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TestCaseElementSerializer extends AbstractTestElementSerializer<ITestCaseElement> {
  public TestCaseElementSerializer(final Option ... options) {
    super(options);
  }

  public TestCaseElementSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTestCaseElement(final StructuredContent content, final ITestCaseElement element, @SuppressWarnings("unused") final Set<Option> options) {
    content.put(key("test", "class", "name"), element.getTestClassName());
    content.put(key("test", "method", "name"), element.getTestMethodName());
  }
}
