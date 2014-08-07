package sk.stuba.fiit.perconik.activity.uaca;

import java.net.URI;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;
import sk.stuba.fiit.perconik.activity.data.Content;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;

public class UacaContentWrapper extends AnyStructuredData {
  public static final URI eventTypeUriPrefix = newUri("http://perconik.gratex.com/useractivity/event");

  protected URI eventTypeUri;

  protected Content data;

  public UacaContentWrapper() {}

  public static UacaContentWrapper of(String path, Content content) {
    UacaContentWrapper data = new UacaContentWrapper();

    data.eventTypeUri = newUri(eventTypeUriPrefix + "/" + path);
    data.data = content;

    return data;
  }

  public void setEventTypeUri(URI eventTypeUri) {
    this.eventTypeUri = eventTypeUri;
  }

  public void setData(Content data) {
    this.data = data;
  }

  public URI getEventTypeUri() {
    return this.eventTypeUri;
  }

  public Content getData() {
    // TODO ws.rs ignores any data in data?? not available in uaca

    return this.data;
  }
}
