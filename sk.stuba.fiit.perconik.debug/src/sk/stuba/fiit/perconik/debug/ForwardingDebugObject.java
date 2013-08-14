package sk.stuba.fiit.perconik.debug;

import sk.stuba.fiit.perconik.eclipse.core.runtime.PluginConsole;

public abstract class ForwardingDebugObject extends DebugObject
{
	protected ForwardingDebugObject()
	{
	}
	
	protected ForwardingDebugObject(final PluginConsole console)
	{
		super(console);
	}
	
	protected abstract Object delegate();
	
	@Override
	public final String toString()
	{
		return this.delegate().toString();
	}
}
