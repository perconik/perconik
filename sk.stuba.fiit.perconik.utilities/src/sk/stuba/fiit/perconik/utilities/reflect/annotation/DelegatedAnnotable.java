package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

final class DelegatedAnnotable implements Annotable {
  private final AnnotatedElement element;

  DelegatedAnnotable(final AnnotatedElement element) {
    this.element = checkNotNull(element);
  }

  public boolean hasAnnotation(final Class<? extends Annotation> type) {
    return this.element.isAnnotationPresent(type);
  }

  public <A extends Annotation> A getAnnotation(final Class<A> type) {
    return this.element.getAnnotation(type);
  }

  public List<Annotation> getAnnotations() {
    return Annotations.ofElement(this.element);
  }
}
