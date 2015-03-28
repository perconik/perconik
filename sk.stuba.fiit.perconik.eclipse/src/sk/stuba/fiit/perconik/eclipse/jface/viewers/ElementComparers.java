package sk.stuba.fiit.perconik.eclipse.jface.viewers;

import java.io.Serializable;

import com.google.common.base.Equivalence;
import com.google.common.reflect.TypeToken;

import org.eclipse.jface.viewers.IElementComparer;

import static com.google.common.base.Preconditions.checkNotNull;

public final class ElementComparers {
  private ElementComparers() {}

  private static final class EquivalenceElementComparer<T> implements IElementComparer, Serializable {
    private static final long serialVersionUID = 0L;

    final TypeToken<T> type;

    final Equivalence<T> equivalence;

    EquivalenceElementComparer(final TypeToken<T> type, final Equivalence<T> equivalence) {
      this.type = checkNotNull(type);
      this.equivalence = checkNotNull(equivalence);
    }

    // casts are unsafe but proper equivalence should fail on incorrectly typed objects
    @SuppressWarnings("unchecked")
    public boolean equals(final Object a, final Object b) {
      return this.equivalence.equivalent((T) a, (T) b);
    }

    // see above note
    @SuppressWarnings("unchecked")
    public int hashCode(final Object o) {
      return this.equivalence.hash((T) o);
    }
  }

  private static final class ElementComparerEquivalence<T> extends Equivalence<T> implements Serializable {
    private static final long serialVersionUID = 0L;

    final TypeToken<T> type;

    final IElementComparer comparer;

    ElementComparerEquivalence(final TypeToken<T> type, final IElementComparer comparer) {
      this.type = checkNotNull(type);
      this.comparer = checkNotNull(comparer);
    }

    @Override
    protected boolean doEquivalent(final Object a, final Object b) {
      return this.comparer.equals(a, b);
    }

    @Override
    protected int doHash(final Object o) {
      return this.comparer.hashCode(o);
    }
  }

  public static <T> IElementComparer fromEquivalence(final Class<T> type, final Equivalence<T> equivalence) {
    return fromEquivalence(TypeToken.of(type), equivalence);
  }

  public static <T> IElementComparer fromEquivalence(final TypeToken<T> type, final Equivalence<T> equivalence) {
    if (equivalence instanceof ElementComparerEquivalence) {
      ElementComparerEquivalence<T> inner = (ElementComparerEquivalence<T>) equivalence;

      if (inner.type.isAssignableFrom(type)) {
        return inner.comparer;
      }
    }

    return new EquivalenceElementComparer<>(type, equivalence);
  }

  public static <T> Equivalence<T> toEquivalence(final Class<T> type, final IElementComparer comparer) {
    return toEquivalence(TypeToken.of(type), comparer);
  }

  public static <T> Equivalence<T> toEquivalence(final TypeToken<T> type, final IElementComparer comparer) {
    if (comparer instanceof EquivalenceElementComparer) {
      @SuppressWarnings("unchecked")
      EquivalenceElementComparer<T> inner = (EquivalenceElementComparer<T>) comparer;

      if (inner.type.isAssignableFrom(type)) {
        return inner.equivalence;
      }
    }

    return new ElementComparerEquivalence<>(type, comparer);
  }
}
