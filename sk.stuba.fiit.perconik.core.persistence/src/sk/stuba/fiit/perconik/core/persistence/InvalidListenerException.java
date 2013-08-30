package sk.stuba.fiit.perconik.core.persistence;

import java.io.InvalidObjectException;

public class InvalidListenerException extends InvalidObjectException
{
	private static final long serialVersionUID = 52193572645439983L;

	public InvalidListenerException()
	{
		super(null);
		
		this.fillInStackTrace();
	}

	public InvalidListenerException(String message)
	{
		super(message);
	}

	public InvalidListenerException(String message, Throwable cause)
	{
		super(message);
		
		this.initCause(cause);
	}

	public InvalidListenerException(Throwable cause)
	{
		this(null, cause);
	}
}
