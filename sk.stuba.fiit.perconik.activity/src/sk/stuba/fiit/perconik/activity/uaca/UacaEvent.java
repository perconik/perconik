package sk.stuba.fiit.perconik.activity.uaca;

import java.net.URI;
import java.util.Date;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import sk.stuba.fiit.perconik.activity.events.Event;
import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.bind.NamingStrategy.Default;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import static com.gratex.perconik.uaca.GenericUacaEventConstants.EVENT_TYPE_URI_PREFIX;
import static com.gratex.perconik.uaca.GenericUacaEventConstants.TIMESTAMP_PATTERN;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.requireNonNullOrEmpty;
import static sk.stuba.fiit.perconik.utilities.net.UniformResources.newUri;

@JsonNaming(Default.class)
public class UacaEvent extends AnyStructuredData {
  @Nullable
  protected Date timestamp;

  @Nullable
  protected URI eventTypeUri;

  @Nullable
  protected Object data;

  public UacaEvent() {}

  public static URI newEventTypeUri(final String path) {
    return newUri(EVENT_TYPE_URI_PREFIX + "/event/" + requireNonNullOrEmpty(path));
  }

  public static UacaEvent of(final String path, @Nullable final Object data) {
    UacaEvent event = new UacaEvent();

    if (data instanceof Event) {
      event.timestamp = new Date(((Event) data).getTimestamp());
    }

    event.eventTypeUri = newEventTypeUri(path);
    event.data = data;

    return event;
  }

  public void setTimestamp(@Nullable final Date timestamp) {
    this.timestamp = timestamp;
  }

  public void setEventTypeUri(@Nullable final URI eventTypeUri) {
    this.eventTypeUri = eventTypeUri;
  }

  public void setData(@Nullable final Object data) {
    this.data = data;
  }

  @JsonFormat(shape = STRING, pattern = TIMESTAMP_PATTERN)
  public Date getTimestamp() {
    return this.timestamp;
  }

  public URI getEventTypeUri() {
    return this.eventTypeUri;
  }

  public Object getData() {
    return this.data;
  }
}
