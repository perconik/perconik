package sk.stuba.fiit.perconik.eclipse.swt;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;

import static java.util.Collections.reverseOrder;

public enum SortDirection {
  UP(SWT.UP) {
    @Override
    public <T extends Comparable<? super T>> void sort(final List<T> list) {
      Collections.sort(list);
    }

    @Override
    public <T> void sort(final List<T> list, final Comparator<? super T> comparator) {
      Collections.sort(list, comparator);
    }
  },

  DOWN(SWT.DOWN) {
    @Override
    public <T extends Comparable<? super T>> void sort(final List<T> list) {
      Collections.sort(list, reverseOrder());
    }

    @Override
    public <T> void sort(final List<T> list, final Comparator<? super T> comparator) {
      Collections.sort(list, reverseOrder(comparator));
    }
  };

  private final int value;

  private SortDirection(final int value) {
    this.value = value;
  }

  public SortDirection opposite() {
    return this == UP ? DOWN : UP;
  }

  public abstract <T extends Comparable<? super T>> void sort(List<T> list);

  public abstract <T> void sort(List<T> list, Comparator<? super T> comparator);

  public int getValue() {
    return this.value;
  }
}
