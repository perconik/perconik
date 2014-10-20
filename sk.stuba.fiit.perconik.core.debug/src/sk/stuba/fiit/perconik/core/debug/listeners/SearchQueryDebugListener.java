package sk.stuba.fiit.perconik.core.debug.listeners;

import org.eclipse.search.ui.ISearchQuery;

import sk.stuba.fiit.perconik.core.debug.AbstractDebugListener;
import sk.stuba.fiit.perconik.core.debug.Debug;
import sk.stuba.fiit.perconik.core.debug.runtime.DebugConsole;
import sk.stuba.fiit.perconik.core.listeners.SearchQueryListener;

public final class SearchQueryDebugListener extends AbstractDebugListener implements SearchQueryListener {
  public SearchQueryDebugListener() {}

  public SearchQueryDebugListener(final DebugConsole console) {
    super(console);
  }

  public void queryAdded(final ISearchQuery query) {
    this.printHeader("Search query added");
    this.printSearchQuery(query);
  }

  public void queryRemoved(final ISearchQuery query) {
    this.printHeader("Search query removed");
    this.printSearchQuery(query);
  }

  public void queryStarting(final ISearchQuery query) {
    this.printHeader("Search query started");
    this.printSearchQuery(query);
  }

  public void queryFinished(final ISearchQuery query) {
    this.printHeader("Search query finished");
    this.printSearchQuery(query);
  }

  private void printSearchQuery(final ISearchQuery query) {
    this.put(Debug.dumpSearchQuery(query));
  }
}
