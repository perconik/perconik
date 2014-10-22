package sk.stuba.fiit.perconik.activity.data.core;

import sk.stuba.fiit.perconik.activity.data.ObjectData;
import sk.stuba.fiit.perconik.core.Listener;

public class ListenerData extends ObjectData {
  public ListenerData() {}

  protected ListenerData(final Listener listener) {
    super(listener);
  }

  public static ListenerData of(final Listener listener) {
    return new ListenerData(listener);
  }
}
