package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when one of the core service classes tries to instantiate
 * a listener through its class but the instantiation process fails.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class ListenerInstantiationException extends RuntimeException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public ListenerInstantiationException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public ListenerInstantiationException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public ListenerInstantiationException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public ListenerInstantiationException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
