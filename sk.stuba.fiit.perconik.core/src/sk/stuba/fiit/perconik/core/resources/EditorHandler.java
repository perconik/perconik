package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;

enum EditorHandler implements Handler<EditorListener> {
  INSTANCE;

  private static final class PartFilter extends InternalFilter<EditorListener> implements PartListener {
    public PartFilter(final EditorListener listener) {
      super(listener);
    }

    public void partOpened(final IWorkbenchPartReference reference) {
      if (reference instanceof IEditorReference) {
        this.listener.editorOpened((IEditorReference) reference);
      }
    }

    public void partClosed(final IWorkbenchPartReference reference) {
      if (reference instanceof IEditorReference) {
        this.listener.editorClosed((IEditorReference) reference);
      }
    }

    public void partActivated(final IWorkbenchPartReference reference) {
      if (reference instanceof IEditorReference) {
        this.listener.editorActivated((IEditorReference) reference);
      }
    }

    public void partDeactivated(final IWorkbenchPartReference reference) {
      if (reference instanceof IEditorReference) {
        this.listener.editorDeactivated((IEditorReference) reference);
      }
    }

    public void partVisible(final IWorkbenchPartReference reference) {
      if (reference instanceof IEditorReference) {
        this.listener.editorVisible((IEditorReference) reference);
      }
    }

    public void partHidden(final IWorkbenchPartReference reference) {
      if (reference instanceof IEditorReference) {
        this.listener.editorHidden((IEditorReference) reference);
      }
    }

    public void partBroughtToTop(final IWorkbenchPartReference reference) {
      if (reference instanceof IEditorReference) {
        this.listener.editorBroughtToTop((IEditorReference) reference);
      }
    }

    public void partInputChanged(final IWorkbenchPartReference reference) {
      if (reference instanceof IEditorReference) {
        this.listener.editorInputChanged((IEditorReference) reference);
      }
    }
  }

  public void register(final EditorListener listener) {
    PartHandler.INSTANCE.register(new PartFilter(listener));
  }

  public void unregister(final EditorListener listener) {
    PartHandler.INSTANCE.unregister(new PartFilter(listener));
  }
}
