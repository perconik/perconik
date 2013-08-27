package sk.stuba.fiit.perconik.core;

public class NoRegistredResourceException extends RuntimeException
{
	private static final long serialVersionUID = -6389036154807251119L;

	public NoRegistredResourceException()
	{
		super();
	}

	public NoRegistredResourceException(String message)
	{
		super(message);
	}

	public NoRegistredResourceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public NoRegistredResourceException(Throwable cause)
	{
		super(cause);
	}
}
