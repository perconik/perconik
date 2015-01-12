package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import static java.lang.String.valueOf;

final class MapLabelProvider extends LabelProvider implements ITableLabelProvider {
  MapLabelProvider() {}

  public String getColumnText(final Object element, final int column) {
    Entry<?, ?> entry = (Entry<?, ?>) element;

    switch (column) {
      case 0:
        return valueOf(entry.getKey());

      case 1:
        return valueOf(entry.getValue());

      default:
        throw new IllegalStateException();
    }
  }

  public Image getColumnImage(final Object element, final int columnIndex) {
    return null;
  }
}
