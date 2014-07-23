package sk.stuba.fiit.perconik.core;

import com.google.common.collect.ForwardingObject;

/**
 * A registrable which forwards all its method calls to another registrable.
 * Subclasses should override one or more methods to modify the behavior
 * of the backing registrable as desired per the decorator pattern.
 * 
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object.
 * See {@link ForwardingObject} for more details.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingRegistrable extends ForwardingObject implements Registrable
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ForwardingRegistrable()
	{
	}
	
	@Override
	protected abstract Registrable delegate();

	public void preRegister()
	{
		this.delegate().preRegister();
	}

	public void postRegister()
	{
		this.delegate().postRegister();
	}

	public void preUnregister()
	{
		this.delegate().preUnregister();
	}

	public void postUnregister()
	{
		this.delegate().postUnregister();
	}
}
