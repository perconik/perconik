package sk.stuba.fiit.perconik.utilities;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

/**
 * Static utility methods pertaining to {@code Throwable} instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreThrowables
{
	private MoreThrowables()
	{
		throw new AssertionError();
	}

	public static final <T extends Throwable> T initializeCause(T throwable, @Nullable Throwable cause)
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
