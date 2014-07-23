package sk.stuba.fiit.perconik.osgi.framework;

import org.osgi.framework.Bundle;

import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

import static com.google.common.base.Preconditions.checkNotNull;

final class BundleClassResolver implements ClassResolver {
  private Bundle bundle;

  BundleClassResolver(Bundle loader) {
    this.bundle = checkNotNull(loader);
  }

  public final Class<?> forName(String name) throws ClassNotFoundException {
    return this.bundle.loadClass(name);
  }

  @Override
  public final String toString() {
    String bundle = this.bundle.getSymbolicName();

    return "BundleClassResolver(" + (bundle != null ? bundle : this.bundle.getBundleId()) + ")";
  }
}
