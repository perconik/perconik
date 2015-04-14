package sk.stuba.fiit.perconik.activity.listeners.search;

import org.eclipse.search.ui.ISearchQuery;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.search.SearchQuerySerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;

import static sk.stuba.fiit.perconik.activity.listeners.search.SearchQueryListener.Action.ADD;
import static sk.stuba.fiit.perconik.activity.listeners.search.SearchQueryListener.Action.FINISH;
import static sk.stuba.fiit.perconik.activity.listeners.search.SearchQueryListener.Action.REMOVE;
import static sk.stuba.fiit.perconik.activity.listeners.search.SearchQueryListener.Action.START;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.2.alpha")
public final class SearchQueryListener extends ActivityListener implements sk.stuba.fiit.perconik.core.listeners.SearchQueryListener {
  public SearchQueryListener() {}

  enum Action implements ActivityListener.Action {
    ADD,

    REMOVE,

    START,

    FINISH;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "search", "query", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  static Event build(final long time, final Action action, final ISearchQuery query) {
    Event data = LocalEvent.of(time, action.getName());

    data.put(key("query"), new SearchQuerySerializer(TREE).serialize(query));

    return data;
  }

  void process(final long time, final Action action, final ISearchQuery query) {
    this.send(action.getPath(), build(time, action, query));
  }

  void execute(final long time, final Action action, final ISearchQuery query) {
    this.execute(new Runnable() {
      public void run() {
        process(time, action, query);
      }
    });
  }

  public void queryAdded(final ISearchQuery query) {
    long time = currentTime();

    this.execute(time, ADD, query);
  }

  public void queryRemoved(final ISearchQuery query) {
    long time = currentTime();

    this.execute(time, REMOVE, query);
  }

  public void queryStarting(final ISearchQuery query) {
    long time = currentTime();

    this.execute(time, START, query);
  }

  public void queryFinished(final ISearchQuery query) {
    long time = currentTime();

    this.execute(time, FINISH, query);
  }
}
