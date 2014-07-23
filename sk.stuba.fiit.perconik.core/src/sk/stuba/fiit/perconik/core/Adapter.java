package sk.stuba.fiit.perconik.core;

/**
 * An abstract adapter class for a {@link Listener} interface.
 * The methods in this class and its adapter subclasses are empty.
 * This class exists as convenience for creating listener objects.
 * 
 * <p>Extend a more concrete subclass of this class to create
 * listener and override the methods for the events of interest.
 * 
 * @see Listener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class Adapter extends AbstractRegistrable implements Listener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected Adapter()
	{
	}
}
