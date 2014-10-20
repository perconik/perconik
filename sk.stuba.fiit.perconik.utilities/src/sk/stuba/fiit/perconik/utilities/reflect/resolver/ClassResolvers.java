package sk.stuba.fiit.perconik.utilities.reflect.resolver;

import com.google.common.collect.ImmutableList;

import static com.google.common.collect.Lists.asList;

public final class ClassResolvers {
  private ClassResolvers() {}

  public static ClassResolver forClassLoader(final ClassLoader loader) {
    return new LoadingClassResolver(loader);
  }

  public static ClassResolver forSystemClassLoader() {
    return forClassLoader(ClassLoader.getSystemClassLoader());
  }

  public static ClassResolver forClass(final Class<?> type) {
    return forClassLoader(type.getClassLoader());
  }

  public static ClassResolver forObject(final Object object) {
    return forClass(object.getClass());
  }

  public static ClassResolver forThread(final Thread thread) {
    return forClassLoader(thread.getContextClassLoader());
  }

  public static ClassResolver forCurrentThread() {
    return forThread(Thread.currentThread());
  }

  public static ClassResolver getDefault() {
    return DefaultClassResolver.INSTANCE;
  }

  public static ClassResolver compose(final ClassResolver a, final ClassResolver b) {
    return compose(ImmutableList.of(a, b));
  }

  public static ClassResolver compose(final ClassResolver a, final ClassResolver b, final ClassResolver ... rest) {
    return compose(asList(a, b, rest));
  }

  public static ClassResolver compose(final Iterable<ClassResolver> resolvers) {
    return new CompositeClassResolver(resolvers);
  }
}
