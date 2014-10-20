package sk.stuba.fiit.perconik.activity.data;

import sk.stuba.fiit.perconik.data.AnyStructuredData;

public class TypeData extends AnyStructuredData {
  protected ClassData implementation;

  public TypeData() {}

  protected TypeData(final Class<?> implementation) {
    if (implementation == null) {
      return;
    }

    this.setImplementation(ClassData.of(implementation));
  }

  public static TypeData of(final Class<?> type) {
    return new TypeData(type);
  }

  public void setImplementation(final ClassData implementation) {
    this.implementation = implementation;
  }

  public ClassData getImplementation() {
    return this.implementation;
  }
}
