package sk.stuba.fiit.perconik.activity.data.base;

public class ClassData extends AnnotableData {
  protected String name;

  protected String simpleName;

  protected String canonicalName;

  public ClassData() {}

  protected ClassData(Class<?> type) {
    super(type);

    if (type == null) {
      return;
    }

    this.setName(type.getName());
    this.setSimpleName(type.getSimpleName());
    this.setCanonicalName(type.getCanonicalName());
  }

  public static ClassData of(Class<?> type) {
    return new ClassData(type);
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCanonicalName(String canonicalName) {
    this.canonicalName = canonicalName;
  }

  public void setSimpleName(String simpleName) {
    this.simpleName = simpleName;
  }

  public String getName() {
    return this.name;
  }

  public String getCanonicalName() {
    return this.canonicalName;
  }

  public String getSimpleName() {
    return this.simpleName;
  }
}
