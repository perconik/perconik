package sk.stuba.fiit.perconik.eclipse.jface.viewers;

import java.util.Map.Entry;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import static sk.stuba.fiit.perconik.utilities.MoreStrings.toStringComparator;

public class MapViewerComparator extends ViewerComparator {
  public MapViewerComparator() {}

  @Override
  public int compare(final Viewer viewer, final Object a, final Object b) {
    if ((a instanceof Entry) && (b instanceof Entry)) {
      return toStringComparator().compare(((Entry<?, ?>) a).getKey(), ((Entry<?, ?>) b).getKey());
    }

    return super.compare(viewer, a, b);
  }
}
