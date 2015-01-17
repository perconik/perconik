package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.swt.widgets.Table;

import static com.google.common.collect.Lists.newLinkedList;

abstract class ListTableSorter<E> extends TableSorter<E> {
  ListTableSorter(final Table table) {
    super(table);
  }

  ListTableSorter(final Table table, @Nullable final Comparator<? super E> comparator) {
    super(table, comparator);
  }

  @Override
  final List<E> loadData() {
    return this.loadList();
  }

  @Override
  final void updateData(final Iterable<? extends E> data) {
    this.updateList(newLinkedList(data));
  }

  abstract List<E> loadList();

  abstract void updateList(List<E> list);
}
