package sk.stuba.fiit.perconik.core.persistence;

import java.io.InvalidObjectException;

public class InvalidResourceException extends InvalidObjectException
{
	private static final long serialVersionUID = 2359400964519339337L;

	public InvalidResourceException()
	{
		super(null);
		
		this.fillInStackTrace();
	}

	public InvalidResourceException(String message)
	{
		super(message);
	}

	public InvalidResourceException(String message, Throwable cause)
	{
		super(message);
		
		this.initCause(cause);
	}

	public InvalidResourceException(Throwable cause)
	{
		this(null, cause);
	}
}
