package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension4;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.TextPresentationListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class TextPresentationHook extends InternalHook<ITextViewer, TextPresentationListener> implements EditorListener {
  TextPresentationHook(final TextPresentationListener listener) {
    super(new TextViewerHandler(listener));
  }

  static final class Support extends AbstractHookSupport<TextPresentationHook, ITextViewer, TextPresentationListener> {
    public Hook<ITextViewer, TextPresentationListener> create(final TextPresentationListener listener) {
      return new TextPresentationHook(listener);
    }
  }

  private static final class TextViewerHandler extends InternalHandler<ITextViewer, TextPresentationListener> {
    TextViewerHandler(final TextPresentationListener listener) {
      super(ITextViewer.class, listener);
    }

    public void register(final ITextViewer viewer) {
      if (viewer instanceof ITextViewerExtension4) {
        ((ITextViewerExtension4) viewer).addTextPresentationListener(this.listener);
      }
    }

    public void unregister(final ITextViewer viewer) {
      if (viewer instanceof ITextViewerExtension4) {
        ((ITextViewerExtension4) viewer).removeTextPresentationListener(this.listener);
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

  public void editorOpened(final IEditorReference reference) {
    Hooks.addNonNull(this, filter(Editors.getTextViewer(Hooks.dereferenceEditor(reference))));
  }

  public final void editorClosed(final IEditorReference reference) {
    Hooks.removeNonNull(this, filter(Editors.getTextViewer(Hooks.dereferenceEditor(reference))));
  }

  public void editorActivated(final IEditorReference reference) {}

  public void editorDeactivated(final IEditorReference reference) {}

  public void editorVisible(final IEditorReference reference) {}

  public void editorHidden(final IEditorReference reference) {}

  public void editorBroughtToTop(final IEditorReference reference) {}

  public void editorInputChanged(final IEditorReference reference) {}
}
