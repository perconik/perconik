package sk.stuba.fiit.perconik.utilities.reflect.resolver;

import static com.google.common.base.Preconditions.checkNotNull;

final class LoadingClassResolver implements ClassResolver {
  private final ClassLoader loader;

  LoadingClassResolver(final ClassLoader loader) {
    this.loader = checkNotNull(loader);
  }

  public Class<?> forName(final String name) throws ClassNotFoundException {
    return this.loader.loadClass(name);
  }

  @Override
  public String toString() {
    return "LoadingClassResolver(" + this.loader + ")";
  }
}
