package sk.stuba.fiit.perconik.core.debug;

import javax.annotation.Nullable;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;

public abstract class DebugObjectProxy extends AbstractDebugObject
{
	protected DebugObjectProxy(final DebugConsole console)
	{
		super(console);
	}
	
	public abstract Object delegate();

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
