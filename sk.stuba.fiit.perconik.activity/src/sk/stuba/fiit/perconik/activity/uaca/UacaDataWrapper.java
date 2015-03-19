package sk.stuba.fiit.perconik.activity.uaca;

import java.net.URI;

import javax.annotation.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.bind.NamingStrategy.Default;

import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;

@JsonNaming(Default.class)
public class UacaDataWrapper extends AnyStructuredData {
  public static final URI eventTypeUriPrefix = newUri("http://perconik.gratex.com/useractivity/event");

  protected URI eventTypeUri;

  @Nullable
  protected Object data;

  public UacaDataWrapper() {}

  public static UacaDataWrapper of(final String path, @Nullable final Object data) {
    UacaDataWrapper wrapper = new UacaDataWrapper();

    wrapper.eventTypeUri = newUri(eventTypeUriPrefix + "/" + path);
    wrapper.data = data;

    return wrapper;
  }

  public void setEventTypeUri(final URI eventTypeUri) {
    this.eventTypeUri = eventTypeUri;
  }

  public void setData(@Nullable final Object data) {
    this.data = data;
  }

  public URI getEventTypeUri() {
    return this.eventTypeUri;
  }

  public Object getData() {
    return this.data;
  }
}
