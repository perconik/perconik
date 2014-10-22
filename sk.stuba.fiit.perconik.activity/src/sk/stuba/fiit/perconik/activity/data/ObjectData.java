package sk.stuba.fiit.perconik.activity.data;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class ObjectData extends AnyStructuredData {
  protected ClassData implementation;

  protected int hashCode;

  protected String toString;

  public ObjectData() {}

  protected ObjectData(final Object object) {
    if (object == null) {
      return;
    }

    this.setImplementation(ClassData.of(object.getClass()));
    this.setHashCode(object.hashCode());
    this.setToString(object.toString());
  }

  public static ObjectData of(final Object object) {
    return new ObjectData(object);
  }

  public void setImplementation(final ClassData implementation) {
    this.implementation = implementation;
  }

  public void setHashCode(final int hashCode) {
    this.hashCode = hashCode;
  }

  public void setToString(final String toString) {
    this.toString = toString;
  }

  public ClassData getImplementation() {
    return this.implementation;
  }

  public int getHashCode() {
    return this.hashCode;
  }

  public String getToString() {
    return this.toString;
  }
}
