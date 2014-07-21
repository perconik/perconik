package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.search.ui.SearchResultEvent;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.SearchResultListener;

/**
 * An abstract adapter class for a {@code SearchResultListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code SearchResultListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see SearchResultListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class SearchResultAdapter extends Adapter implements SearchResultListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected SearchResultAdapter()
	{
	}

	public void searchResultChanged(SearchResultEvent event)
	{
	}
}
