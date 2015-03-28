package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.TextInputListener;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferencePart;

final class TextInputHook extends InternalHook<ITextViewer, TextInputListener> implements PartListener {
  TextInputHook(final TextInputListener listener) {
    super(new TextViewerHandler(listener));
  }

  static final class Support extends AbstractHookSupport<TextInputHook, ITextViewer, TextInputListener> {
    public Hook<ITextViewer, TextInputListener> create(final TextInputListener listener) {
      return new TextInputHook(listener);
    }
  }

  private static final class TextInputListenerProxy extends AbstractListenerProxy<TextInputListener> implements ITextInputListener {
    final ITextViewer viewer;

    TextInputListenerProxy(final TextInputListener listener, final ITextViewer viewer) {
      super(listener);

      this.viewer = checkNotNull(viewer);
    }

    public void inputDocumentAboutToBeChanged(final IDocument before, final IDocument after) {
      this.listener.inputDocumentAboutToBeChanged(this.viewer, before, after);
    }

    public void inputDocumentChanged(final IDocument before, final IDocument after) {
      this.listener.inputDocumentChanged(this.viewer, before, after);
    }
  }

  private static final class TextViewerHandler extends InternalHandler<ITextViewer, TextInputListener> {
    TextViewerHandler(final TextInputListener listener) {
      super(ITextViewer.class, listener);
    }

    public void register(final ITextViewer viewer) {
      viewer.addTextInputListener(new TextInputListenerProxy(this.listener, viewer));
    }

    public void unregister(final ITextViewer viewer) {
      viewer.removeTextInputListener(new TextInputListenerProxy(this.listener, viewer));
    }
  }

  @Override
  void preRegisterInternal() {
    Hooks.addTextViewersAsynchronouslyTo(this);
  }

  public void partOpened(final IWorkbenchPartReference reference) {
    Hooks.addNonNull(this, Parts.getTextViewer(dereferencePart(reference)));
  }

  public final void partClosed(final IWorkbenchPartReference reference) {
    Hooks.removeNonNull(this, Parts.getTextViewer(dereferencePart(reference)));
  }

  public void partActivated(final IWorkbenchPartReference reference) {}

  public void partDeactivated(final IWorkbenchPartReference reference) {}

  public void partVisible(final IWorkbenchPartReference reference) {}

  public void partHidden(final IWorkbenchPartReference reference) {}

  public void partBroughtToTop(final IWorkbenchPartReference reference) {}

  public void partInputChanged(final IWorkbenchPartReference reference) {}
}
