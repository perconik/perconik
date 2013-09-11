package sk.stuba.fiit.perconik.core.services;

import com.google.common.util.concurrent.Service.State;

/**
 * An abstract adapter class for a {@code ServiceListener} interface.
 * The methods in this class are empty. This class exists as convenience
 * for creating service listener objects.
 * 
 * @see ServiceListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ServiceAdapter implements ServiceListener 
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ServiceAdapter()
	{
	}

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
