package sk.stuba.fiit.perconik.listeners;

class KnownResources
{
	static final Resource<LaunchListener> launch;
	
	static
	{
		Resources.register(LaunchListener.class, launch = Resources.create(LaunchHandler.INSTANCE));
	}
	
	private KnownResources()
	{
		throw new AssertionError();
	}
}
