package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import java.util.Comparator;
import java.util.Set;

import javax.annotation.Nullable;

import org.eclipse.swt.widgets.Table;

import static com.google.common.collect.Sets.newLinkedHashSet;

public abstract class SetTableSorter<E> extends TableSorter<E> {
  protected SetTableSorter(final Table table) {
    super(table);
  }

  protected SetTableSorter(final Table table, @Nullable final Comparator<? super E> comparator) {
    super(table, comparator);
  }

  @Override
  public final Set<E> loadData() {
    return this.loadSet();
  }

  @Override
  public final void updateData(final Iterable<? extends E> data) {
    this.updateSet(newLinkedHashSet(data));
  }

  protected abstract Set<E> loadSet();

  protected abstract void updateSet(Set<E> set);
}
