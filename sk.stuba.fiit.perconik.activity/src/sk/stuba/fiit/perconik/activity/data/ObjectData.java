package sk.stuba.fiit.perconik.activity.data;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class ObjectData extends AnyStructuredData {
  protected ClassData implementation;

  protected String description;

  protected int hash;

  public ObjectData() {}

  protected ObjectData(final Object object) {
    if (object == null) {
      return;
    }

    this.setImplementation(ClassData.of(object.getClass()));
    this.setHash(object.hashCode());
    this.setDescription(object.toString());
  }

  public static ObjectData of(final Object object) {
    return new ObjectData(object);
  }

  public void setImplementation(final ClassData implementation) {
    this.implementation = implementation;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public void setHash(final int hash) {
    this.hash = hash;
  }

  public ClassData getImplementation() {
    return this.implementation;
  }

  public String getDescription() {
    return this.description;
  }

  public int getHash() {
    return this.hash;
  }
}
