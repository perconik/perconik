package sk.stuba.fiit.perconik.utilities.concurrent;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import static java.lang.Long.compare;
import static java.lang.Long.parseLong;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class TimeValue implements Comparable<TimeValue>, Serializable {
  private static final long serialVersionUID = 0L;

  final long duration;

  final TimeUnit unit;

  TimeValue(final long duration, final TimeUnit unit) {
    this.duration = duration;
    this.unit = unit;
  }

  public static TimeValue of(final long duration, final TimeUnit unit) {
    return new TimeValue(duration, requireNonNull(unit));
  }

  public static TimeValue fromString(final String value) {
    final int length = value.length();

    if (length < 2) {
      throw new IllegalArgumentException();
    }

    int index = length - 1;
    char last = value.charAt(index);
    String part = value.substring(0, index);

    if (last == 's') {
      index --;

      last = value.charAt(index);
      part = value.substring(0, index);

      if (last == 'n') {
        return new TimeValue(parseLong(part), NANOSECONDS);
      } else if (last == 'µ') {
        return new TimeValue(parseLong(part), MILLISECONDS);
      } else if (last == 'm') {
        return new TimeValue(parseLong(part), MICROSECONDS);
      } else {
        return new TimeValue(parseLong(part), SECONDS);
      }
    } else if (last == 'm') {
      return new TimeValue(parseLong(part), MINUTES);
    } else if (last == 'h') {
      return new TimeValue(parseLong(part), HOURS);
    } else if (last == 'd') {
      return new TimeValue(parseLong(part), DAYS);
    }

    throw new IllegalArgumentException();
  }

  public static String toString(final long duration, final TimeUnit unit) {
    return duration + TimeUnits.toString(unit);
  }

  public static String toString(final Number duration, final TimeUnit unit) {
    return toString(duration.longValue(), unit);
  }

  public int compareTo(final long duration, final TimeUnit unit) {
    return compare(this.durationToNanos(), unit.toNanos(duration));
  }

  public int compareTo(final TimeValue value) {
    return this.compareTo(this.duration, this.unit);
  }

  @Override
  public boolean equals(@Nullable final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof TimeValue)) {
      return false;
    }

    TimeValue other = (TimeValue) o;

    return this.duration == other.duration && this.unit == other.unit;
  }

  @Override
  public int hashCode() {
    return 31 * (31 + (int) (this.duration ^ (this.duration >>> 32))) + this.unit.hashCode();
  }

  public TimeValue convert(final TimeUnit unit) {
    return this.unit == unit ? this : new TimeValue(unit.convert(this.duration, this.unit), unit);
  }

  public void timedWait(final Object o) throws InterruptedException {
    this.unit.timedWait(o, this.duration);
  }

  public void timedJoin(final Thread thread) throws InterruptedException {
    this.unit.timedJoin(thread, this.duration);
  }

  public void sleep() throws InterruptedException {
    this.unit.sleep(this.duration);
  }

  private static final class SerializationProxy implements Serializable {
    private static final long serialVersionUID = 0L;

    private final long duration;

    private final TimeUnit unit;

    SerializationProxy(final TimeValue value) {
      this.duration = value.duration;
      this.unit = value.unit;
    }

    private Object readResolve() throws InvalidObjectException {
      try {
        return of(this.duration, this.unit);
      } catch (RuntimeException e) {
        throw new InvalidObjectException("Unknown deserialization error");
      }
    }
  }

  @SuppressWarnings({"static-method", "unused"})
  private void readObject(final ObjectInputStream in) throws InvalidObjectException {
    throw new InvalidObjectException("Serialization proxy required");
  }

  private Object writeReplace() {
    return new SerializationProxy(this);
  }

  public long duration() {
    return this.duration;
  }

  public TimeValue duration(final long duration) {
    return this.duration == duration ? this : new TimeValue(duration, this.unit);
  }

  public long durationToNanos() {
    return this.unit.toNanos(this.duration);
  }

  public long durationToMicros() {
    return this.unit.toMicros(this.duration);
  }

  public long durationToMillis() {
    return this.unit.toMillis(this.duration);
  }

  public long durationToSeconds() {
    return this.unit.toSeconds(this.duration);
  }

  public long durationToMinutes() {
    return this.unit.toMinutes(this.duration);
  }

  public long durationToHours() {
    return this.unit.toHours(this.duration);
  }

  public long durationToDays() {
    return this.unit.toDays(this.duration);
  }

  public TimeUnit unit() {
    return this.unit;
  }

  public TimeValue unit(final TimeUnit unit) {
    return this.unit == unit ? this : of(this.duration, unit);
  }

  @Override
  public String toString() {
    return toString(this.duration, this.unit);
  }

  public TimeValue toNanos() {
    return this.unit == NANOSECONDS ? this : of(this.durationToNanos(), NANOSECONDS);
  }

  public TimeValue toMicros() {
    return this.unit == MICROSECONDS ? this : of(this.durationToMicros(), MICROSECONDS);
  }

  public TimeValue toMillis() {
    return this.unit == MILLISECONDS ? this : of(this.durationToMillis(), MILLISECONDS);
  }

  public TimeValue toSeconds() {
    return this.unit == SECONDS ? this : of(this.durationToSeconds(), SECONDS);
  }

  public TimeValue toMinutes() {
    return this.unit == MINUTES ? this : of(this.durationToMinutes(), MINUTES);
  }

  public TimeValue toHours() {
    return this.unit == HOURS ? this : of(this.durationToHours(), HOURS);
  }

  public TimeValue toDays() {
    return this.unit == DAYS ? this : of(this.durationToDays(), DAYS);
  }
}
