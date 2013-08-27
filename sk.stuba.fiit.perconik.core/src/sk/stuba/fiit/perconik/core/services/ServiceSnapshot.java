package sk.stuba.fiit.perconik.core.services;

public final class ServiceSnapshot
{
	ServiceGroup<Service> services;
	
	private ServiceSnapshot()
	{
		this.services = new ServiceGroup<>(Services.inStartOrder());
	}

	public static final ServiceSnapshot take()
	{
		return new ServiceSnapshot();
	}
	
	public final ServiceGroup<Service> services()
	{
		return this.services;
	}
	
	public final ServiceGroup<Service> servicesInStartOrder()
	{
		return this.services;
	}

	public final ServiceGroup<Service> servicesInStopOrder()
	{
		return this.services.reverse();
	}
}
