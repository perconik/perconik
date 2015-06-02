package sk.stuba.fiit.perconik.data.content;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;

import static java.util.Arrays.asList;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterators.filter;
import static com.google.common.collect.Lists.asList;

import static sk.stuba.fiit.perconik.data.content.StructuredContent.separator;
import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullOrEmpty;
import static sk.stuba.fiit.perconik.utilities.MoreStrings.isNullOrEmptyPredicate;

public class StructuredContents {
  private static final Joiner joiner = Joiner.on(separator);

  private static final Predicate<String> filter = not(isNullOrEmptyPredicate());

  private static final Splitter splitter = Splitter.on(separator).trimResults().omitEmptyStrings();

  private StructuredContents() {}

  public static String key(final String component) {
    return checkNotNullOrEmpty(component);
  }

  public static String key(final String first, final String second, final String ... rest) {
    return key(asList(first, second, rest));
  }

  public static String key(final String[] components) {
    return key(asList(components));
  }

  public static String key(final Iterable<String> components) {
    return checkNotNullOrEmpty(joiner.join(filter(components, filter)));
  }

  public static String key(final Iterator<String> components) {
    return checkNotNullOrEmpty(joiner.join(filter(components, filter)));
  }

  public static List<String> sequence(final CharSequence key) {
    List<String> components = splitter.splitToList(key);

    checkState(!components.isEmpty());

    return components;
  }
}
