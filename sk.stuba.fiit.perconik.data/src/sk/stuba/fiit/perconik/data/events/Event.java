package sk.stuba.fiit.perconik.data.events;

import sk.stuba.fiit.perconik.data.content.AnyStructuredContent;

public interface Event extends AnyStructuredContent {
  public long getTimestamp();

  public String getAction();
}
