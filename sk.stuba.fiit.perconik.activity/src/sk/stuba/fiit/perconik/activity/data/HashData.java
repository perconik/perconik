package sk.stuba.fiit.perconik.activity.data;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

import static java.lang.System.identityHashCode;

public class HashData extends AnyStructuredData {
  protected int identity;

  protected int implementation;

  public HashData() {}

  protected HashData(final Object object) {
    if (object == null) {
      return;
    }

    this.setIdentity(identityHashCode(object));
    this.setImplementation(object.hashCode());
  }

  public static HashData of(final Object object) {
    return new HashData(object);
  }

  public void setIdentity(final int identity) {
    this.identity = identity;
  }

  public void setImplementation(final int implementation) {
    this.implementation = implementation;
  }

  public int getIdentity() {
    return this.identity;
  }

  public int getImplementation() {
    return this.implementation;
  }
}
