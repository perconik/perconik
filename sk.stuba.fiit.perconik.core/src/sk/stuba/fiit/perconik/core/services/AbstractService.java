package sk.stuba.fiit.perconik.core.services;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import sk.stuba.fiit.perconik.utilities.MoreStrings;
import sk.stuba.fiit.perconik.utilities.SmartStringBuilder;

/**
 * An abstract implementation of {@link Service}
 * interface covering service name and equivalence.
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public abstract class AbstractService extends com.google.common.util.concurrent.AbstractService implements Service {
  /**
   * Constructor for use by subclasses.
   */
  protected AbstractService() {}

  protected final void checkState(final State first, final State ... rest) {
    this.checkState(EnumSet.of(first, rest));
  }

  protected final void checkState(final Set<State> states) {
    if (!states.contains(this.state())) {
      SmartStringBuilder builder = new SmartStringBuilder();

      builder.append(this.toString()).append(" must be in state ");

      Function<State, String> toLowerCase = new Function<State, String>() {
        public final String apply(@Nonnull final State state) {
          return state.toString().toLowerCase();
        }
      };

      List<State> list = Lists.newArrayList(states);

      Collections.sort(list, MoreStrings.toStringComparator());

      builder.list(Lists.transform(list, toLowerCase));

      throw new IllegalStateException(builder.toString());
    }
  }

  protected final void checkRunning() {
    this.checkState(State.RUNNING);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(@Nullable final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Service)) {
      return false;
    }

    Service other = (Service) o;

    return this.getName().equals(other.getName());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int hashCode() {
    return this.getName().hashCode();
  }

  /**
   * Converts service to string consisting of its name and operational
   * status. More formally, the returned string is a concatenation of
   * service name, space and service operational status in lowercase
   * enclosed in square brackets.
   */
  @Override
  public final String toString() {
    return this.getName() + " [" + this.state().toString().toLowerCase() + "]";
  }

  /**
   * Returns service name.
   */
  public final String getName() {
    return this.getClass().getName();
  }
}
