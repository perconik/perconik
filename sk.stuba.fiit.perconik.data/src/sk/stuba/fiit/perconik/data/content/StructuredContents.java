package sk.stuba.fiit.perconik.data.content;

import java.util.Iterator;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterators.filter;
import static com.google.common.collect.Lists.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContent.separator;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.isNullOrEmptyPredicate;

public class StructuredContents {
  private static final Joiner joiner = Joiner.on(separator);

  private static final Predicate<String> filter = not(isNullOrEmptyPredicate());

  private static final Splitter splitter = Splitter.on(separator).trimResults().omitEmptyStrings();

  private StructuredContents() {
  }

  public static String key(final String first, final String second, final String ... rest) {
    return key(asList(first, second, rest));
  }

  public static String key(final Iterable<String> sequence) {
    return joiner.join(filter(sequence, filter));
  }

  public static String key(final Iterator<String> sequence) {
    return joiner.join(filter(sequence, filter));
  }

  public static Iterable<String> sequence(final CharSequence key) {
    return splitter.split(key);
  }
}
