package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import static java.util.Collections.emptySet;

final class SetContentProvider implements IStructuredContentProvider {
  private Set<?> data;

  SetContentProvider() {
    this.data = emptySet();
  }

  public Object[] getElements(final Object input) {
    return this.data.toArray();
  }

  public void inputChanged(final Viewer viewer, final Object from, final Object to) {
    this.data = (Set<?>) to;
  }

  public void dispose() {
    this.data = null;
  }
}
