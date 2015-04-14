package sk.stuba.fiit.perconik.activity.listeners.search;

import org.eclipse.search.ui.SearchResultEvent;
import org.eclipse.search.ui.text.FilterUpdateEvent;
import org.eclipse.search.ui.text.MatchEvent;
import org.eclipse.search.ui.text.RemoveAllEvent;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.activity.events.LocalEvent;
import sk.stuba.fiit.perconik.activity.listeners.ActivityListener;
import sk.stuba.fiit.perconik.activity.serializers.ObjectIdentitySerializer;
import sk.stuba.fiit.perconik.activity.serializers.search.SearchResultSerializer;
import sk.stuba.fiit.perconik.core.annotations.Version;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.eclipse.search.ui.text.MatchEventKind;

import static sk.stuba.fiit.perconik.activity.listeners.search.SearchResultListener.Action.ADD;
import static sk.stuba.fiit.perconik.activity.listeners.search.SearchResultListener.Action.FILTER;
import static sk.stuba.fiit.perconik.activity.listeners.search.SearchResultListener.Action.REMOVE;
import static sk.stuba.fiit.perconik.activity.listeners.search.SearchResultListener.Action.REMOVE_ALL;
import static sk.stuba.fiit.perconik.activity.serializers.ConfigurableSerializer.StandardOption.TREE;
import static sk.stuba.fiit.perconik.activity.serializers.Serializations.identifyObject;
import static sk.stuba.fiit.perconik.data.content.StructuredContents.key;

/**
 * TODO
 *
 * @author Pavol Zbell
 * @since 1.0
 */
@Version("0.0.1.alpha")
public final class SearchResultListener extends ActivityListener implements sk.stuba.fiit.perconik.core.listeners.SearchResultListener {
  public SearchResultListener() {}

  enum Action implements ActivityListener.Action {
    ADD,

    REMOVE,

    REMOVE_ALL,

    FILTER;

    private final String name;

    private final String path;

    private Action() {
      this.name = actionName("eclipse", "search", "result", this);
      this.path = actionPath(this.name);
    }

    public String getName() {
      return this.name;
    }

    public String getPath() {
      return this.path;
    }
  }

  private static void putEvent(final StructuredContent content, final SearchResultEvent event) {
    content.put(key("source"), identifyObject(event.getSource()));

    content.put(key("result"), new SearchResultSerializer(TREE).serialize(event.getSearchResult()));
  }

  static Event build(final long time, final Action action, final MatchEvent event) {
    Event data = LocalEvent.of(time, action.getName());

    putEvent(data, event);

    data.put(key("matches"), new ObjectIdentitySerializer().serialize(event.getMatches()));

    return data;
  }

  static Event build(final long time, final Action action, final FilterUpdateEvent event) {
    Event data = LocalEvent.of(time, action.getName());

    putEvent(data, event);

    data.put(key("filters", "active"), new ObjectIdentitySerializer().serialize(event.getActiveFilters()));
    data.put(key("matches", "updated"), new ObjectIdentitySerializer().serialize(event.getUpdatedMatches()));

    return data;
  }

  static Event build(final long time, final Action action, final RemoveAllEvent event) {
    Event data = LocalEvent.of(time, action.getName());

    putEvent(data, event);

    return data;
  }

  void process(final long time, final SearchResultEvent event) {
    if (event instanceof MatchEvent) {
      Action action;

      switch (MatchEventKind.valueOf(((MatchEvent) event).getKind())) {
        case ADDED:
          action = ADD;
          break;

        case REMOVED:
          action = REMOVE;
          break;

        default:
          return;
      }

      this.send(action.getPath(), build(time, action, (MatchEvent) event));
    } else if (event instanceof FilterUpdateEvent) {
      this.send(FILTER.getPath(), build(time, FILTER, (FilterUpdateEvent) event));
    } else if (event instanceof RemoveAllEvent) {
      this.send(REMOVE_ALL.getPath(), build(time, REMOVE_ALL, (RemoveAllEvent) event));
    }
  }

  void execute(final long time, final SearchResultEvent event) {
    this.execute(new Runnable() {
      public void run() {
        process(time, event);
      }
    });
  }

  public void searchResultChanged(final SearchResultEvent event) {
    long time = currentTime();

    this.execute(time, event);
  }
}
