package sk.stuba.fiit.perconik.activity.data;

public class ClassData extends AnnotableData {
  protected NameData name;

  public ClassData() {}

  protected ClassData(final Class<?> type) {
    super(type);

    this.setName(NameData.of(type));
  }

  public static ClassData of(final Class<?> type) {
    return new ClassData(type);
  }

  public void setName(final NameData name) {
    this.name = name;
  }

  public NameData getName() {
    return this.name;
  }
}
