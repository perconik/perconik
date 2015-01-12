package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Collection;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import static java.util.Collections.emptyList;

final class CollectionContentProvider implements IStructuredContentProvider {
  private Collection<?> data;

  CollectionContentProvider() {
    this.data = emptyList();
  }

  public Object[] getElements(final Object input) {
    return this.data.toArray();
  }

  public void inputChanged(final Viewer viewer, final Object from, final Object to) {
    this.data = (Set<?>) to;
  }

  @Override
  public void dispose() {
    this.data = null;
  }
}
