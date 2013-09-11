package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.debug.core.ILaunch;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.LaunchListener;

/**
 * An abstract adapter class for a {@code LaunchListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code LaunchListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see LaunchListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class LaunchAdapter extends Adapter implements LaunchListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected LaunchAdapter()
	{
	}

	public void launchAdded(ILaunch launch)
	{
	}

	public void launchRemoved(ILaunch launch)
	{
	}

	public void launchChanged(ILaunch launch)
	{
	}
}
