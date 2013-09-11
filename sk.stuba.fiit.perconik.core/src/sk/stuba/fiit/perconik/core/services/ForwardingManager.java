package sk.stuba.fiit.perconik.core.services;

import sk.stuba.fiit.perconik.core.ForwardingNameable;

/**
 * A manager which forwards all its method calls to another manager.
 * Subclasses should override one or more methods to modify the behavior
 * of the backing manager as desired per the decorator pattern.
 * 
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object.
 * See {@code ForwardingObject} for more details.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingManager extends ForwardingNameable implements Manager
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ForwardingManager()
	{
	}

	@Override
	protected abstract Manager delegate();
}
