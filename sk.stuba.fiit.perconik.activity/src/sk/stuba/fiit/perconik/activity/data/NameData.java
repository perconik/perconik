package sk.stuba.fiit.perconik.activity.data;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class NameData extends AnyStructuredData {
  protected String canonical;

  protected String normal;

  protected String simple;

  public NameData() {}

  protected NameData(final Class<?> type) {
    if (type == null) {
      return;
    }

    this.setCanonical(type.getCanonicalName());
    this.setNormal(type.getName());
    this.setSimple(type.getSimpleName());
  }

  public static NameData of(final Class<?> type) {
    return new NameData(type);
  }

  public void setCanonical(final String canonical) {
    this.canonical = canonical;
  }

  public void setNormal(final String normal) {
    this.normal = normal;
  }

  public void setSimple(final String simple) {
    this.simple = simple;
  }

  public String getCanonical() {
    return this.canonical;
  }

  public String getNormal() {
    return this.normal;
  }

  public String getSimple() {
    return this.simple;
  }
}
