package sk.stuba.fiit.perconik.core.ui.preferences;

import org.eclipse.jface.viewers.ViewerComparator;

abstract class AbstractViewerComparator extends ViewerComparator {
  AbstractViewerComparator() {}

  @Override
  public boolean isSorterProperty(final Object element, final String property) {
    return true;
  }
}
