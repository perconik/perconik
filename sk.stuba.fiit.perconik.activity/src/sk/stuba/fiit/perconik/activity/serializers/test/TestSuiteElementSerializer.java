package sk.stuba.fiit.perconik.activity.serializers.test;

import java.util.Set;

import org.eclipse.jdt.junit.model.ITestSuiteElement;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TestSuiteElementSerializer extends AbstractTestElementSerializer<ITestSuiteElement> {
  public TestSuiteElementSerializer(final Option ... options) {
    super(options);
  }

  public TestSuiteElementSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTestSuiteElement(final StructuredContent content, final ITestSuiteElement element, @SuppressWarnings("unused") final Set<Option> options) {
    content.put(key("type", "name"), element.getSuiteTypeName());
  }
}
