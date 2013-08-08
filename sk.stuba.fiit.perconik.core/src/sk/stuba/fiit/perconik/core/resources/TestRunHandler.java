package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jdt.junit.JUnitCore;
import org.eclipse.jdt.junit.model.ITestCaseElement;
import org.eclipse.jdt.junit.model.ITestRunSession;
import sk.stuba.fiit.perconik.core.listeners.TestRunListener;
import com.google.common.base.Preconditions;

enum TestRunHandler implements Handler<TestRunListener>
{
	INSTANCE;
	
	private static final class TestRunProxy extends org.eclipse.jdt.junit.TestRunListener implements TestRunListener
	{
		private final TestRunListener listener;
		
		public TestRunProxy(final TestRunListener listener)
		{
			this.listener = Preconditions.checkNotNull(listener);
		}

		public final void preRegister()
		{
			this.listener.preRegister();
		}

		public final void postRegister()
		{
			this.listener.postRegister();
		}

		public final void preUnregister()
		{
			this.listener.preUnregister();
		}

		public final void postUnregister()
		{
			this.listener.postUnregister();
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
			return this.listener.equals(o);
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
	
	public final void register(final TestRunListener listener)
	{
		JUnitCore.addTestRunListener(new TestRunProxy(listener));
	}

	public final void unregister(final TestRunListener listener)
	{
		JUnitCore.addTestRunListener(new TestRunProxy(listener));
	}
}
