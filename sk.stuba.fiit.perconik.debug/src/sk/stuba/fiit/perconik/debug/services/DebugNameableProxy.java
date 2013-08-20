package sk.stuba.fiit.perconik.debug.services;

import sk.stuba.fiit.perconik.core.Nameable;
import sk.stuba.fiit.perconik.debug.DebugConsole;
import sk.stuba.fiit.perconik.debug.DebugObjectProxy;

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
