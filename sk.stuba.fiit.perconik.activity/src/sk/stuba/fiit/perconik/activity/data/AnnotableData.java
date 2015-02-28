package sk.stuba.fiit.perconik.activity.data;

import java.lang.reflect.AnnotatedElement;
import java.util.List;

import sk.stuba.fiit.perconik.data.AnyStructuredData;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotable;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotables;

import static sk.stuba.fiit.perconik.activity.data.DataCollections.toAnnotationData;

public class AnnotableData extends AnyStructuredData {
  protected List<AnnotationData> annotations;

  public AnnotableData() {}

  protected AnnotableData(final Annotable annotable) {
    if (annotable == null) {
      return;
    }

    this.setAnnotations(toAnnotationData(annotable.getAnnotations()));
  }

  protected AnnotableData(final AnnotatedElement element) {
    this(Annotables.fromElement(element));
  }

  public static AnnotableData of(final Annotable annotable) {
    return new AnnotableData(annotable);
  }

  public static AnnotableData of(final AnnotatedElement element) {
    return new AnnotableData(element);
  }

  public void setAnnotations(final List<AnnotationData> annotations) {
    this.annotations = annotations;
  }

  public List<AnnotationData> getAnnotations() {
    return this.annotations;
  }
}
