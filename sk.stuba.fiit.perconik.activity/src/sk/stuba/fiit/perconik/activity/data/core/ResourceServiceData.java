package sk.stuba.fiit.perconik.activity.data.core;

import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.activity.data.base.NameableBaseData;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.resources.ResourceService;

public class ResourceServiceData extends NameableBaseData
{
	protected NameableBaseData provider;
	
	protected NameableBaseData manager;

	protected Set<String> names;
	
	protected SetMultimap<String, ResourceData> registrations;
	
	public ResourceServiceData()
	{
	}
	
	protected ResourceServiceData(ResourceService service)
	{
		super(service);
		
		if (service == null)
		{
			return;
		}
		
		this.setProvider(NameableBaseData.of(service.getResourceProvider()));
		this.setManager(NameableBaseData.of(service.getResourceManager()));
		this.setNames(service.getResourceProvider().names());
		
		SetMultimap<String, ResourceData> registrations = HashMultimap.create();
		
		for (Entry<Class<? extends Listener>, Resource<?>> entry: service.getResourceManager().registrations().entries())
		{
			registrations.put(entry.getKey().getName(), ResourceData.of(entry.getValue()));
		}
		
		this.setRegistrations(registrations);
	}
	
	public static ResourceServiceData of(ResourceService service)
	{
		return new ResourceServiceData(service);
	}

	public void setProvider(NameableBaseData provider)
	{
		this.provider = provider;
	}

	public void setManager(NameableBaseData manager)
	{
		this.manager = manager;
	}

	public void setNames(Set<String> names)
	{
		this.names = names;
	}

	public void setRegistrations(SetMultimap<String, ResourceData> registrations)
	{
		this.registrations = registrations;
	}

	public NameableBaseData getProvider()
	{
		return this.provider;
	}

	public NameableBaseData getManager()
	{
		return this.manager;
	}

	public SetMultimap<String, ResourceData> getRegistrations()
	{
		return this.registrations;
	}

	public Set<String> getNames()
	{
		return this.names;
	}
}
