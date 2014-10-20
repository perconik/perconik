package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;

import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * Static utility methods pertaining to Eclipse products.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Products {
  private Products() {}

  public static IProduct getProduct() {
    return Platform.getProduct();
  }

  public static Bundle getBundle(final IProduct product) {
    return product.getDefiningBundle();
  }

  public static String getName(final IProduct product) {
    return product.getName();
  }

  public static Version getVersion(final IProduct product) {
    return getBundle(product).getVersion();
  }
}
