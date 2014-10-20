package sk.stuba.fiit.perconik.utilities.reflect.annotation;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

final class EnumeratedAnnotable implements Annotable {
  private final ImmutableMap<Class<? extends Annotation>, Annotation> map;

  EnumeratedAnnotable(final Iterator<Annotation> annotations) {
    Builder<Class<? extends Annotation>, Annotation> builder = ImmutableMap.builder();

    while (annotations.hasNext()) {
      Annotation annotation = annotations.next();

      builder.put(annotation.annotationType(), annotation);
    }

    this.map = builder.build();
  }

  public boolean hasAnnotation(final Class<? extends Annotation> type) {
    return this.map.containsKey(type);
  }

  public <A extends Annotation> A getAnnotation(final Class<A> type) {
    return type.cast(this.map.get(type));
  }

  public List<Annotation> getAnnotations() {
    return this.map.values().asList();
  }
}
