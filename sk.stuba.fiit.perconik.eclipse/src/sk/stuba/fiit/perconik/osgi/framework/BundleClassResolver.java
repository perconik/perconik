package sk.stuba.fiit.perconik.osgi.framework;

import org.osgi.framework.Bundle;

import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

import static com.google.common.base.Preconditions.checkNotNull;

final class BundleClassResolver implements ClassResolver {
  private final Bundle bundle;

  BundleClassResolver(final Bundle loader) {
    this.bundle = checkNotNull(loader);
  }

  public Class<?> forName(final String name) throws ClassNotFoundException {
    return this.bundle.loadClass(name);
  }

  @Override
  public String toString() {
    String bundle = this.bundle.getSymbolicName();

    return "BundleClassResolver(" + (bundle != null ? bundle : this.bundle.getBundleId()) + ")";
  }
}
