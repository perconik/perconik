package sk.stuba.fiit.perconik.core.listeners;

import org.eclipse.jdt.junit.JUnitCore;
import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
import com.google.common.base.Preconditions;

enum TestRunHandler implements Handler<TestRunListener>
{
	INSTANCE;
	
	private static final class TestRunProxy extends org.eclipse.jdt.junit.TestRunListener
	{
		private final TestRunListener listener;
		
		public TestRunProxy(final TestRunListener listener)
		{
			this.listener = Preconditions.checkNotNull(listener);
		}

		@Override
		public final void sessionLaunched(final ITestRunSession session)
		{
			this.listener.sessionLaunched(session);
		}

		@Override
		public final void sessionStarted(final ITestRunSession session)
		{
			this.listener.sessionStarted(session);
		}

		@Override
		public final void sessionFinished(final ITestRunSession session)
		{
			this.listener.sessionFinished(session);
		}

		@Override
		public final void testCaseStarted(final ITestCaseElement testCaseElement)
		{
			this.listener.testCaseStarted(testCaseElement);
		}

		@Override
		public final void testCaseFinished(final ITestCaseElement testCaseElement)
		{
			this.listener.testCaseFinished(testCaseElement);
		}

		@Override
		public final boolean equals(final Object o)
		{
			return this == o || this.listener.equals(o);
		}

		@Override
		public final int hashCode()
		{
			return this.listener.hashCode();
		}

		@Override
		public final String toString()
		{
			return this.listener.toString();
		}
	}
	
	public final void add(final TestRunListener listener)
	{
		JUnitCore.addTestRunListener(new TestRunProxy(listener));
	}

	public final void remove(final TestRunListener listener)
	{
		JUnitCore.addTestRunListener(new TestRunProxy(listener));
	}
}
