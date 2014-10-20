package sk.stuba.fiit.perconik.core.java;

import com.google.common.base.Throwables;

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

  static <T> T handle(final Exception failure) {
    if (failure instanceof JavaModelException) {
      throw new JavaException(failure);
    }

    if (failure instanceof NullPointerException) {
      return null;
    }

    throw Throwables.propagate(failure);
  }
}
