package sk.stuba.fiit.perconik.activity.data;

import javax.annotation.Nullable;

public class DataException extends RuntimeException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public DataException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public DataException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public DataException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public DataException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
