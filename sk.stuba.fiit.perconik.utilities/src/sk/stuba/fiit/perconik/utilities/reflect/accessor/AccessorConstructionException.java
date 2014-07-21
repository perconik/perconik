package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import javax.annotation.Nullable;

import sk.stuba.fiit.perconik.utilities.reflect.ReflectionException;

public class AccessorConstructionException extends ReflectionException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public AccessorConstructionException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public AccessorConstructionException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public AccessorConstructionException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public AccessorConstructionException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
