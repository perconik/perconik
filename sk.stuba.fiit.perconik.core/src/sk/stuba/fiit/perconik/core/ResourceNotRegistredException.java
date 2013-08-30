package sk.stuba.fiit.perconik.core;

public class ResourceNotRegistredException extends IllegalStateException
{
	private static final long serialVersionUID = 5184848846807217597L;

	public ResourceNotRegistredException()
	{
		super();
	}

	public ResourceNotRegistredException(String message)
	{
		super(message);
	}

	public ResourceNotRegistredException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ResourceNotRegistredException(Throwable cause)
	{
		super(cause);
	}
}
