package sk.stuba.fiit.perconik.utilities.time;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Ticker;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A time source; returns a time value representing the number of milliseconds
 * elapsed since some fixed but arbitrary point in time.
 *
 * <p><b>Note:</b> this interface can be used to measure both wall time or elapsed time.
 */
public abstract class TimeSource {
  private static final TimeSource SYSTEM_TIME_SOURCE = new TimeSource() {
    @Override
    public long read() {
      return System.currentTimeMillis();
    }
  };

  /**
   * Constructor for use by subclasses.
   */
  protected TimeSource() {}

  public static final TimeSource fromTicker(final Ticker ticker) {
    if (ticker instanceof TimeSourceTicker) {
      return ((TimeSourceTicker) ticker).source;
    }

    return new TickerTimeSource(ticker);
  }

  public static final Ticker toTicker(final TimeSource source) {
    if (source instanceof TickerTimeSource) {
      return ((TickerTimeSource) source).ticker;
    }

    return new TimeSourceTicker(source);
  }

  private static final class TickerTimeSource extends TimeSource {
    final Ticker ticker;

    TickerTimeSource(final Ticker ticker) {
      this.ticker = checkNotNull(ticker);
    }

    @Override
    public long read() {
      return TimeUnit.NANOSECONDS.toMillis(this.ticker.read());
    }
  }

  private static final class TimeSourceTicker extends Ticker {
    final TimeSource source;

    TimeSourceTicker(final TimeSource source) {
      this.source = checkNotNull(source);
    }

    @Override
    public long read() {
      return TimeUnit.MILLISECONDS.toNanos(this.source.read());
    }
  }

  /**
   * A ticker that reads the current time using {@link System#currentTimeMillis}.
   */
  public static final TimeSource systemTimeSource() {
    return SYSTEM_TIME_SOURCE;
  }

  /**
   * Returns the number of milliseconds elapsed since this ticker's fixed point of reference.
   */
  public abstract long read();
}
