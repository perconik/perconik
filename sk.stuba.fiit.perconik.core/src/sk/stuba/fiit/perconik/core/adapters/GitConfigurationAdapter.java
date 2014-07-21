package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.jgit.events.ConfigChangedEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.GitConfigurationListener;

/**
 * An abstract adapter class for a {@code GitConfigurationListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code GitConfigurationListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see GitConfigurationListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class GitConfigurationAdapter extends Adapter implements GitConfigurationListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected GitConfigurationAdapter()
	{
	}
	
	public void onConfigChanged(ConfigChangedEvent event)
	{
	}
}
