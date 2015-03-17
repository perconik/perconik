package sk.stuba.fiit.perconik.ui;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public final class TableColumns {
  private TableColumns() {}

  public static TableColumn create(final Table table, final TableColumnLayout layout, final String text, final GC gc, final int weight) {
    TableColumn column = new TableColumn(table, SWT.NONE);

    column.setText(text);

    ColumnWeightData data = new ColumnWeightData(weight, getMinimumWidth(gc, column.getText()), true);

    layout.setColumnData(column, data);

    return column;
  }

  public static int getMinimumWidth(final GC gc, final String s) {
    return gc.stringExtent(s).x + 10;
  }
}
