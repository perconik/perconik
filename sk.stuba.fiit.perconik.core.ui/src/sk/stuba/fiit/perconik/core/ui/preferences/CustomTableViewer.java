package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Set;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

import static java.util.Objects.requireNonNull;

import static com.google.common.collect.Sets.newHashSet;

final class CustomTableViewer extends CheckboxTableViewer {
  private static final String key = CustomTableViewer.class.getName() + ".grayed";

  CustomTableViewer(final Table table) {
    super(table);
  }

  private static void setGrayedItem(final TableItem item, final boolean state) {
    item.setData(key, state);
    item.setForeground(item.getDisplay().getSystemColor(state ? SWT.COLOR_DARK_GRAY : SWT.COLOR_LIST_FOREGROUND));
  }

  private static boolean getGrayedItem(final TableItem item) {
    Object value = item.getData(key);

    return value != null ? (boolean) value : false;
  }

  @Override
  public void setAllGrayed(final boolean state) {
    for (TableItem item: this.getTable().getItems()) {
      if (item.getData() != null && getGrayedItem(item) != state) {
        setGrayedItem(item, state);
      }
    }
  }

  @Override
  public boolean setGrayed(final Object element, final boolean state) {
    Widget widget = this.findItem(requireNonNull(element));

    if (widget instanceof TableItem) {
      TableItem item = (TableItem) widget;

      if (getGrayedItem(item) != state) {
        setGrayedItem(item, state);
      }

      return true;
    }

    return false;
  }

  @Override
  public void setGrayedElements(final Object[] elements) {
    Set<Object> set = newHashSet(elements);

    for (TableItem item: this.getTable().getItems()) {
      Object element = item.getData();

      if (element != null) {
        boolean state = set.contains(element);

        if (getGrayedItem(item) != state) {
          setGrayedItem(item, state);
        }
      }
    }
  }
}
