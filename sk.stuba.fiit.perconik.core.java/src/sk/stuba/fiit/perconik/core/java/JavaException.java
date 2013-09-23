package sk.stuba.fiit.perconik.core.java;

import javax.annotation.Nullable;

public final class JavaException extends RuntimeException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public JavaException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public JavaException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public JavaException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public JavaException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
