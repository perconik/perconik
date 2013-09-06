package sk.stuba.fiit.perconik.utilities;

import com.google.common.base.Optional;

public final class MoreThrowables
{
	private MoreThrowables()
	{
		throw new AssertionError();
	}

	public static final <T extends Throwable> T initializeCause(T throwable, Throwable cause)
	{
		throwable.initCause(cause);
		
		return throwable;
	}
	
	public static final <T extends Throwable> T initializeCause(T throwable, Optional<? extends Throwable> cause)
	{
		return initializeCause(throwable, cause.orNull());
	}
	
	public static final <T extends Throwable> T initializeSuppressor(T suppressor, Throwable suppressed)
	{
		suppressor.addSuppressed(suppressed);
		
		return suppressor;
	}
	
	public static final <T extends Throwable> T initializeSuppressor(T suppressor, Iterable<? extends Throwable> suppressions)
	{
		addSuppressed(suppressor, suppressions);
		
		return suppressor;
	}
	
	public static final void addSuppressed(Throwable throwable, Optional<? extends Throwable> suppressed)
	{
		if (suppressed.isPresent())
		{
			throwable.addSuppressed(suppressed.get());
		}
	}
	
	public static final void addSuppressed(Throwable throwable, Iterable<? extends Throwable> suppressions)
	{
		for (Throwable suppressed: suppressions)
		{
			throwable.addSuppressed(suppressed);
		}
	}
}
