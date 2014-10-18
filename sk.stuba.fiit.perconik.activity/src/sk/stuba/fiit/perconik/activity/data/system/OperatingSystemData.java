package sk.stuba.fiit.perconik.activity.data.system;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class OperatingSystemData extends AnyStructuredData {
  protected String architecture;

  protected String name;

  protected String version;

  public OperatingSystemData() {}

  public void setArchitecture(String architecture) {
    this.architecture = architecture;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getArchitecture() {
    return this.architecture;
  }

  public String getName() {
    return this.name;
  }

  public String getVersion() {
    return this.version;
  }
}
