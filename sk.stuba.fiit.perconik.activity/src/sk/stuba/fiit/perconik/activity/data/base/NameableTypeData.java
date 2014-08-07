package sk.stuba.fiit.perconik.activity.data.base;

import sk.stuba.fiit.perconik.core.Nameable;

public class NameableTypeData extends TypeData {
  protected String name;

  public NameableTypeData() {}

  protected NameableTypeData(Nameable nameable) {
    if (nameable == null) {
      return;
    }

    this.setName(nameable.getName());
    this.setImplementation(ClassData.of(nameable.getClass()));
  }

  protected NameableTypeData(String name, Class<?> implementation) {
    super(implementation);

    if (name == null) {
      return;
    }

    this.setName(name);
  }

  public static NameableTypeData of(Nameable nameable) {
    return new NameableTypeData(nameable);
  }

  public static NameableTypeData of(String name, Class<?> implementation) {
    return new NameableTypeData(name, implementation);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
