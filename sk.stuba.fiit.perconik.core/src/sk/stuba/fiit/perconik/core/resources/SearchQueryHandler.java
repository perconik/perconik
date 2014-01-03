package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.search.ui.NewSearchUI;
import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;

enum SearchQueryHandler implements Handler<SearchQueryListener>
{
	INSTANCE;
	
	public final void register(final SearchQueryListener listener)
	{
		NewSearchUI.addQueryListener(listener);
	}

	public final void unregister(final SearchQueryListener listener)
	{
		NewSearchUI.removeQueryListener(listener);
	}
}
