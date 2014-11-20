package sk.stuba.fiit.perconik.activity.serializers.test;

import java.util.Set;

import org.eclipse.jdt.junit.model.ITestElement;
import org.eclipse.jdt.junit.model.ITestElement.FailureTrace;

import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TestElementSerializer extends AbstractTestElementSerializer<ITestElement> {
  public TestElementSerializer(final Option ... options) {
    super(options);
  }

  public TestElementSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTestElement(final StructuredContent content, final ITestElement element, @SuppressWarnings("unused") final Set<Option> options) {
    content.put(key("state"), element.getProgressState().toString().toLowerCase());

    content.put(key("test", "result", "includingChildren"), element.getTestResult(true).toString().toLowerCase());
    content.put(key("test", "result", "excludingChildren"), element.getTestResult(false).toString().toLowerCase());

    FailureTrace trace = element.getFailureTrace();

    content.put(key("failure", "result", "actual"), trace.getActual());
    content.put(key("failure", "result", "expected"), trace.getExpected());
    content.put(key("failure", "stack", "trace"), trace.getTrace());

    content.put(key("elapsedTime"), element.getElapsedTimeInSeconds());
  }
}
