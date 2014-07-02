package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.CoreException;
import com.google.common.base.Throwables;

public final class CoreExceptions
{
	private CoreExceptions()
	{
		throw new AssertionError();
	}
	
	public static final RuntimeCoreException propagate(final CoreException failure)
	{
		Throwables.propagateIfPossible(failure);
		
		throw new RuntimeCoreException(failure);
	}
}
