package sk.stuba.fiit.perconik.debug;

import javax.annotation.Nullable;

public abstract class DebugObjectProxy extends AbstractDebugObject
{
	protected DebugObjectProxy(final DebugConsole console)
	{
		super(console);
	}
	
	protected abstract Object delegate();

	@Override
	public final boolean equals(@Nullable final Object o)
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
