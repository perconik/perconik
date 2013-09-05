package sk.stuba.fiit.perconik.utilities;

public class FallbackException extends RuntimeException
{
	private static final long serialVersionUID = 7148042480911599954L;

	private final Throwable parent;
	
	public FallbackException(Throwable parent, Throwable cause)
	{
		super(cause);
		
		this.parent = parent;
	}

	public final Throwable getParent()
	{
		return this.parent;
	}
}
