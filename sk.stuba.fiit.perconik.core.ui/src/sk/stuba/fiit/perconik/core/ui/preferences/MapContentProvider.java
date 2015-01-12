package sk.stuba.fiit.perconik.core.ui.preferences;

import java.util.Map;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import static java.util.Collections.emptyMap;

final class MapContentProvider implements IStructuredContentProvider {
  private Map<?, ?> data;

  MapContentProvider() {
    this.data = emptyMap();
  }

  public Object[] getElements(final Object input) {
    return this.data.entrySet().toArray();
  }

  public void inputChanged(final Viewer viewer, final Object from, final Object to) {
    this.data = (Map<?, ?>) to;
  }

  public void dispose() {
    this.data = null;
  }
}
