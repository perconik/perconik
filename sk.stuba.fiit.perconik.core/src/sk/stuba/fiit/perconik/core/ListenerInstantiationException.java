package sk.stuba.fiit.perconik.core;

public class ListenerInstantiationException extends RuntimeException
{
	private static final long serialVersionUID = 6252605578850925601L;

	public ListenerInstantiationException()
	{
		super();
	}

	public ListenerInstantiationException(String message)
	{
		super(message);
	}

	public ListenerInstantiationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ListenerInstantiationException(Throwable cause)
	{
		super(cause);
	}
}
