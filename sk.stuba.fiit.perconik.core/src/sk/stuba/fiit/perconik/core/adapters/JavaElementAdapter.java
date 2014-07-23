package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jdt.core.ElementChangedEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.AbstractJavaElementListener;
import sk.stuba.fiit.perconik.core.listeners.JavaElementListener;

/**
 * An abstract adapter class for a {@code JavaElementListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code JavaElementListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see JavaElementListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class JavaElementAdapter extends AbstractJavaElementListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected JavaElementAdapter()
	{
	}

	public void elementChanged(ElementChangedEvent event)
	{
	}
}
