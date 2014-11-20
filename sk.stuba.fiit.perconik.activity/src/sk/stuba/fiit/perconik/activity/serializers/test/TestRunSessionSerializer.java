package sk.stuba.fiit.perconik.activity.serializers.test;

import java.util.Set;

import org.eclipse.jdt.junit.model.ITestRunSession;

import sk.stuba.fiit.perconik.activity.serializers.resource.ProjectSerializer;
import sk.stuba.fiit.perconik.data.content.StructuredContent;

import static com.google.common.collect.ImmutableSet.of;
import static com.google.common.collect.Sets.difference;

import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

public final class TestRunSessionSerializer extends AbstractTestElementSerializer<ITestRunSession> {
  public TestRunSessionSerializer(final Option ... options) {
    super(options);
  }

  public TestRunSessionSerializer(final Iterable<Option> options) {
    super(options);
  }

  static void putTestRunSession(final StructuredContent content, final ITestRunSession session, final Set<Option> options) {
    content.put(key("run", "name"), session.getTestRunName());
    content.put(key("run", "project"), new ProjectSerializer(difference(options, of(StandardOption.TREE))).serialize(session.getLaunchedProject().getProject()));
  }
}
