package sk.stuba.fiit.perconik.core;

import javax.annotation.Nullable;

/**
 * Thrown when one of the core service classes tries to load in a resource
 * through its class or string name but no definition of the resource could
 * be found.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class ResourceNotFoundException extends IllegalStateException
{
	private static final long serialVersionUID = 0;

	/**
	 * Creates a new instance with no detail message.
	 */
	public ResourceNotFoundException()
	{
		super();
	}

	/**
	 * Creates a new instance with the given detail message.
	 */
	public ResourceNotFoundException(@Nullable String message)
	{
		super(message);
	}

	/**
	 * Creates a new instance with the given detail message and cause.
	 */
	public ResourceNotFoundException(@Nullable String message, @Nullable Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Creates a new instance with the given cause.
	 */
	public ResourceNotFoundException(@Nullable Throwable cause)
	{
		super(cause);
	}
}
