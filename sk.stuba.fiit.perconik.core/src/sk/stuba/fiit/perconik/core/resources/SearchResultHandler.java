package sk.stuba.fiit.perconik.core.resources;

import sk.stuba.fiit.perconik.core.listeners.SearchResultListener;
import sk.stuba.fiit.perconik.core.resources.SearchResultHook.Support;

enum SearchResultHandler implements Handler<SearchResultListener> {
  INSTANCE;

  private final Support support = new Support();

  public void register(final SearchResultListener listener) {
    this.support.hook(DefaultResources.getSearchQueryResource(), listener);
  }

  public void unregister(final SearchResultListener listener) {
    this.support.unhook(DefaultResources.getSearchQueryResource(), listener);
  }
}
