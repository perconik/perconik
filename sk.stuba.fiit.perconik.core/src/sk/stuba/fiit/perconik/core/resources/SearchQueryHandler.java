package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;

enum SearchQueryHandler implements Handler<SearchQueryListener>
{
	INSTANCE;
	
	public final void register(final SearchQueryListener listener)
	{
	}

	public final void unregister(final SearchQueryListener listener)
	{
	}
}
