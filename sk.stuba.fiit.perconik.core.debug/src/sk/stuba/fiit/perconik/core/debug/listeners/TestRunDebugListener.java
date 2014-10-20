package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;

public final class TestRunDebugListener extends AbstractDebugListener implements TestRunListener {
  public TestRunDebugListener() {}

  public TestRunDebugListener(final DebugConsole console) {
    super(console);
  }

  public void sessionLaunched(final ITestRunSession session) {
    this.printHeader("Test run session launched");
    this.printTestRunSession(session);
  }

  public void sessionStarted(final ITestRunSession session) {
    this.printHeader("Test run session started");
    this.printTestRunSession(session);
  }

  public void sessionFinished(final ITestRunSession session) {
    this.printHeader("Test run session finished");
    this.printTestRunSession(session);
  }

  public void testCaseStarted(final ITestCaseElement element) {
    this.printHeader("Test case started");
    this.printTestCaseElement(element);
  }

  public void testCaseFinished(final ITestCaseElement element) {
    this.printHeader("Test case finished");
    this.printTestCaseElement(element);
  }

  private void printTestCaseElement(final ITestCaseElement element) {
    this.put(Debug.dumpTestCaseElement(element));
  }

  private void printTestRunSession(final ITestRunSession session) {
    this.put(Debug.dumpTestRunSession(session));
  }
}
