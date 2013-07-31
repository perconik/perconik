package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;
import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

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
		// TODO
	}

	public final void sessionStarted(final ITestRunSession session)
	{
		// TODO
	}

	public final void sessionFinished(final ITestRunSession session)
	{
		// TODO
	}

	public final void testCaseStarted(final ITestCaseElement testCaseElement)
	{
		// TODO
	}

	public final void testCaseFinished(final ITestCaseElement testCaseElement)
	{
		// TODO
	}
}
