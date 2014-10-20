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

    public Builder<T> arrayList() {
      checkState(this.implementation == null);

      this.implementation = newArrayList();

      return this;
    }

    public Builder<T> hashSet() {
      checkState(this.implementation == null);

      this.implementation = newHashSet();

      return this;
    }

    public Builder<T> linkedList() {
      checkState(this.implementation == null);

      this.implementation = newLinkedList();

      return this;
    }

    public Builder<T> linkedHashSet() {
      checkState(this.implementation == null);

      this.implementation = newLinkedHashSet();

      return this;
    }

    public Builder<T> identity() {
      checkState(this.strategy == null);

      this.strategy = PresenceStrategy.IDENTITY;

      return this;
    }

    public Builder<T> equals() {
      checkState(this.strategy == null);

      this.strategy = PresenceStrategy.EQUALS;

      return this;
    }

    public GenericPool<T> build() {
      if (this.strategy == null) {
        this.strategy = PresenceStrategy.DEFAULT;
      }

      if (this.strategy == PresenceStrategy.IDENTITY && this.implementation instanceof HashSet) {
        this.implementation = newIdentityHashSet();
      }

      return new GenericPool<>(this);
    }
  }

  public static <T> Builder<T> builder(final Handler<T> handler) {
    return new Builder<>(handler);
  }

  private enum PresenceStrategy {
    DEFAULT {
      @Override
      boolean contains(final Collection<?> collection, @Nullable final Object object) {
        return collection.contains(object);
      }
    },

    IDENTITY {
      @Override
      boolean contains(final Collection<?> collection, @Nullable final Object object) {
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
      boolean contains(final Collection<?> collection, @Nullable final Object object) {
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

  public boolean contains(final Object object) {
    return this.strategy.contains(this.objects, object);
  }

  public Collection<T> toCollection() {
    return new ArrayList<>(this.objects);
  }

  @Override
  public String toString() {
    return this.getName();
  }

  public String getName() {
    String name = this.handler.getClass().getCanonicalName();

    if (name == null) {
      name = this.handler.getClass().getName();
    }

    return name.replace("Handler", "Pool");
  }
}
