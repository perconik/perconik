package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.search.ui.SearchResultEvent;
import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.SearchResultListener;

public final class SearchResultDebugListener extends AbstractDebugListener implements SearchResultListener
{
	public SearchResultDebugListener()
	{
	}
	
	public SearchResultDebugListener(final DebugConsole console)
	{
		super(console);
	}

	public final void searchResultChanged(final SearchResultEvent event)
	{
		this.printHeader("Search result changed");
		this.printSearchResultEvent(event);
	}

	private final void printSearchResultEvent(final SearchResultEvent event)
	{
		this.put(Debug.dumpSearchResultEvent(event));
	}
}
