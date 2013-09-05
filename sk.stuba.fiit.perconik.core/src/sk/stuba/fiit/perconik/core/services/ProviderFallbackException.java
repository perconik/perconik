package sk.stuba.fiit.perconik.core.services;

public class ProviderFallbackException extends RuntimeException
{
	private static final long serialVersionUID = 4422720325649677707L;

	private final Throwable parent;
	
	public ProviderFallbackException(Throwable cause, Throwable parent)
	{
		super(cause);
		
		this.parent = parent;
	}

	public final Throwable getParent()
	{
		return this.parent;
	}
}
