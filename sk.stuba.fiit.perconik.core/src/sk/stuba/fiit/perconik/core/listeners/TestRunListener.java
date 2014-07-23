package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;

import sk.stuba.fiit.perconik.core.Listener;

/**
 * A test run listener.
 * 
 * @see Listener 
 * @see org.eclipse.jdt.junit.TestRunListener TestRunListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public interface TestRunListener extends Listener
{
	public void sessionLaunched(ITestRunSession session);
	
	public void sessionStarted(ITestRunSession session);
	
	public void sessionFinished(ITestRunSession session);

	public void testCaseStarted(ITestCaseElement testCaseElement);

	public void testCaseFinished(ITestCaseElement testCaseElement);
}
