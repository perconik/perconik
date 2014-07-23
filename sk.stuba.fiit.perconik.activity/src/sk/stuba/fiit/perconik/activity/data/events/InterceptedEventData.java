package sk.stuba.fiit.perconik.activity.data.events;

import java.util.List;

import sk.stuba.fiit.perconik.activity.data.core.ListenerData;
import sk.stuba.fiit.perconik.activity.data.core.ResourceData;

public class InterceptedEventData extends EventData {
  protected String category;

  protected String label;

  protected ListenerData interceptingListener;

  protected List<ResourceData> possibleResources;

  public InterceptedEventData() {
    super();
  }

  protected InterceptedEventData(String action) {
    super(action);
  }

  public static InterceptedEventData of(String action) {

    return new InterceptedEventData(action);
  }
}
