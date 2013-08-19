package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.Service;
import com.google.common.util.concurrent.Service.State;

public abstract class ServiceAdapter implements ServiceListener 
{
	protected ServiceAdapter()
	{
	}
	
	public abstract Service service();

	public void starting()
	{
	}

	public void running()
	{
	}

	public void stopping(State from)
	{
	}

	public void terminated(State from)
	{
	}

	public void failed(State from, Throwable failure)
	{
	}
}
