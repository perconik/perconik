package sk.stuba.fiit.perconik.activity.data;

import sk.stuba.fiit.perconik.core.Nameable;

public class NameableData extends ObjectData {
  protected String name;

  public NameableData() {}

  protected NameableData(final Nameable nameable) {
    super(nameable);

    if (nameable == null) {
      return;
    }

    this.setName(nameable.getName());
  }

  protected NameableData(final String name, final Class<?> implementation) {
    super(implementation);

    if (name == null) {
      return;
    }

    this.setName(name);
  }

  public static NameableData of(final Nameable nameable) {
    return new NameableData(nameable);
  }

  public static NameableData of(final String name, final Class<?> implementation) {
    return new NameableData(name, implementation);
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
