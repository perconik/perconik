package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.SearchResultListener;

enum SearchResultHandler implements Handler<SearchResultListener>
{
	INSTANCE;
	
	public final void register(final SearchResultListener listener)
	{
	}

	public final void unregister(final SearchResultListener listener)
	{
	}
}
