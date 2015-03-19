package sk.stuba.fiit.perconik.utilities.concurrent;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public final class TimeUnits {
  private TimeUnits() {}

  public static long setTo(final long duration, final TimeUnit unit) {
    return NANOSECONDS.convert(duration, unit);
  }
}
