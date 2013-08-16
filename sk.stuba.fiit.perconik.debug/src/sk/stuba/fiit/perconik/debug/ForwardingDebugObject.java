package sk.stuba.fiit.perconik.debug;

public abstract class ForwardingDebugObject extends DebugObject
{
	protected ForwardingDebugObject()
	{
	}
	
	protected ForwardingDebugObject(final DebugConsole console)
	{
		super(console);
	}
	
	protected abstract Object delegate();
	
	@Override
	public String toString()
	{
		return this.delegate().toString();
	}
}
