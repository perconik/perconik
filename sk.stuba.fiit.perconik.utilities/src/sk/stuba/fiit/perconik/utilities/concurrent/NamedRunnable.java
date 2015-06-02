package sk.stuba.fiit.perconik.utilities.concurrent;

import com.google.common.base.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Suppliers.ofInstance;

import static sk.stuba.fiit.perconik.utilities.MorePreconditions.checkNotNullOrEmpty;

public abstract class NamedRunnable implements Runnable {
  private final Supplier<String> toString;

  protected NamedRunnable(final Class<?> identity) {
    this(ofInstance(identity.getName()));
  }

  protected NamedRunnable(final Class<?> identity, final String name) {
    this(ofInstance(identity.getName() + "$" + checkNotNullOrEmpty(name)));
  }

  protected NamedRunnable(final String name) {
    this(ofInstance(name));
  }

  protected NamedRunnable(final Supplier<String> supplier) {
    this.toString = checkNotNull(supplier);
  }

  @Override
  public final String toString() {
    return checkNotNullOrEmpty(this.toString.get());
  }
}
