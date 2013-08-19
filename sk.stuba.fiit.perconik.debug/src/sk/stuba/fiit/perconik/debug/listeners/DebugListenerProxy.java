package sk.stuba.fiit.perconik.debug.listeners;

import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.debug.DebugConsole;
import sk.stuba.fiit.perconik.debug.DebugListener;
import sk.stuba.fiit.perconik.debug.DebugRegistrableProxy;

public abstract class DebugListenerProxy<L extends Listener> extends DebugRegistrableProxy<L> implements DebugListener
{
	protected DebugListenerProxy(final L listener)
	{
		super(listener);
	}

	protected DebugListenerProxy(final L listener, final DebugConsole console)
	{
		super(listener, console);
	}
}
