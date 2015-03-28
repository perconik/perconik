package sk.stuba.fiit.perconik.activity.data;

import java.lang.annotation.Annotation;
import java.util.List;

import sk.stuba.fiit.perconik.core.Nameable;
import sk.stuba.fiit.perconik.utilities.reflect.annotation.Annotable;

import static sk.stuba.fiit.perconik.utilities.MoreLists.newArrayListSuitableFor;

public class DataCollections {
  private DataCollections() {}

  public static List<ObjectData> toObjectData(final Iterable<? extends Object> objects) {
    List<ObjectData> data = newArrayListSuitableFor(objects);

    for (Object object: objects) {
      data.add(ObjectData.of(object));
    }

    return data;
  }

  public static List<ClassData> toClassData(final Iterable<? extends Class<?>> types) {
    List<ClassData> data = newArrayListSuitableFor(types);

    for (Class<?> type: types) {
      data.add(ClassData.of(type));
    }

    return data;
  }

  public static List<AnnotationData> toAnnotationData(final Iterable<? extends Annotation> annotations) {
    List<AnnotationData> data = newArrayListSuitableFor(annotations);

    for (Annotation annotation: annotations) {
      data.add(AnnotationData.of(annotation));
    }

    return data;
  }

  public static List<AnnotableData> toAnnotableData(final Iterable<? extends Annotable> annotables) {
    List<AnnotableData> data = newArrayListSuitableFor(annotables);

    for (Annotable annotable: annotables) {
      data.add(AnnotableData.of(annotable));
    }

    return data;
  }

  public static List<NameData> toNameData(final Iterable<? extends Class<?>> types) {
    List<NameData> data = newArrayListSuitableFor(types);

    for (Class<?> type: types) {
      data.add(NameData.of(type));
    }

    return data;
  }

  public static List<NameableData> toNameableData(final Iterable<? extends Nameable> nameables) {
    List<NameableData> data = newArrayListSuitableFor(nameables);

    for (Nameable nameable: nameables) {
      data.add(NameableData.of(nameable));
    }

    return data;
  }
}
