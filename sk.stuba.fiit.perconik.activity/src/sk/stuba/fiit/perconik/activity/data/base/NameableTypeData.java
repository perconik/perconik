package sk.stuba.fiit.perconik.activity.data.base;

import sk.stuba.fiit.perconik.core.Nameable;

public class NameableTypeData extends TypeData {
  protected String name;

  public NameableTypeData() {}

  protected NameableTypeData(final Nameable nameable) {
    if (nameable == null) {
      return;
    }

    this.setName(nameable.getName());
    this.setImplementation(ClassData.of(nameable.getClass()));
  }

  protected NameableTypeData(final String name, final Class<?> implementation) {
    super(implementation);

    if (name == null) {
      return;
    }

    this.setName(name);
  }

  public static NameableTypeData of(final Nameable nameable) {
    return new NameableTypeData(nameable);
  }

  public static NameableTypeData of(final String name, final Class<?> implementation) {
    return new NameableTypeData(name, implementation);
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
