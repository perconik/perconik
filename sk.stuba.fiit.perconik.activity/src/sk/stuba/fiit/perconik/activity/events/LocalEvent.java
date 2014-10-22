package sk.stuba.fiit.perconik.activity.events;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import static sk.stuba.fiit.perconik.data.bind.Defaults.TIME_PATTERN;

public class LocalEvent extends Event {
  public LocalEvent() {}

  protected LocalEvent(final long timestamp) {
    super(timestamp);
  }

  protected LocalEvent(final Date time) {
    this(time.getTime());
  }

  protected LocalEvent(final long timestamp, final String action) {
    super(timestamp, action);
  }

  protected LocalEvent(final Date time, final String action) {
    this(time.getTime(), action);
  }

  public static LocalEvent of(final long timestamp) {
    return new LocalEvent(timestamp);
  }

  public static LocalEvent of(final Date time) {
    return new LocalEvent(time);
  }

  public static LocalEvent of(final long timestamp, final String action) {
    return new LocalEvent(timestamp, action);
  }

  public static LocalEvent of(final Date time, final String action) {
    return new LocalEvent(time, action);
  }

  public void setTime(final Date time) {
    super.setTimestamp(time != null ? time.getTime() : 0);
  }

  @JsonFormat(shape = STRING, pattern = TIME_PATTERN)
  public Date getTime() {
    return new Date(super.timestamp);
  }
}
