package sk.stuba.fiit.perconik.utilities;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;

import jersey.repackaged.com.google.common.collect.Lists;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static com.google.common.base.Preconditions.checkNotNull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Static utility methods pertaining to {@code String} or {@code CharSequence}
 * instances.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class MoreStrings {
  static final String lineSeparatorRegex = "\r?\n|\r";

  private MoreStrings() {}

  public static boolean equalsIgnoreLineSeparators(final String s, @Nullable final Object o) {
    if (s == o) {
      return true;
    }

    if (o instanceof String) {
      String r = (String) o;

      char[] v = r.toCharArray();
      char[] u = s.toCharArray();

      int m = v.length;
      int n = u.length;

      int i = 0;
      int j = 0;

      while (i < m) {
        char c = v[i ++];

        if (c == '\n' || c == '\r') {
          continue;
        }

        while (j < n) {
          char d = u[j ++];

          if (d == '\n' || d == '\r') {
            continue;
          }

          if (c == d) {
            break;
          }

          return false;
        }
      }

      while (j < n) {
        char c = u[j ++];

        if (c != '\n' && c != '\r') {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  public static String lineSeparatorRegex() {
    return lineSeparatorRegex;
  }

  public static List<Integer> lineNumbers(final String s, final int offset, final int length) {
    int limit = offset + length;

    checkState(offset >= 0 && length >= 0 && limit <= s.length());

    List<Integer> lines = Lists.newArrayList();

    int line = 0;

    for (int i = 0; i < limit; i ++) {
      char c = s.charAt(i);

      if (i >= offset) {
        lines.add(line);
      }

      if (c == '\n') {
        line ++;
      } else if (c == '\r') {
        line ++;

        int k = i + 1;

        if (k < limit && s.charAt(k) == '\n') {
          i = k;
        }

        lines.add(line);
      }
    }

    return lines;
  }

  public static List<String> lines(final String s) {
    return asList(s.split(lineSeparatorRegex));
  }

  public static String firstNonNullOrEmpty(@Nullable final String first, @Nullable final String second) {
    return !isNullOrEmpty(first) ? first : checkNotNullOrEmpty(second);
  }

  public static String checkNotNullOrEmpty(@Nullable final String s) {
    checkArgument(!s.isEmpty());

    return s;
  }

  public static boolean isWhitespace(final String s) {
    int length = s.length();

    for (int i = 0; i < length; i ++) {
      if (!Character.isWhitespace(s.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  private enum IsNullOrEmptyPredicate implements Predicate<String> {
    INSTANCE;

    public boolean apply(final String s) {
      return isNullOrEmpty(s);
    }
  }

  public static Predicate<String> isNullOrEmptyPredicate() {
    return IsNullOrEmptyPredicate.INSTANCE;
  }

  private enum IsWhitespacePredicate implements Predicate<String> {
    INSTANCE;

    public boolean apply(final String s) {
      return isWhitespace(s);
    }
  }

  public static Predicate<String> isWhitespacePredicate() {
    return IsWhitespacePredicate.INSTANCE;
  }

  public static String toDefaultString(final Object o) {
    return o.getClass().getName() + "@" + Integer.toHexString(o.hashCode());
  }

  public static String toCanonicalString(final Object o) {
    String name = o.getClass().getCanonicalName();

    if (name == null) {
      return null;
    }

    return name + "@" + Integer.toHexString(o.hashCode());
  }

  public static String toImplementedString(final Object o) {
    String result = o.toString();

    if (toDefaultString(o).equals(result)) {
      return null;
    }

    return result;
  }

  private enum ToStringComparator implements Comparator<Object> {
    INSTANCE;

    public int compare(final Object a, final Object b) {
      return a.toString().compareTo(b.toString());
    }
  }

  private static final class ToStringComparatorWithLocale<T> implements Comparator<T>, Serializable {
    private static final long serialVersionUID = 0L;

    private final Locale locale;

    ToStringComparatorWithLocale(final Locale locale) {
      this.locale = checkNotNull(locale);
    }

    Collator collator() {
      return Collator.getInstance(this.locale);
    }

    public final int compare(final T a, final T b) {
      return this.collator().compare(a.toString(), b.toString());
    }
  }

  public static <T> Comparator<T> toStringComparator() {
    @SuppressWarnings("unchecked")
    Comparator<T> comparator = (Comparator<T>) ToStringComparator.INSTANCE;

    return comparator;
  }

  public static <T> Comparator<T> toStringComparator(final Locale locale) {
    return new ToStringComparatorWithLocale<>(locale);
  }

  public static String toStringFallback(final Object o) {
    String result = toImplementedString(o);

    if (result != null) {
      return result;
    }

    result = toCanonicalString(o);

    if (result == null) {
      return result;
    }

    return toDefaultString(o);
  }

  public static <T> Function<T, String> toStringFunction() {
    @SuppressWarnings("unchecked")
    Function<T, String> result = (Function<T, String>) Functions.toStringFunction();

    return result;
  }

  public static String toLowerCase(@Nullable final Object o) {
    return valueOf(o).toLowerCase();
  }

  public static String toLowerCase(@Nullable final Object o, final Locale locale) {
    return valueOf(o).toLowerCase(locale);
  }

  public static String toLowerCaseFirst(@Nullable final Object o) {
    return toLowerCaseFirst(valueOf(o));
  }

  public static String toLowerCaseFirst(@Nullable final Object o, final Locale locale) {
    return toLowerCaseFirst(valueOf(o), locale);
  }

  public static String toLowerCaseFirst(final String s) {
    return Character.toLowerCase(s.charAt(0)) + s.substring(1);
  }

  public static String toLowerCaseFirst(final String s, final Locale locale) {
    return s.toLowerCase(locale).charAt(0) + s.substring(1);
  }

  public static String toUpperCase(@Nullable final Object o) {
    return valueOf(o).toUpperCase();
  }

  public static String toUpperCase(@Nullable final Object o, final Locale locale) {
    return valueOf(o).toUpperCase(locale);
  }

  public static String toUpperCaseFirst(@Nullable final Object o) {
    return toUpperCaseFirst(valueOf(o));
  }

  public static String toUpperCaseFirst(@Nullable final Object o, final Locale locale) {
    return toUpperCaseFirst(valueOf(o), locale);
  }

  public static String toUpperCaseFirst(final String s) {
    return Character.toUpperCase(s.charAt(0)) + s.substring(1);
  }

  public static String toUpperCaseFirst(final String s, final Locale locale) {
    return s.toUpperCase(locale).charAt(0) + s.substring(1);
  }

  private enum ToLowerCaseFunction implements Function<Object, String> {
    INSTANCE;

    public String apply(@Nullable final Object o) {
      return String.valueOf(o).toLowerCase();
    }
  }

  private static final class ToLowerCaseFunctionWithLocale<T> implements Function<T, String>, Serializable {
    private static final long serialVersionUID = 0L;

    private final Locale locale;

    ToLowerCaseFunctionWithLocale(final Locale locale) {
      this.locale = checkNotNull(locale);
    }

    public String apply(@Nullable final Object o) {
      return valueOf(o).toLowerCase(this.locale);
    }
  }

  private enum ToUpperCaseFunction implements Function<Object, String> {
    INSTANCE;

    public String apply(@Nullable final Object o) {
      return String.valueOf(o).toUpperCase();
    }
  }

  private static final class ToUpperCaseFunctionWithLocale<T> implements Function<T, String>, Serializable {
    private static final long serialVersionUID = 0L;

    private final Locale locale;

    ToUpperCaseFunctionWithLocale(final Locale locale) {
      this.locale = checkNotNull(locale);
    }

    public String apply(@Nullable final Object o) {
      return valueOf(o).toUpperCase(this.locale);
    }
  }

  public static <T> Function<T, String> toLowerCaseFunction() {
    @SuppressWarnings("unchecked")
    Function<T, String> result = (Function<T, String>) ToLowerCaseFunction.INSTANCE;

    return result;
  }

  public static <T> Function<T, String> toLowerCaseFunction(final Locale locale) {
    return new ToLowerCaseFunctionWithLocale<>(locale);
  }

  public static <T> Function<T, String> toUpperCaseFunction() {
    @SuppressWarnings("unchecked")
    Function<T, String> result = (Function<T, String>) ToUpperCaseFunction.INSTANCE;

    return result;
  }

  public static <T> Function<T, String> toUpperCaseFunction(final Locale locale) {
    return new ToUpperCaseFunctionWithLocale<>(locale);
  }
}
