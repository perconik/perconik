package sk.stuba.fiit.perconik.data.bind;

import com.fasterxml.jackson.databind.ObjectWriter;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;

public final class Writer {
  private static final ObjectWriter pretty;

  static {
    pretty = Mapper.getShared().writer(new PrettyPrinter()).with(INDENT_OUTPUT, ORDER_MAP_ENTRIES_BY_KEYS);
  }

  private Writer() {
    throw new AssertionError();
  }

  public static final ObjectWriter getPretty() {
    return pretty;
  }
}
