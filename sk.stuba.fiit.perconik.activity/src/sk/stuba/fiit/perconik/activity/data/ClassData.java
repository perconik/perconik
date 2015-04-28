package sk.stuba.fiit.perconik.activity.data;

public class ClassData extends AnnotableData {
  protected ClassNameData name;

  public ClassData() {}

  protected ClassData(final Class<?> type) {
    super(type);

    this.setName(ClassNameData.of(type));
  }

  public static ClassData of(final Object object) {
    return of(object.getClass());
  }

  public static ClassData of(final Class<?> type) {
    return new ClassData(type);
  }

  public void setName(final ClassNameData name) {
    this.name = name;
  }

  public ClassNameData getName() {
    return this.name;
  }
}
