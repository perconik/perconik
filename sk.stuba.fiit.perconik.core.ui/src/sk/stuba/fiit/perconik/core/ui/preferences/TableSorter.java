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

abstract class TableSorter<T> {
  static final String key = TableSorter.class.getName();

  private final Table table;

  @Nullable
  private final Comparator<? super T> comparator;

  private boolean enabled;

  TableSorter(final Table table) {
    this(table, null);
  }

  TableSorter(final Table table, @Nullable final Comparator<? super T> comparator) {
    this.table = requireNonNull(table);
    this.comparator = comparator;
    this.enabled = true;
  }

  final class Handle implements Listener {
    private SortDirection direction;

    Handle(final SortDirection direction) {
      this.direction = requireNonNull(direction);
    }

    public void handleEvent(final Event event) {
      sort((TableColumn) event.widget, this.direction);

      this.direction = this.direction.opposite();
    }

    void detach(final TableColumn column) {
      column.removeListener(SWT.Selection, this);
      column.setData(key, null);
    }
  }

  final Handle attach(final TableColumn column) {
    return this.attach(column, SortDirection.UP);
  }

  final Handle attach(final TableColumn column, final SortDirection direction) {
    Handle handle = new Handle(direction);

    column.addListener(SWT.Selection, handle);
    column.setData(key, this);

    return handle;
  }

  private static TableSorter<?> fetch(final TableColumn column) {
    return ((TableSorter<?>) column.getData(key));
  }

  static final void attachedSort(final TableColumn column, final SortDirection direction) {
    fetch(column).sort(column, direction);
  }

  static final void automaticSort(final Table table) {
    automaticSort(table, table.getColumn(0), SortDirection.UP);
  }

  static final void automaticSort(final Table table, final TableColumn defaultColumn, final SortDirection defaultDirection) {
    TableColumn column = table.getSortColumn();
    SortDirection direction = SortDirection.valueOf(table.getSortDirection());

    attachedSort(column != null ? column : defaultColumn, direction != SortDirection.NONE ? direction : defaultDirection);

    if (column == null) {
      table.setSortColumn(null);
    }

    table.setSortDirection(direction.getValue());
  }

  static final void enable(final Table table, final boolean value) {
    for (TableColumn column: table.getColumns()) {
      TableSorter<?> sorter = fetch(column);

      if (sorter != null) {
        sorter.setEnabled(value);
      }
    }
  }

  static final void enable(final TableColumn column, final boolean value) {
    fetch(column).setEnabled(value);
  }

  final void sort(final TableColumn column, final SortDirection direction) {
    if (!this.isEnabled()) {
      return;
    }

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

  final void setEnabled(final boolean value) {
    this.enabled = value;
  }

  final Table getTable() {
    return this.table;
  }

  @Nullable
  final Comparator<? super T> getComparator() {
    return this.comparator;
  }

  final boolean isEnabled() {
    return this.enabled;
  }
}
