package sk.stuba.fiit.perconik.activity.data.base;

import sk.stuba.fiit.perconik.activity.data.AnyStructuredData;

public class TypeData extends AnyStructuredData {
  protected ClassData implementation;

  public TypeData() {}

  protected TypeData(Class<?> implementation) {
    if (implementation == null) {
      return;
    }

    this.setImplementation(ClassData.of(implementation));
  }

  public static TypeData of(Class<?> type) {
    return new TypeData(type);
  }

  public void setImplementation(ClassData implementation) {
    this.implementation = implementation;
  }

  public ClassData getImplementation() {
    return this.implementation;
  }
}
