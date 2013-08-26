package sk.stuba.fiit.perconik.debug;

import sk.stuba.fiit.perconik.core.Nameable;
import sk.stuba.fiit.perconik.debug.runtime.DebugConsole;

public abstract class DebugNameableProxy extends DebugObjectProxy implements Nameable
{
	protected DebugNameableProxy(final DebugConsole console)
	{
		super(console);
	}

	@Override
	protected abstract Nameable delegate();
	
	public final String getName()
	{
		return this.delegate().getName();
	}
}
