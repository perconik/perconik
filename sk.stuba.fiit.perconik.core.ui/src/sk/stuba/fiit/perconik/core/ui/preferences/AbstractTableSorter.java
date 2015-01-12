package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import sk.stuba.fiit.perconik.eclipse.swt.SortDirection;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Lists.newLinkedList;

abstract class AbstractTableSorter<T> {
  private final Table table;

  AbstractTableSorter(final Table table) {
    this.table = requireNonNull(table);
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

  abstract Comparator<? super T> comparator();

  final void sort(final TableColumn column, final SortDirection direction) {
    List<T> data = newLinkedList(this.loadData());

    direction.sort(data, this.comparator());

    this.updateData(data);

    this.table.setSortColumn(column);
    this.table.setSortDirection(direction.getValue());
  }

  abstract Iterable<T> loadData();

  abstract void updateData(List<T> data);
}
