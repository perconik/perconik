package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Set;

import org.eclipse.swt.widgets.Table;

import static com.google.common.collect.Sets.newLinkedHashSet;

abstract class SetTableSorter<E> extends AbstractTableSorter<E> {
  SetTableSorter(final Table table) {
    super(table);
  }

  @Override
  final Set<E> loadData() {
    return this.loadSet();
  }

  @Override
  final void updateData(final Iterable<? extends E> data) {
    this.updateSet(newLinkedHashSet(data));
  }

  abstract Set<E> loadSet();

  abstract void updateSet(Set<E> set);
}
