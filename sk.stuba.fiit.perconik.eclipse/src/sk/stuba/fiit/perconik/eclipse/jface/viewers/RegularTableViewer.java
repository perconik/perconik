package sk.stuba.fiit.perconik.eclipse.jface.viewers;

import java.util.Set;

import com.google.common.base.Equivalence;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

import static java.util.Arrays.asList;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.eclipse.jface.viewers.ElementComparers.toEquivalence;
import static sk.stuba.fiit.perconik.utilities.MoreSets.newHashSet;

public class RegularTableViewer extends CheckboxTableViewer {
  protected final String grayStateKey;

  protected int normalColor;

  protected int grayColor;

  public RegularTableViewer(final Table table) {
    super(table);

    this.grayStateKey = RegularTableViewer.class.getName() + ".grayed";

    this.normalColor = SWT.COLOR_LIST_FOREGROUND;
    this.grayColor = SWT.COLOR_GRAY;
  }

  protected void setGrayedItem(final TableItem item, final boolean state) {
    item.setData(this.grayStateKey, state);
    item.setForeground(item.getDisplay().getSystemColor(state ? this.grayColor : this.normalColor));
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
    Widget widget = this.findItem(checkNotNull(element));

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
    Equivalence<Object> equivalence = this.getEquivalence();
    Set<Equivalence.Wrapper<Object>> set = newHashSet(equivalence, asList(elements));

    for (TableItem item: this.getTable().getItems()) {
      Object element = item.getData();

      if (element != null) {
        boolean state = set.contains(equivalence.wrap(element));

        if (getGrayedItem(item) != state) {
          setGrayedItem(item, state);
        }
      }
    }
  }

  protected boolean getGrayedItem(final TableItem item) {
    Object value = item.getData(this.grayStateKey);

    return value != null ? (boolean) value : false;
  }

  public Equivalence<Object> getEquivalence() {
    return this.getComparer() != null ? toEquivalence(Object.class, this.getComparer()) : Equivalence.equals();
  }
}
