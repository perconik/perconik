package sk.stuba.fiit.perconik.utilities.reflect.resolver;

public interface ClassResolver {
  public Class<?> forName(final String name) throws ClassNotFoundException;
}
