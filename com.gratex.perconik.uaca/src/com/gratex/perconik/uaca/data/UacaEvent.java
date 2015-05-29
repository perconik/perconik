package com.gratex.perconik.uaca.data;

import java.net.URI;
import java.util.Date;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.data.bind.NamingStrategy.Default;
import sk.stuba.fiit.perconik.data.content.Content;
import sk.stuba.fiit.perconik.data.content.StructuredContent;
import sk.stuba.fiit.perconik.data.events.Event;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import static com.gratex.perconik.uaca.data.GenericUacaEventConstants.EVENT_TYPE_URI_PREFIX;
import static com.gratex.perconik.uaca.data.GenericUacaEventConstants.TIMESTAMP_PATTERN;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.checkNotNullOrEmpty;
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

  public static UacaEvent of(final String path, @Nullable final Object data) {
    UacaEvent event = new UacaEvent();

    if (data instanceof Event) {
      event.timestamp = new Date(((Event) data).getTimestamp());
    }

    event.eventTypeUri = eventTypeUri(path);
    event.data = normalizeData(data);

    return event;
  }

  /**
   * Creates UACA compatible event type URI for specified path. Each URI is prefixed with
   * an {@link GenericUacaEventConstants#EVENT_TYPE_URI_PREFIX EVENT_TYPE_URI_PREFIX}.
   */
  public static URI eventTypeUri(final String path) {
    return newUri(EVENT_TYPE_URI_PREFIX + "/" + checkNotNullOrEmpty(path));
  }

  /**
   * Normalizes event data according to UACA restrictions. Each data of type {@link Content}
   * must be fully structured since UACA does not permit dot characters in JSON field names.
   */
  public static Object normalizeData(@Nullable final Object data) {
    if (data instanceof StructuredContent) {
      return ((StructuredContent) data).structure();
    } else if (data instanceof Content) {
      return new AnyStructuredData().merge((Content) data);
    }

    return data;
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
