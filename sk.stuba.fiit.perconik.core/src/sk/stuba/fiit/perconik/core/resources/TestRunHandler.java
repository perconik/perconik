package sk.stuba.fiit.perconik.core.resources;

import javax.annotation.Nullable;

import org.eclipse.jdt.junit.JUnitCore;
import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;

import sk.stuba.fiit.perconik.core.listeners.TestRunListener;

import static java.util.Objects.requireNonNull;

enum TestRunHandler implements Handler<TestRunListener> {
  INSTANCE;

  private static final class TestRunProxy extends org.eclipse.jdt.junit.TestRunListener {
    private final TestRunListener listener;

    public TestRunProxy(final TestRunListener listener) {
      this.listener = requireNonNull(listener);
    }

    @Override
    public void sessionLaunched(final ITestRunSession session) {
      this.listener.sessionLaunched(session);
    }

    @Override
    public void sessionStarted(final ITestRunSession session) {
      this.listener.sessionStarted(session);
    }

    @Override
    public void sessionFinished(final ITestRunSession session) {
      this.listener.sessionFinished(session);
    }

    @Override
    public void testCaseStarted(final ITestCaseElement testCaseElement) {
      this.listener.testCaseStarted(testCaseElement);
    }

    @Override
    public void testCaseFinished(final ITestCaseElement testCaseElement) {
      this.listener.testCaseFinished(testCaseElement);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
      if (this == o) {
        return true;
      }

      if (!(o instanceof TestRunProxy)) {
        return false;
      }

      TestRunProxy other = (TestRunProxy) o;

      return this.listener.equals(other.listener);
    }

    @Override
    public int hashCode() {
      return this.listener.hashCode();
    }

    @Override
    public String toString() {
      return this.listener.toString();
    }
  }

  public void register(final TestRunListener listener) {
    JUnitCore.addTestRunListener(new TestRunProxy(listener));
  }

  public void unregister(final TestRunListener listener) {
    JUnitCore.addTestRunListener(new TestRunProxy(listener));
  }
}
