package sk.stuba.fiit.perconik.eclipse.jdt.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;

import sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions;

public final class JavaExceptions {
  private JavaExceptions() {}

  public static RuntimeException propagate(final CoreException failure) {
    if (failure instanceof JavaModelException) {
      throw new JavaException(failure);
    }

    return CoreExceptions.propagate(failure);
  }
}
