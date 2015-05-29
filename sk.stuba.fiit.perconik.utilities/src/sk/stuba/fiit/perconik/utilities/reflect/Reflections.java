package sk.stuba.fiit.perconik.utilities.reflect;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import static java.util.Arrays.asList;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;

public final class Reflections {
  private Reflections() {}

  private enum ToAnnotationTypeFunction implements Function<Annotation, Class<? extends Annotation>> {
    INSTANCE;

    public Class<? extends Annotation> apply(@Nullable final Annotation annotation) {
      return annotation != null ? annotation.annotationType() : null;
    }

    @Override
    public String toString() {
      return Classes.toMethodName(this.getClass());
    }
  }

  private enum ToClassFunction implements Function<Object, Class<? extends Object>> {
    INSTANCE;

    public Class<? extends Object> apply(@Nullable final Object object) {
      return object != null ? object.getClass() : null;
    }

    @Override
    public String toString() {
      return Classes.toMethodName(this.getClass());
    }
  }

  private enum ToEnumTypeFunction implements Function<Enum<?>, Class<?>> {
    INSTANCE;

    public Class<?> apply(@Nullable final Enum<?> constant) {
      return constant != null ? constant.getDeclaringClass() : null;
    }

    @Override
    public String toString() {
      return Classes.toMethodName(this.getClass());
    }
  }

  private static <F, T> Function<F, T> cast(final Function<?, ?> function) {
    // only for stateless internal singletons shared across all types
    @SuppressWarnings({"unchecked", "rawtypes"})
    Function<F, T> result = (Function) function;

    return result;
  }

  public static <A extends Annotation> Function<A, Class<? extends A>> toAnnotationTypeFunction() {
    return cast(ToAnnotationTypeFunction.INSTANCE);
  }

  public static <T> Function<T, Class<? extends T>> toClassFunction() {
    return cast(ToClassFunction.INSTANCE);
  }

  public static <E extends Enum<E>> Function<E, Class<E>> toEnumTypeFunction() {
    return cast(ToEnumTypeFunction.INSTANCE);
  }

  public static <T> LinkedList<Class<? super T>> collectSuperclasses(final Class<T> type) {
    LinkedList<Class<? super T>> superclasses = newLinkedList();

    Class<? super T> supertype = type;

    while ((supertype = supertype.getSuperclass()) != null) {
      superclasses.add(supertype);
    }

    return superclasses;
  }

  public static LinkedHashSet<Class<?>> collectInterfaces(final Class<?> type) {
    Set<Class<?>> resolved = newHashSet();

    LinkedHashSet<Class<?>> interfaces = newLinkedHashSet();

    interfaces.addAll(asList(type.getInterfaces()));

    resolved.add(type);

    for (Class<?> supertype: collectSuperclasses(type)) {
      groupInterfaces(supertype, resolved, interfaces);
    }

    return interfaces;
  }

  private static void groupInterfaces(final Class<?> type, final Set<Class<?>> resolved, final Set<Class<?>> result) {
    if (resolved.add(type)) {
      for (Class<?> supertype: type.getInterfaces()) {
        groupInterfaces(supertype, resolved, result);
      }
    }

    if (type.isInterface()) {
      result.add(type);
    }
  }
}
