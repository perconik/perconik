package sk.stuba.fiit.perconik.debug;

public abstract class DebugObjectProxy extends AbstractDebugObject
{
	protected DebugObjectProxy(final DebugConsole console)
	{
		super(console);
	}
	
	protected abstract Object delegate();

	@Override
	public final boolean equals(final Object o)
	{
		return this.delegate().equals(o);
	}

	@Override
	public final int hashCode()
	{
		return this.delegate().hashCode();
	}

	@Override
	public final String toString()
	{
		return this.delegate().toString();
	}
}
