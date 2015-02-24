package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextPresentationListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension4;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.TextPresentationListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferenceEditor;

final class TextPresentationHook extends InternalHook<ITextViewer, TextPresentationListener> implements EditorListener {
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

      this.viewer = requireNonNull(viewer);
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

  public void editorOpened(final IEditorReference reference) {
    Hooks.addNonNull(this, filter(Editors.getTextViewer(dereferenceEditor(reference))));
  }

  public final void editorClosed(final IEditorReference reference) {
    Hooks.removeNonNull(this, filter(Editors.getTextViewer(dereferenceEditor(reference))));
  }

  public void editorActivated(final IEditorReference reference) {}

  public void editorDeactivated(final IEditorReference reference) {}

  public void editorVisible(final IEditorReference reference) {}

  public void editorHidden(final IEditorReference reference) {}

  public void editorBroughtToTop(final IEditorReference reference) {}

  public void editorInputChanged(final IEditorReference reference) {}
}
