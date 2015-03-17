package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.swt.widgets.Table;

import static com.google.common.collect.Lists.newLinkedList;

public abstract class ListTableSorter<E> extends TableSorter<E> {
  protected ListTableSorter(final Table table) {
    super(table);
  }

  protected ListTableSorter(final Table table, @Nullable final Comparator<? super E> comparator) {
    super(table, comparator);
  }

  @Override
  public final List<E> loadData() {
    return this.loadList();
  }

  @Override
  public final void updateData(final Iterable<? extends E> data) {
    this.updateList(newLinkedList(data));
  }

  protected abstract List<E> loadList();

  protected abstract void updateList(List<E> list);
}
