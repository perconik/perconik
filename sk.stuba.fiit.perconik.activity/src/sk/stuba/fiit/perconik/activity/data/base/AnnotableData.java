package sk.stuba.fiit.perconik.activity.data.base;

import java.lang.reflect.AnnotatedElement;
import java.util.List;

import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotable;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotables;

public class AnnotableData extends AnyStructuredData {
  protected List<AnnotationData> annotations;

  public AnnotableData() {}

  protected AnnotableData(Annotable annotable) {
    if (annotable == null) {
      return;
    }

    this.setAnnotations(AnnotationData.of(annotable.getAnnotations()));
  }

  protected AnnotableData(AnnotatedElement element) {
    this(Annotables.fromElement(element));
  }

  public static AnnotableData of(Annotable annotable) {
    return new AnnotableData(annotable);
  }

  public static AnnotableData of(AnnotatedElement element) {
    return new AnnotableData(element);
  }

  public void setAnnotations(List<AnnotationData> annotations) {
    this.annotations = annotations;
  }

  public List<AnnotationData> getAnnotations() {
    return this.annotations;
  }
}
