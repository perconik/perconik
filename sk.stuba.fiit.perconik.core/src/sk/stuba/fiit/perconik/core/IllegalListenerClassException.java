package sk.stuba.fiit.perconik.core;

public class IllegalListenerClassException extends IllegalArgumentException
{
	private static final long serialVersionUID = -4138698955719662456L;

	public IllegalListenerClassException()
	{
		super();
	}

	public IllegalListenerClassException(String message)
	{
		super(message);
	}

	public IllegalListenerClassException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public IllegalListenerClassException(Throwable cause)
	{
		super(cause);
	}
}
