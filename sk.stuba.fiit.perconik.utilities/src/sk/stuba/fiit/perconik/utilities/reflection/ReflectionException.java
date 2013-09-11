package sk.stuba.fiit.perconik.utilities.reflection;

import javax.annotation.Nullable;

/**
 * Unchecked variant of {@link java.lang.ReflectiveOperationException}.
 *
 * @author Pavol Zbell
 */
public class ReflectionException extends RuntimeException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public ReflectionException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public ReflectionException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public ReflectionException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public ReflectionException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
