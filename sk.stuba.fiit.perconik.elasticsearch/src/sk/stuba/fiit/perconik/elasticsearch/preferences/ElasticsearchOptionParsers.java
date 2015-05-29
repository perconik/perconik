package sk.stuba.fiit.perconik.elasticsearch.preferences;

import com.google.common.reflect.TypeToken;

import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.RatioValue;
import org.elasticsearch.common.unit.SizeValue;
import org.elasticsearch.common.unit.TimeValue;

import sk.stuba.fiit.perconik.utilities.configuration.OptionParser;

import static org.elasticsearch.common.unit.ByteSizeValue.parseBytesSizeValue;
import static org.elasticsearch.common.unit.RatioValue.parseRatioValue;
import static org.elasticsearch.common.unit.SizeValue.parseSizeValue;
import static org.elasticsearch.common.unit.TimeValue.parseTimeValue;

public final class ElasticsearchOptionParsers {
  private ElasticsearchOptionParsers() {}

  private enum ByteSizeValueParser implements OptionParser<ByteSizeValue> {
    INSTANCE;

    public ByteSizeValue parse(final Object object) {
      return object instanceof ByteSizeValue ? (ByteSizeValue) object : parseBytesSizeValue(object.toString());
    }

    public TypeToken<ByteSizeValue> type() {
      return TypeToken.of(ByteSizeValue.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<ByteSizeValue> byteSizeParser() {
    return ByteSizeValueParser.INSTANCE;
  }

  private enum RatioValueParser implements OptionParser<RatioValue> {
    INSTANCE;

    public RatioValue parse(final Object object) {
      return object instanceof RatioValue ? (RatioValue) object : parseRatioValue(object.toString());
    }

    public TypeToken<RatioValue> type() {
      return TypeToken.of(RatioValue.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<RatioValue> ratioParser() {
    return RatioValueParser.INSTANCE;
  }

  private enum SizeValueParser implements OptionParser<SizeValue> {
    INSTANCE;

    public SizeValue parse(final Object object) {
      return object instanceof SizeValue ? (SizeValue) object : parseSizeValue(object.toString());
    }

    public TypeToken<SizeValue> type() {
      return TypeToken.of(SizeValue.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<SizeValue> sizeParser() {
    return SizeValueParser.INSTANCE;
  }

  private enum TimeValueParser implements OptionParser<TimeValue> {
    INSTANCE;

    public TimeValue parse(final Object object) {
      return object instanceof TimeValue ? (TimeValue) object : parseTimeValue(object.toString(), null);
    }

    public TypeToken<TimeValue> type() {
      return TypeToken.of(TimeValue.class);
    }

    @Override
    public String toString() {
      return this.getClass().getSimpleName();
    }
  }

  public static OptionParser<TimeValue> timeParser() {
    return TimeValueParser.INSTANCE;
  }
}
