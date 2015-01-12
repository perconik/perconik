package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import sk.stuba.fiit.perconik.eclipse.swt.SortDirection;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Lists.newArrayList;

abstract class AbstractTableSorter<T> {
  private final Table table;

  @Nullable
  private final Comparator<? super T> comparator;

  AbstractTableSorter(final Table table) {
    this(table, null);
  }

  AbstractTableSorter(final Table table, final Comparator<? super T> comparator) {
    this.table = requireNonNull(table);
    this.comparator = comparator;
  }

  private final class Handler implements Listener {
    private SortDirection direction;

    Handler(final SortDirection direction) {
      this.direction = requireNonNull(direction);
    }

    public void handleEvent(final Event event) {
      sort((TableColumn) event.widget, this.direction);

      this.direction = this.direction.opposite();
    }
  }

  final void attach(final TableColumn column, final SortDirection direction) {
    column.addListener(SWT.Selection, new Handler(direction));
  }

  final void sort(final TableColumn column, final SortDirection direction) {
    List<T> data = newArrayList(this.loadData());

    this.directionSort(data, direction, this.comparator);
    this.updateData(data);

    this.table.setSortColumn(column);
    this.table.setSortDirection(direction.getValue());
  }

  @SuppressWarnings("unchecked")
  private void directionSort(final List<T> data, final SortDirection direction, final Comparator<? super T> comparator) {
    if (comparator == null) {
      direction.sort(List.class.cast(data));
    } else {
      direction.sort(data, comparator);
    }
  }

  abstract Iterable<T> loadData();

  abstract void updateData(Iterable<? extends T> data);

  final Table getTable() {
    return this.table;
  }

  @Nullable
  final Comparator<? super T> getComparator() {
    return this.comparator;
  }
}
