package sk.stuba.fiit.perconik.activity.data.core;

import sk.stuba.fiit.perconik.activity.data.base.ClassData;
import sk.stuba.fiit.perconik.core.Listener;
import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class ListenerData extends AnyStructuredData {
  protected ClassData implementation;

  public ListenerData() {}

  protected ListenerData(Listener listener) {
    if (listener == null) {
      return;
    }

    this.setImplementation(ClassData.of(listener.getClass()));
  }

  public static ListenerData of(Listener listener) {
    return new ListenerData(listener);
  }

  public void setImplementation(ClassData implementation) {
    this.implementation = implementation;
  }

  public ClassData getImplementation() {
    return this.implementation;
  }
}
