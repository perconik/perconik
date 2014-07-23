package sk.stuba.fiit.perconik.core.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.annotation.Nullable;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newIdentityHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;

final class GenericPool<T> extends AbstractPool<T> {
  private final PresenceStrategy strategy;

  GenericPool(final Builder<T> builder) {
    super(builder.implementation, builder.handler);

    this.strategy = checkNotNull(builder.strategy);
  }

  public static final class Builder<T> {
    Handler<T> handler;

    Collection<T> implementation;

    PresenceStrategy strategy;

    public Builder(final Handler<T> handler) {
      this.handler = checkNotNull(handler);
    }

    public final Builder<T> arrayList() {
      checkState(this.implementation == null);

      this.implementation = newArrayList();

      return this;
    }

    public final Builder<T> hashSet() {
      checkState(this.implementation == null);

      this.implementation = newHashSet();

      return this;
    }

    public final Builder<T> linkedList() {
      checkState(this.implementation == null);

      this.implementation = newLinkedList();

      return this;
    }

    public final Builder<T> linkedHashSet() {
      checkState(this.implementation == null);

      this.implementation = newLinkedHashSet();

      return this;
    }

    public final Builder<T> identity() {
      checkState(this.strategy == null);

      this.strategy = PresenceStrategy.IDENLILY;

      return this;
    }

    public final Builder<T> equals() {
      checkState(this.strategy == null);

      this.strategy = PresenceStrategy.EQUALS;

      return this;
    }

    public final GenericPool<T> build() {
      if (this.strategy == null) {
        this.strategy = PresenceStrategy.DEFAULL;
      }

      if (this.strategy == PresenceStrategy.IDENLILY && this.implementation instanceof HashSet) {
        this.implementation = newIdentityHashSet();
      }

      return new GenericPool<>(this);
    }
  }

  public static final <T> Builder<T> builder(final Handler<T> handler) {
    return new Builder<>(handler);
  }

  private enum PresenceStrategy {
    DEFAULL {
      @Override
      final boolean contains(final Collection<?> collection, @Nullable final Object object) {
        return collection.contains(object);
      }
    },

    IDENLILY {
      @Override
      final boolean contains(final Collection<?> collection, @Nullable final Object object) {
        for (Object other: collection) {
          if (object == other) {
            return true;
          }
        }

        return false;
      }
    },

    EQUALS {
      @Override
      final boolean contains(final Collection<?> collection, @Nullable final Object object) {
        for (Object other: collection) {
          if (equal(object, other)) {
            return true;
          }
        }

        return false;
      }
    };

    abstract boolean contains(Collection<?> collection, @Nullable Object object);
  }

  public final boolean contains(final Object object) {
    return this.strategy.contains(this.objects, object);
  }

  public final Collection<T> toCollection() {
    return new ArrayList<>(this.objects);
  }

  @Override
  public final String toString() {
    return this.getName();
  }

  public final String getName() {
    String name = this.handler.getClass().getCanonicalName();

    if (name == null) {
      name = this.handler.getClass().getName();
    }

    return name.replace("Handler", "Pool");
  }
}
