package sk.stuba.fiit.perconik.core.ui.preferences;

import org.eclipse.jface.viewers.ViewerComparator;

abstract class SortingViewerComparator extends ViewerComparator {
  SortingViewerComparator() {}

  @Override
  public boolean isSorterProperty(final Object element, final String property) {
    return true;
  }
}
