package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;

/**
 * An abstract adapter class for a {@code TestRunListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code TestRunListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see TestRunListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class TestRunAdapter extends Adapter implements TestRunListener {
  /**
   * Constructor for use by subclasses.
   */
  protected TestRunAdapter() {}

  public void sessionLaunched(ITestRunSession session) {}

  public void sessionStarted(ITestRunSession session) {}

  public void sessionFinished(ITestRunSession session) {}

  public void testCaseStarted(ITestCaseElement element) {}

  public void testCaseFinished(ITestCaseElement element) {}
}
