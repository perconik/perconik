package sk.stuba.fiit.perconik.eclipse.jface.viewers;

import org.eclipse.jface.viewers.ViewerComparator;

public class SortingViewerComparator extends ViewerComparator {
  public SortingViewerComparator() {}

  @Override
  public boolean isSorterProperty(final Object element, final String property) {
    return true;
  }
}
