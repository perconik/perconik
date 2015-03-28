package sk.stuba.fiit.perconik.utilities.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import sk.stuba.fiit.perconik.utilities.reflect.resolver.ClassResolver;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.utilities.MoreThrowables.initializeSuppressor;

public class ClassResolvingObjectInputStream extends ObjectInputStream {
  private final ClassResolver resolver;

  public ClassResolvingObjectInputStream(final ClassResolver resolver, final InputStream in) throws IOException {
    super(in);

    this.resolver = checkNotNull(resolver);
  }

  @Override
  protected Class<?> resolveClass(final ObjectStreamClass type) throws ClassNotFoundException, IOException {
    try {
      return this.resolver.forName(type.getName());
    } catch (ClassNotFoundException suppress) {
      try {
        return super.resolveClass(type);
      } catch (ClassNotFoundException failure) {
        throw initializeSuppressor(failure, suppress);
      }
    }
  }
}
