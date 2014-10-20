package sk.stuba.fiit.perconik.activity.data.system;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class SpecificationData extends AnyStructuredData {
  protected String name;

  protected String vendor;

  protected String version;

  public SpecificationData() {}

  public void setName(final String name) {
    this.name = name;
  }

  public void setVendor(final String vendor) {
    this.vendor = vendor;
  }

  public void setVersion(final String version) {
    this.version = version;
  }

  public String getName() {
    return this.name;
  }

  public String getVendor() {
    return this.vendor;
  }

  public String getVersion() {
    return this.version;
  }
}
