package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.TextListener;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferencePart;

final class TextHook extends InternalHook<ITextViewer, TextListener> implements PartListener {
  TextHook(final TextListener listener) {
    super(new TextViewerHandler(listener));
  }

  static final class Support extends AbstractHookSupport<TextHook, ITextViewer, TextListener> {
    public Hook<ITextViewer, TextListener> create(final TextListener listener) {
      return new TextHook(listener);
    }
  }

  private static final class TextListenerProxy extends AbstractListenerProxy<TextListener> implements ITextListener {
    final ITextViewer viewer;

    TextListenerProxy(final TextListener listener, final ITextViewer viewer) {
      super(listener);

      this.viewer = requireNonNull(viewer);
    }

    public void textChanged(final TextEvent event) {
      this.listener.textChanged(this.viewer, event);
    }
  }

  private static final class TextViewerHandler extends InternalHandler<ITextViewer, TextListener> {
    TextViewerHandler(final TextListener listener) {
      super(ITextViewer.class, listener);
    }

    public void register(final ITextViewer viewer) {
      viewer.addTextListener(new TextListenerProxy(this.listener, viewer));
    }

    public void unregister(final ITextViewer viewer) {
      viewer.removeTextListener(new TextListenerProxy(this.listener, viewer));
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
