package sk.stuba.fiit.perconik.eclipse.jface.viewers;

import java.util.Map;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import static java.util.Collections.emptyMap;

public class MapContentProvider implements IStructuredContentProvider {
  protected Map<?, ?> data;

  public MapContentProvider() {
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
