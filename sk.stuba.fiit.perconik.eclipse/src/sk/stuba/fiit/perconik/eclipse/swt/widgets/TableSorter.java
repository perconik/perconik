package sk.stuba.fiit.perconik.eclipse.swt.widgets;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import sk.stuba.fiit.perconik.eclipse.swt.SortDirection;

import static com.google.common.base.Preconditions.checkNotNull;

import static com.google.common.collect.Lists.newArrayList;

public abstract class TableSorter<T> {
  static final String key = TableSorter.class.getName();

  private final Table table;

  @Nullable
  private final Comparator<? super T> comparator;

  private boolean enabled;

  protected TableSorter(final Table table) {
    this(table, null);
  }

  protected TableSorter(final Table table, @Nullable final Comparator<? super T> comparator) {
    this.table = checkNotNull(table);
    this.comparator = comparator;
    this.enabled = true;
  }

  public final class Handle {
    private final Switcher switcher;

    Handle(final SortDirection direction) {
      this.switcher = new Switcher(direction);
    }

    private final class Switcher implements Listener {
      SortDirection direction;

      Switcher(final SortDirection direction) {
        this.direction = checkNotNull(direction);
      }

      public void handleEvent(final Event event) {
        sort((TableColumn) event.widget, this.direction);

        this.direction = this.direction.opposite();
      }
    }

    public Handle attach(final TableColumn column) {
      column.addListener(SWT.Selection, this.switcher);
      column.setData(key, TableSorter.this);

      return this;
    }

    public Handle detach(final TableColumn column) {
      column.removeListener(SWT.Selection, this.switcher);
      column.setData(key, null);

      return this;
    }
  }

  public final Handle attach(final TableColumn column) {
    return this.attach(column, SortDirection.UP);
  }

  public final Handle attach(final TableColumn column, final SortDirection direction) {
    return new Handle(direction).attach(column);
  }

  private static TableSorter<?> fetch(final TableColumn column) {
    return ((TableSorter<?>) column.getData(key));
  }

  private static final void attachedSort(final TableColumn column, final SortDirection direction) {
    fetch(column).sort(column, direction);
  }

  public static final void automaticSort(final Table table) {
    automaticSort(table, table.getColumn(0), SortDirection.UP);
  }

  public static final void automaticSort(final Table table, final TableColumn defaultColumn, final SortDirection defaultDirection) {
    TableColumn column = table.getSortColumn();
    SortDirection direction = SortDirection.valueOf(table.getSortDirection());

    attachedSort(column != null ? column : defaultColumn, direction != SortDirection.NONE ? direction : defaultDirection);

    if (column == null) {
      table.setSortColumn(null);
    }

    table.setSortDirection(direction.getValue());
  }

  public static final void enable(final Table table, final boolean value) {
    for (TableColumn column: table.getColumns()) {
      TableSorter<?> sorter = fetch(column);

      if (sorter != null) {
        sorter.setEnabled(value);
      }
    }
  }

  public static final void enable(final TableColumn column, final boolean value) {
    fetch(column).setEnabled(value);
  }

  public final void sort(final TableColumn column, final SortDirection direction) {
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

  public abstract Iterable<T> loadData();

  public abstract void updateData(Iterable<? extends T> data);

  public final void setEnabled(final boolean value) {
    this.enabled = value;
  }

  public final Table getTable() {
    return this.table;
  }

  @Nullable
  public final Comparator<? super T> getComparator() {
    return this.comparator;
  }

  public final boolean isEnabled() {
    return this.enabled;
  }
}
