package sk.stuba.fiit.perconik.core;

public class ResourceAlreadyRegistredException extends IllegalStateException
{
	private static final long serialVersionUID = -6682103571457711242L;

	public ResourceAlreadyRegistredException()
	{
		super();
	}

	public ResourceAlreadyRegistredException(String message)
	{
		super(message);
	}

	public ResourceAlreadyRegistredException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ResourceAlreadyRegistredException(Throwable cause)
	{
		super(cause);
	}
}
