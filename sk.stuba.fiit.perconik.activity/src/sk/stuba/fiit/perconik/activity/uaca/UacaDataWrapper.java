package sk.stuba.fiit.perconik.activity.uaca;

import java.net.URI;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;

public class UacaDataWrapper extends AnyStructuredData {
  public static final URI eventTypeUriPrefix = newUri("http://perconik.gratex.com/useractivity/event");

  protected URI eventTypeUri;

  protected Object data;

  public UacaDataWrapper() {}

  public static UacaDataWrapper of(String path, Object data) {
    UacaDataWrapper wrapper = new UacaDataWrapper();

    wrapper.eventTypeUri = newUri(eventTypeUriPrefix + "/" + path);
    wrapper.data = data;

    return wrapper;
  }

  public void setEventTypeUri(URI eventTypeUri) {
    this.eventTypeUri = eventTypeUri;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public URI getEventTypeUri() {
    return this.eventTypeUri;
  }

  public Object getData() {
    return this.data;
  }
}
