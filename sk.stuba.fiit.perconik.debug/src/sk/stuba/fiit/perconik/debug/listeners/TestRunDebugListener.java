package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class TestRunDebugListener extends AbstractDebugListener implements TestRunListener
{
	public TestRunDebugListener()
	{
	}
	
	public TestRunDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void sessionLaunched(final ITestRunSession session)
	{
	}

	public final void sessionStarted(final ITestRunSession session)
	{
	}

	public final void sessionFinished(final ITestRunSession session)
	{
	}

	public final void testCaseStarted(final ITestCaseElement testCaseElement)
	{
	}

	public final void testCaseFinished(final ITestCaseElement testCaseElement)
	{
	}
}
