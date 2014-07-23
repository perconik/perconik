package sk.stuba.fiit.perconik.core;

/**
 * A listener which forwards all its method calls to another listener.
 * Subclasses should override one or more methods to modify the behavior
 * of the backing listener as desired per the decorator pattern.
 * 
 * <p>Note that this class does <i>not</i> forward the {@code equals}
 * and {@code hashCode} methods through to the backing object. See
 * {@link com.google.common.collect.ForwardingObject ForwardingObject}
 * for more details.
 * 
 * @see Listener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class ForwardingListener extends ForwardingRegistrable implements Listener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected ForwardingListener()
	{
	}
	
	@Override
	protected abstract Listener delegate();
}
