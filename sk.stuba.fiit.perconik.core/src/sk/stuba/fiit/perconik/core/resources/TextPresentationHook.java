package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextPresentationListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension4;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.core.listeners.TextPresentationListener;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;

import static com.google.common.base.Preconditions.checkNotNull;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferencePart;

final class TextPresentationHook extends InternalHook<ITextViewer, TextPresentationListener> implements PartListener {
  TextPresentationHook(final TextPresentationListener listener) {
    super(new TextViewerHandler(listener));
  }

  static final class Support extends AbstractHookSupport<TextPresentationHook, ITextViewer, TextPresentationListener> {
    public Hook<ITextViewer, TextPresentationListener> create(final TextPresentationListener listener) {
      return new TextPresentationHook(listener);
    }
  }

  private static final class TextPresentationListenerProxy extends AbstractListenerProxy<TextPresentationListener> implements ITextPresentationListener {
    final ITextViewer viewer;

    TextPresentationListenerProxy(final TextPresentationListener listener, final ITextViewer viewer) {
      super(listener);

      this.viewer = checkNotNull(viewer);
    }

    public void applyTextPresentation(final TextPresentation textPresentation) {
      this.listener.applyTextPresentation(this.viewer, textPresentation);
    }
  }

  private static final class TextViewerHandler extends InternalHandler<ITextViewer, TextPresentationListener> {
    TextViewerHandler(final TextPresentationListener listener) {
      super(ITextViewer.class, listener);
    }

    public void register(final ITextViewer viewer) {
      if (viewer instanceof ITextViewerExtension4) {
        ((ITextViewerExtension4) viewer).addTextPresentationListener(new TextPresentationListenerProxy(this.listener, viewer));
      }
    }

    public void unregister(final ITextViewer viewer) {
      if (viewer instanceof ITextViewerExtension4) {
        ((ITextViewerExtension4) viewer).removeTextPresentationListener(new TextPresentationListenerProxy(this.listener, viewer));
      }
    }
  }

  @Override
  void preRegisterInternal() {
    Hooks.addTextViewersAsynchronouslyTo(this);
  }

  private static ITextViewer filter(final ITextViewer viewer) {
    if (viewer instanceof ITextViewerExtension4) {
      return viewer;
    }

    return null;
  }

  public void partOpened(final IWorkbenchPartReference reference) {
    Hooks.addNonNull(this, filter(Parts.getTextViewer(dereferencePart(reference))));
  }

  public final void partClosed(final IWorkbenchPartReference reference) {
    Hooks.removeNonNull(this, filter(Parts.getTextViewer(dereferencePart(reference))));
  }

  public void partActivated(final IWorkbenchPartReference reference) {}

  public void partDeactivated(final IWorkbenchPartReference reference) {}

  public void partVisible(final IWorkbenchPartReference reference) {}

  public void partHidden(final IWorkbenchPartReference reference) {}

  public void partBroughtToTop(final IWorkbenchPartReference reference) {}

  public void partInputChanged(final IWorkbenchPartReference reference) {}
}
