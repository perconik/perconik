package sk.stuba.fiit.perconik.activity.serializers.test;

import java.util.Set;

import org.eclipse.jdt.junit.model.ITestElementContainer;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static java.util.Arrays.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TestElementContainerSerializer extends AbstractTestElementSerializer<ITestElementContainer> {
  public TestElementContainerSerializer(final Option ... options) {
    super(options);
  }

  public TestElementContainerSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTestElementContainer(final StructuredContent content, final ITestElementContainer container, final Set<Option> options) {
    if (options.contains(StandardOption.TREE)) {
      content.put(key("children"), new TestElementSerializer(options).serialize(asList(container.getChildren())));
    }
  }
}
