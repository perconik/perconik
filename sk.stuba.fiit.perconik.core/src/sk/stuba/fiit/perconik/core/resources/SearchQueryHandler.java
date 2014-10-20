package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.search.ui.NewSearchUI;

import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;

enum SearchQueryHandler implements Handler<SearchQueryListener> {
  INSTANCE;

  public void register(final SearchQueryListener listener) {
    NewSearchUI.addQueryListener(listener);
  }

  public void unregister(final SearchQueryListener listener) {
    NewSearchUI.removeQueryListener(listener);
  }
}
