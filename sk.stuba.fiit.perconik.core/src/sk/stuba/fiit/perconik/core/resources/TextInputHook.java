package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.TextInputListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class TextInputHook extends InternalHook<ITextViewer, TextInputListener> implements EditorListener {
  TextInputHook(final TextInputListener listener) {
    super(new TextViewerHandler(listener));
  }

  static final class Support extends AbstractHookSupport<TextInputHook, ITextViewer, TextInputListener> {
    public Hook<ITextViewer, TextInputListener> create(final TextInputListener listener) {
      return new TextInputHook(listener);
    }
  }

  private static final class TextViewerHandler extends InternalHandler<ITextViewer, TextInputListener> {
    TextViewerHandler(final TextInputListener listener) {
      super(ITextViewer.class, listener);
    }

    public void register(final ITextViewer viewer) {
      viewer.addTextInputListener(this.listener);
    }

    public void unregister(final ITextViewer viewer) {
      viewer.removeTextInputListener(this.listener);
    }
  }

  @Override
  void preRegisterInternal() {
    Hooks.addTextViewersAsynchronouslyTo(this);
  }

  public void editorOpened(final IEditorReference reference) {
    Hooks.addNonNull(this, Editors.getTextViewer(Hooks.dereferenceEditor(reference)));
  }

  public final void editorClosed(final IEditorReference reference) {
    Hooks.removeNonNull(this, Editors.getTextViewer(Hooks.dereferenceEditor(reference)));
  }

  public void editorActivated(final IEditorReference reference) {}

  public void editorDeactivated(final IEditorReference reference) {}

  public void editorVisible(final IEditorReference reference) {}

  public void editorHidden(final IEditorReference reference) {}

  public void editorBroughtToTop(final IEditorReference reference) {}

  public void editorInputChanged(final IEditorReference reference) {}
}
