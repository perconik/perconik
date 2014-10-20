package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.NewSearchUI;

import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;
import sk.stuba.fiit.perconik.core.listeners.SearchResultListener;

final class SearchResultHook extends InternalHook<ISearchQuery, SearchResultListener> implements SearchQueryListener {
  SearchResultHook(final SearchResultListener listener) {
    super(new QueryHandler(listener));
  }

  static final class Support extends AbstractHookSupport<SearchResultHook, ISearchQuery, SearchResultListener> {
    public Hook<ISearchQuery, SearchResultListener> create(final SearchResultListener listener) {
      return new SearchResultHook(listener);
    }
  }

  private static final class QueryHandler extends InternalHandler<ISearchQuery, SearchResultListener> {
    QueryHandler(final SearchResultListener listener) {
      super(ISearchQuery.class, listener);
    }

    public void register(final ISearchQuery query) {
      query.getSearchResult().addListener(this.listener);
    }

    public void unregister(final ISearchQuery query) {
      query.getSearchResult().removeListener(this.listener);
    }
  }

  @Override
  void preRegisterInternal() {
    for (ISearchQuery query: NewSearchUI.getQueries()) {
      this.add(query);
    }
  }

  public void queryAdded(final ISearchQuery query) {
    this.add(query);
  }

  public void queryRemoved(final ISearchQuery query) {
    this.remove(query);
  }

  public void queryStarting(final ISearchQuery query) {}

  public void queryFinished(final ISearchQuery query) {}
}
