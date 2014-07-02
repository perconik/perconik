package sk.stuba.fiit.perconik.core.java;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions;
import com.google.common.base.Throwables;

public final class JavaExceptions
{
	private JavaExceptions()
	{
		throw new AssertionError();
	}
	
	public static final RuntimeException propagate(final CoreException failure)
	{
		if (failure instanceof JavaModelException)
		{
			throw new JavaException(failure);
		}
		
		return CoreExceptions.propagate(failure);
	}

	static final <T> T handle(final Exception failure)
	{
		if (failure instanceof JavaModelException)
		{
			throw new JavaException(failure);
		}
		
		if (failure instanceof NullPointerException)
		{
			return null;
		}

		throw Throwables.propagate(failure);
	}
}
