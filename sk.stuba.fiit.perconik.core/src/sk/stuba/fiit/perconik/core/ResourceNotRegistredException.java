package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when a provided resource is not registered but requested.
 * 
 * <p>This exception may be also thrown when an attempt is made to unregister
 * a listener that is not registered and the core listener service decided
 * to inform about that.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class ResourceNotRegistredException extends IllegalStateException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public ResourceNotRegistredException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public ResourceNotRegistredException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public ResourceNotRegistredException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public ResourceNotRegistredException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
