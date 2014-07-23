package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.utilities.reflect.ReflectionException;

public class AccessorInvocationException extends ReflectionException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public AccessorInvocationException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public AccessorInvocationException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public AccessorInvocationException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public AccessorInvocationException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
