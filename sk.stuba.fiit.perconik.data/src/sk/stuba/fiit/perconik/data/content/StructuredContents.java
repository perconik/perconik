package sk.stuba.fiit.perconik.data.content;

import java.util.Iterator;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import static sk.stuba.fiit.perconik.data.content.StructuredContent.separator;

public class StructuredContents {
  private static final Joiner joiner = Joiner.on(separator).skipNulls();

  private static final Splitter splitter = Splitter.on(separator).trimResults().omitEmptyStrings();

  private StructuredContents() {
  }

  public static String key(final String first, final String second, final String ... rest) {
    return joiner.join(first, second, (Object[]) rest);
  }

  public static String key(final Iterable<String> sequence) {
    return joiner.join(sequence);
  }

  public static String key(final Iterator<String> sequence) {
    return joiner.join(sequence);
  }

  public static Iterable<String> sequence(final CharSequence key) {
    return splitter.split(key);
  }
}
