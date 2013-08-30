package sk.stuba.fiit.perconik.core;

public class UnsupportedResourceException extends UnsupportedOperationException
{
	private static final long serialVersionUID = -6555133329919320483L;

	public UnsupportedResourceException()
	{
		super();
	}

	public UnsupportedResourceException(String message)
	{
		super(message);
	}

	public UnsupportedResourceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public UnsupportedResourceException(Throwable cause)
	{
		super(cause);
	}
}
