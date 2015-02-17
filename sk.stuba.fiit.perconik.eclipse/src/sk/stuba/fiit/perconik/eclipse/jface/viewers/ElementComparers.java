package sk.stuba.fiit.perconik.eclipse.jface.viewers;

import java.io.Serializable;

import com.google.common.base.Equivalence;

import org.eclipse.jface.viewers.IElementComparer;

import static java.util.Objects.requireNonNull;

public final class ElementComparers {
  private ElementComparers() {}

  private static final class EquivalenceElementComparer implements IElementComparer, Serializable {
    private static final long serialVersionUID = 0L;

    final Equivalence<Object> equivalence;

    EquivalenceElementComparer(final Equivalence<Object> equivalence) {
      this.equivalence = requireNonNull(equivalence);
    }

    public boolean equals(final Object a, final Object b) {
      return this.equivalence.equivalent(a, b);
    }

    public int hashCode(final Object o) {
      return this.equivalence.hash(o);
    }
  }

  private static final class ElementComparerEquivalence extends Equivalence<Object> implements Serializable {
    private static final long serialVersionUID = 0L;

    final IElementComparer comparer;

    ElementComparerEquivalence(final IElementComparer comparer) {
      this.comparer = requireNonNull(comparer);
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

  public static IElementComparer fromEquivalence(final Equivalence<Object> equivalence) {
    if (equivalence instanceof ElementComparerEquivalence) {
      return ((ElementComparerEquivalence) equivalence).comparer;
    }

    return new EquivalenceElementComparer(equivalence);
  }

  public static Equivalence<Object> toEquivalence(final IElementComparer comparer) {
    if (comparer instanceof EquivalenceElementComparer) {
      return ((EquivalenceElementComparer) comparer).equivalence;
    }

    return new ElementComparerEquivalence(comparer);
  }
}
