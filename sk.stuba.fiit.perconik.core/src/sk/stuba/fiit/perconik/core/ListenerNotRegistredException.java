package sk.stuba.fiit.perconik.core;

public class ListenerNotRegistredException extends IllegalStateException
{
	private static final long serialVersionUID = 1962632699561484975L;

	public ListenerNotRegistredException()
	{
		super();
	}

	public ListenerNotRegistredException(String message)
	{
		super(message);
	}

	public ListenerNotRegistredException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ListenerNotRegistredException(Throwable cause)
	{
		super(cause);
	}
}
