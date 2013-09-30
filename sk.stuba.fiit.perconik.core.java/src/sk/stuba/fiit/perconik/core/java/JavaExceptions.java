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
	
	public static final RuntimeException propagate(final CoreException e)
	{
		if (e instanceof JavaModelException)
		{
			throw new JavaException(e);
		}
		
		return CoreExceptions.propagate(e);
	}

	static final <T> T handle(final Exception e)
	{
		if (e instanceof JavaModelException)
		{
			throw new JavaException(e);
		}
		
		if (e instanceof NullPointerException)
		{
			return null;
		}

		throw Throwables.propagate(e);
	}
}
