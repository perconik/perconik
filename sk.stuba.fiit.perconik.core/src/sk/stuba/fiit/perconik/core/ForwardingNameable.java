package sk.stuba.fiit.perconik.core;

import com.google.common.collect.ForwardingObject;

/**
 * A nameable which forwards all its method calls to another nameable.
 * Subclasses should override one or more methods to modify the behavior
 * of the backing nameable as desired per the decorator pattern.
 * 
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object.
 * See {@link ForwardingObject} for more details.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingNameable extends ForwardingObject implements Nameable
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ForwardingNameable()
	{
	}

	@Override
	protected abstract Nameable delegate();

	public String getName()
	{
		return this.delegate().getName();
	}
}
