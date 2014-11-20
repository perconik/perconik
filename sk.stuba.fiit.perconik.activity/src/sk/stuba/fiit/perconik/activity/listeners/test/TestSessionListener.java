package sk.stuba.fiit.perconik.activity.listeners.test;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.test.TestRunSessionSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;

import static sk.stuba.fiit.perconik.activity.listeners.test.TestSessionListener.Action.FINISH;
import static sk.stuba.fiit.perconik.activity.listeners.test.TestSessionListener.Action.LAUNCH;
import static sk.stuba.fiit.perconik.activity.listeners.test.TestSessionListener.Action.START;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class TestSessionListener extends CommonEventListener implements TestRunListener {
  public TestSessionListener() {}

  enum Action {
    LAUNCH,

    START,

    FINISH;

    final String name;

    final String path;

    private Action() {
      this.name = actionName("eclipse", "test", "session", this);
      this.path = actionPath(this.name);
    }
  }

  static Event build(final long time, final Action action, final ITestRunSession session) {
    Event data = LocalEvent.of(time, action.name);

    data.put(key("session"), new TestRunSessionSerializer(TREE).serialize(session));

    return data;
  }

  void process(final long time, final Action action, final ITestRunSession session) {
    this.send(action.path, build(time, action, session));
  }

  void execute(final long time, final Action action, final ITestRunSession session) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, session);
      }
    });
  }

  public void sessionLaunched(final ITestRunSession session) {
    final long time = currentTime();

    this.execute(time, LAUNCH, session);
  }

  public void sessionStarted(final ITestRunSession session) {
    final long time = currentTime();

    this.execute(time, START, session);
  }

  public void sessionFinished(final ITestRunSession session) {
    final long time = currentTime();

    this.execute(time, FINISH, session);
  }

  public void testCaseStarted(final ITestCaseElement element) {
    // ignore
  }

  public void testCaseFinished(final ITestCaseElement element) {
    // ignore
  }
}
