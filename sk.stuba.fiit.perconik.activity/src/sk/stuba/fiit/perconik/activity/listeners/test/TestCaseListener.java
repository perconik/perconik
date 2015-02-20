package sk.stuba.fiit.perconik.activity.listeners.test;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.CommonEventListener;
import sk.stuba.fiit.perconik.activity.serializers.test.TestCaseElementSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;

import static sk.stuba.fiit.perconik.activity.listeners.test.TestCaseListener.Action.FINISH;
import static sk.stuba.fiit.perconik.activity.listeners.test.TestCaseListener.Action.START;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.0.alpha")
public final class TestCaseListener extends CommonEventListener implements TestRunListener {
  public TestCaseListener() {}

  enum Action implements CommonEventListener.Action {
    START,

    FINISH;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "test", "case", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action, final ITestCaseElement element) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("case"), new TestCaseElementSerializer(TREE).serialize(element));

    data.put(key("case", "session"), identifyObject(element.getTestRunSession()));

    return data;
  }

  void process(final long time, final Action action, final ITestCaseElement element) {
    this.send(action.getPath(), build(time, action, element));
  }

  void execute(final long time, final Action action, final ITestCaseElement element) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, element);
      }
    });
  }

  public void testCaseStarted(final ITestCaseElement element) {
    final long time = this.currentTime();

    this.execute(time, START, element);
  }

  public void testCaseFinished(final ITestCaseElement element) {
    final long time = this.currentTime();

    this.execute(time, FINISH, element);
  }

  public void sessionLaunched(final ITestRunSession session) {
    // ignore
  }

  public void sessionStarted(final ITestRunSession session) {
    // ignore
  }

  public void sessionFinished(final ITestRunSession session) {
    // ignore
  }
}
