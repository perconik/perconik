package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when listener registration fails.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class ListenerRegistrationException extends IllegalStateException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public ListenerRegistrationException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public ListenerRegistrationException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public ListenerRegistrationException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public ListenerRegistrationException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
