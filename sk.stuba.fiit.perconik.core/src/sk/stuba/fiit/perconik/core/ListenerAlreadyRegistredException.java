package sk.stuba.fiit.perconik.core;

public class ListenerAlreadyRegistredException extends IllegalStateException
{
	private static final long serialVersionUID = 6263277608857440160L;

	public ListenerAlreadyRegistredException()
	{
		super();
	}

	public ListenerAlreadyRegistredException(String message)
	{
		super(message);
	}

	public ListenerAlreadyRegistredException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ListenerAlreadyRegistredException(Throwable cause)
	{
		super(cause);
	}
}
