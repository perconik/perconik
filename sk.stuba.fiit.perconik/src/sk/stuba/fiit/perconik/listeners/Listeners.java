package sk.stuba.fiit.perconik.listeners;

public final class Listeners
{
	private Listeners()
	{
		throw new AssertionError();
	}
	
	public static final void register(final LaunchListener listener)
	{
		Resources.getLaunchResource().register(listener);
	}
	
	public static final void unregister(final LaunchListener listener)
	{
		Resources.getLaunchResource().unregister(listener);
	}
	
	public static final void unregisterAll()
	{
		for (Resource<?> resource: Resources.getResources())
		{
			resource.unregisterAll(Listener.class);
		}
	}
}
