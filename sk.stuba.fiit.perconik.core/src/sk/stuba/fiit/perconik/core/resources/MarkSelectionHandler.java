package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.IMarkSelection;
import org.eclipse.jface.text.MarkSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import sk.stuba.fiit.perconik.core.listeners.MarkSelectionListener;
import sk.stuba.fiit.perconik.core.listeners.SelectionListener;

enum MarkSelectionHandler implements Handler<MarkSelectionListener> {
  INSTANCE;

  private static final class SelectionFilter extends InternalFilter<MarkSelectionListener> implements SelectionListener {
    public SelectionFilter(final MarkSelectionListener listener) {
      super(listener);
    }

    public final void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
      if (selection instanceof MarkSelection) {
        this.listener.selectionChanged(part, (IMarkSelection) selection);
      }
    }
  }

  public final void register(final MarkSelectionListener listener) {
    SelectionHandler.INSTANCE.register(new SelectionFilter(listener));
  }

  public final void unregister(final MarkSelectionListener listener) {
    SelectionHandler.INSTANCE.unregister(new SelectionFilter(listener));
  }
}
