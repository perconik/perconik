package sk.stuba.fiit.perconik.debug;

import sk.stuba.fiit.perconik.core.Nameable;

public class DebugNameableProxy<N extends Nameable> extends DebugObjectProxy<N> implements Nameable
{
	protected DebugNameableProxy(final N nameable)
	{
		super(nameable);
	}

	protected DebugNameableProxy(final N nameable, final DebugConsole console)
	{
		super(nameable, console);
	}

	public final String getName()
	{
		return this.delegate().getName();
	}
}
