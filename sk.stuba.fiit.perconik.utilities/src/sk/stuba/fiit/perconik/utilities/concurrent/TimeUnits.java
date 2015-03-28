package sk.stuba.fiit.perconik.utilities.concurrent;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public final class TimeUnits {
  private TimeUnits() {}

  public static TimeUnit lesser(final TimeUnit a, final TimeUnit b) {
    return a.compareTo(b) < 0 ? a : b;
  }

  public static TimeUnit greater(final TimeUnit a, final TimeUnit b) {
    return a.compareTo(b) > 0 ? a : b;
  }

  public static TimeUnit fromString(final String unit) {
    switch (unit) {
      case "ns":
        return NANOSECONDS;

      case "\u00b5s":
        return MICROSECONDS;

      case "ms":
        return MILLISECONDS;

      case "s":
        return SECONDS;

      case "m":
        return MINUTES;

      case "h":
        return HOURS;

      case "d":
        return DAYS;

      default:
        throw new IllegalArgumentException();
    }
  }

  public static String toString(final TimeUnit unit) {
    switch (unit) {
      case NANOSECONDS:
        return "ns";

      case MICROSECONDS:
        return "\u00b5s";

      case MILLISECONDS:
        return "ms";

      case SECONDS:
        return "s";

      case MINUTES:
        return "m";

      case HOURS:
        return "h";

      case DAYS:
        return "d";

      default:
        throw new AssertionError();
    }
  }

  public static long toNanos(final long duration, final TimeUnit unit) {
    return unit.toNanos(duration);
  }

  public static long toMicros(final long duration, final TimeUnit unit) {
    return unit.toMicros(duration);
  }

  public static long toMillis(final long duration, final TimeUnit unit) {
    return unit.toMillis(duration);
  }

  public static long toSeconds(final long duration, final TimeUnit unit) {
    return unit.toSeconds(duration);
  }

  public static long toMinutes(final long duration, final TimeUnit unit) {
    return unit.toMinutes(duration);
  }

  public static long toHours(final long duration, final TimeUnit unit) {
    return unit.toHours(duration);
  }

  public static long toDays(final long duration, final TimeUnit unit) {
    return unit.toDays(duration);
  }
}
