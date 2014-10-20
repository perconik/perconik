package sk.stuba.fiit.perconik.utilities.reflect.resolver;

enum DefaultClassResolver implements ClassResolver {
  INSTANCE;

  public Class<?> forName(final String name) throws ClassNotFoundException {
    return Class.forName(name);
  }

  @Override
  public String toString() {
    return "DefaultClassResolver";
  }
}
