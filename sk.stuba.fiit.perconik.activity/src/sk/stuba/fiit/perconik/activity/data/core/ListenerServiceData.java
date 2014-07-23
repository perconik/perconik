package sk.stuba.fiit.perconik.activity.data.core;

import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import sk.stuba.fiit.perconik.activity.data.base.ClassData;
import sk.stuba.fiit.perconik.activity.data.base.NameableBaseData;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.core.Resource;
import sk.stuba.fiit.perconik.core.services.listeners.ListenerService;

public class ListenerServiceData extends NameableBaseData
{
	protected NameableBaseData provider;
	
	protected NameableBaseData manager;

	protected Set<Class<? extends Listener>> classes;
	
	protected SetMultimap<String, ListenerData> registrations;
	
	public ListenerServiceData()
	{
	}
	
	protected ListenerServiceData(ListenerService service)
	{
		super(service);
		
		if (service == null)
		{
			return;
		}
		
		this.setImplementation(ClassData.of(service.getClass()));
		this.setProvider(NameableBaseData.of(service.getListenerProvider()));
		this.setManager(NameableBaseData.of(service.getListenerManager()));
		this.setClasses(service.getListenerProvider().classes());
		
		SetMultimap<String, ListenerData> registrations = HashMultimap.create();
		
		for (Entry<Resource<?>, Listener> entry: service.getListenerManager().registrations().entries())
		{
			registrations.put(entry.getKey().getName(), ListenerData.of(entry.getValue()));
		}
		
		this.setRegistrations(registrations);
	}
	
	public static ListenerServiceData of(ListenerService service)
	{
		return new ListenerServiceData(service);
	}

	public void setProvider(NameableBaseData provider)
	{
		this.provider = provider;
	}

	public void setManager(NameableBaseData manager)
	{
		this.manager = manager;
	}

	public void setClasses(Set<Class<? extends Listener>> classes)
	{
		this.classes = classes;
	}

	public void setRegistrations(SetMultimap<String, ListenerData> registrations)
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

	public SetMultimap<String, ListenerData> getRegistrations()
	{
		return this.registrations;
	}

	public Set<Class<? extends Listener>> getClasses()
	{
		return this.classes;
	}	
}
