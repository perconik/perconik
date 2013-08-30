package sk.stuba.fiit.perconik.core;

public class ResourceNotFoundException extends IllegalStateException
{
	private static final long serialVersionUID = -1612301772719673206L;

	public ResourceNotFoundException()
	{
		super();
	}

	public ResourceNotFoundException(String message)
	{
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ResourceNotFoundException(Throwable cause)
	{
		super(cause);
	}
}
