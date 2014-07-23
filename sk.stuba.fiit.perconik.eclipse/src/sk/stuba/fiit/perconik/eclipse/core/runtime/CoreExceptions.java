package sk.stuba.fiit.perconik.eclipse.core.runtime;

import org.eclipse.core.runtime.CoreException;

import static com.google.common.base.Throwables.propagateIfPossible;

public final class CoreExceptions {
  private CoreExceptions() {
    throw new AssertionError();
  }

  public static final RuntimeCoreException propagate(final CoreException failure) {
    propagateIfPossible(failure);

    throw new RuntimeCoreException(failure);
  }
}
