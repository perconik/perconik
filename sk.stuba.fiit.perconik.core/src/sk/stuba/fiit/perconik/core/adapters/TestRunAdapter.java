package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;

public class TestRunAdapter extends Adapter implements TestRunListener
{
	public void sessionLaunched(ITestRunSession session)
	{
	}

	public void sessionStarted(ITestRunSession session)
	{
	}

	public void sessionFinished(ITestRunSession session)
	{
	}

	public void testCaseStarted(ITestCaseElement element)
	{
	}

	public void testCaseFinished(ITestCaseElement element)
	{
	}
}
