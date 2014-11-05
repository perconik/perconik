package sk.stuba.fiit.perconik.activity.data.platform;

import org.eclipse.core.runtime.IProduct;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class ProductData extends AnyStructuredData {
  protected String application;

  protected String identifier;

  protected String name;

  protected String description;

  protected BundleData bundle;

  public ProductData() {}

  protected ProductData(final IProduct product) {
    if (product == null) {
      return;
    }

    this.setApplication(product.getApplication());
    this.setIdentifier(product.getId());
    this.setName(product.getName());
    this.setDescription(product.getDescription());
    this.setBundle(BundleData.of(product.getDefiningBundle()));
  }

  public static ProductData of(final IProduct product) {
    return new ProductData(product);
  }

  public void setApplication(final String application) {
    this.application = application;
  }

  public void setIdentifier(final String identifier) {
    this.identifier = identifier;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public void setBundle(final BundleData bundle) {
    this.bundle = bundle;
  }

  public String getApplication() {
    return this.application;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public BundleData getBundle() {
    return this.bundle;
  }
}
