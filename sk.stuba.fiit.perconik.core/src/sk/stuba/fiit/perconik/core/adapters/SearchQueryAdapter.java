package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.search.ui.ISearchQuery;
import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;

/**
 * An abstract adapter class for a {@code SearchQueryListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code SearchQueryListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see SearchQueryListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class SearchQueryAdapter extends Adapter implements SearchQueryListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected SearchQueryAdapter()
	{
	}

	public void queryAdded(ISearchQuery query)
	{
	}

	public void queryRemoved(ISearchQuery query)
	{
	}

	public void queryStarting(ISearchQuery query)
	{
	}

	public void queryFinished(ISearchQuery query)
	{
	}
}
