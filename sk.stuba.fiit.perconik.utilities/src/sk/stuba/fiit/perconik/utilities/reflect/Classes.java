package sk.stuba.fiit.perconik.utilities.reflect;

import com.google.common.base.CaseFormat;

public final class Classes {
  private Classes() {}

  public static String toMethodName(final Class<?> type) {
    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, type.getSimpleName());
  }
}
