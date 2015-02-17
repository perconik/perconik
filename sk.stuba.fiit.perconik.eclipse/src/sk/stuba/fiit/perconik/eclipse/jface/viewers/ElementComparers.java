package sk.stuba.fiit.perconik.eclipse.jface.viewers;

import com.google.common.base.Equivalence;

import org.eclipse.jface.viewers.IElementComparer;

public final class ElementComparers {
  private ElementComparers() {}

  public static IElementComparer fromEquivalence(final Equivalence<Object> equivalence) {
    return new IElementComparer() {
      public boolean equals(final Object a, final Object b) {
        return equivalence.equivalent(equivalence, b);
      }

      public int hashCode(final Object o) {
        return equivalence.hash(o);
      }
    };
  }

  public static Equivalence<Object> toEquivalence(final IElementComparer comparer) {
    return new Equivalence<Object>() {
      @Override
      protected boolean doEquivalent(final Object a, final Object b) {
        return comparer.equals(a, b);
      }

      @Override
      protected int doHash(final Object o) {
        return comparer.hashCode(o);
      }
    };
  }
}
