package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.reflect.AnnotatedElement;

public final class Annotables {
  private Annotables() {}

  public static Annotable fromClass(final Class<?> type) {
    return new DelegatedAnnotable(type);
  }

  public static Annotable fromElement(final AnnotatedElement element) {
    return new DelegatedAnnotable(element);
  }
}
