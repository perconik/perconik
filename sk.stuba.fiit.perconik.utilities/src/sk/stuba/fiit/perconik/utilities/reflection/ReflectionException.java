package sk.stuba.fiit.perconik.utilities.reflection;

public class ReflectionException extends RuntimeException
{
	private static final long serialVersionUID = 2500590677823567553L;

	public ReflectionException()
	{
		super();
	}

	public ReflectionException(String message)
	{
		super(message);
	}

	public ReflectionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ReflectionException(Throwable cause)
	{
		super(cause);
	}
}
