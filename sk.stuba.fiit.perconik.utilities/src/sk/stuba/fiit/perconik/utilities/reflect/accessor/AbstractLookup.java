package sk.stuba.fiit.perconik.utilities.reflect.accessor;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import sk.stuba.fiit.perconik.utilities.MoreThrowables;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.propagateIfInstanceOf;
import static com.google.common.collect.Lists.newArrayListWithExpectedSize;
import static com.google.common.collect.Lists.newLinkedList;

abstract class AbstractLookup<T> implements Lookup<T> {
  final List<Accessor<? extends T>> accessors;

  final Optional<RuntimeException> suppression;

  AbstractLookup(AbstractBuilder<T> builder) {
    this.accessors = ImmutableList.copyOf(builder.accessors);

    if (!builder.suppressions.isEmpty()) {
      RuntimeException suppressor = new AccessorConstructionException("Accessor construction failed");

      this.suppression = Optional.of(MoreThrowables.initializeSuppressor(suppressor, Lists.reverse(builder.suppressions)));
    } else {
      this.suppression = Optional.absent();
    }
  }

  static abstract class AbstractBuilder<T> {
    final List<Accessor<? extends T>> accessors;

    final List<Throwable> suppressions;

    public AbstractBuilder() {
      this.accessors = newArrayListWithExpectedSize(8);
      this.suppressions = newArrayListWithExpectedSize(8);
    }

    final void add(Accessor<? extends T> accessor) {
      this.accessors.add(checkNotNull(accessor));
    }

    final void handle(Throwable e) {
      propagateIfInstanceOf(e, NullPointerException.class);

      this.suppressions.add(e);
    }

    public abstract AbstractLookup<T> build();
  }

  public final T get() {
    List<Throwable> suppressions = newLinkedList();

    for (Accessor<? extends T> accessor: this.accessors) {
      try {
        return accessor.get();
      } catch (Throwable e) {
        if (e.getClass() == AccessorInvocationException.class) {
          e = e.getCause();
        }

        suppressions.add(e);
      }
    }

    RuntimeException failure = new AccessorInvocationException("Accessor invocation failed");

    suppressions = Lists.reverse(suppressions);

    if (this.suppression.isPresent()) {
      suppressions.add(this.suppression.get());
    }

    throw MoreThrowables.initializeSuppressor(failure, suppressions);
  }
}
