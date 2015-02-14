package sk.stuba.fiit.perconik.utilities.concurrent;

import com.google.common.base.Supplier;

import static java.util.Objects.requireNonNull;

import static com.google.common.base.Suppliers.ofInstance;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.requireNonNullOrEmpty;

public abstract class NamedRunnable implements Runnable {
  private final Supplier<String> toString;

  protected NamedRunnable(final Class<?> identity) {
    this(new Supplier<String>() {
      public String get() {
        return identity.getName();
      }
    });
  }

  protected NamedRunnable(final String name) {
    this(ofInstance(name));
  }

  protected NamedRunnable(final Supplier<String> supplier) {
    this.toString = requireNonNull(supplier);
  }

  @Override
  public final String toString() {
    return requireNonNullOrEmpty(this.toString.get());
  }
}
