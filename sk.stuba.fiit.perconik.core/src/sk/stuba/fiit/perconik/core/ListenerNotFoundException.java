package sk.stuba.fiit.perconik.core;

public class ListenerNotFoundException extends IllegalStateException
{
	private static final long serialVersionUID = 2341641908884736360L;

	public ListenerNotFoundException()
	{
		super();
	}

	public ListenerNotFoundException(String message)
	{
		super(message);
	}

	public ListenerNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ListenerNotFoundException(Throwable cause)
	{
		super(cause);
	}
}
