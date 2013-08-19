package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class TestRunDebugListener extends AbstractDebugListener implements TestRunListener
{
	public TestRunDebugListener()
	{
	}
	
	public TestRunDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void sessionLaunched(final ITestRunSession session)
	{
		this.printHeader("Test run session launched");
		this.printTestRunSession(session);
	}

	public final void sessionStarted(final ITestRunSession session)
	{
		this.printHeader("Test run session started");
		this.printTestRunSession(session);
	}

	public final void sessionFinished(final ITestRunSession session)
	{
		this.printHeader("Test run session finished");
		this.printTestRunSession(session);
	}

	public final void testCaseStarted(final ITestCaseElement element)
	{
		this.printHeader("Test case started");
		this.printTestCaseElement(element);
	}

	public final void testCaseFinished(final ITestCaseElement element)
	{
		this.printHeader("Test case finished");
		this.printTestCaseElement(element);
	}
	
	private final void printTestCaseElement(final ITestCaseElement element)
	{
		this.put(Debug.dumpTestCaseElement(element));
	}
	
	private final void printTestRunSession(final ITestRunSession session)
	{
		this.put(Debug.dumpTestRunSession(session));
	}
}
