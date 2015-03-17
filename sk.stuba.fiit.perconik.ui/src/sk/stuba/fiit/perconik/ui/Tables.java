package sk.stuba.fiit.perconik.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public final class Tables {
  private Tables() {}

  public static Table create(final Composite parent, final int style) {
    Table table = new Table(parent, style);

    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    return table;
  }
}
