package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.TextInputListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferenceEditor;

final class TextInputHook extends InternalHook<ITextViewer, TextInputListener> implements EditorListener {
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

      this.viewer = requireNonNull(viewer);
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

  public void editorOpened(final IEditorReference reference) {
    Hooks.addNonNull(this, Editors.getTextViewer(dereferenceEditor(reference)));
  }

  public final void editorClosed(final IEditorReference reference) {
    Hooks.removeNonNull(this, Editors.getTextViewer(dereferenceEditor(reference)));
  }

  public void editorActivated(final IEditorReference reference) {}

  public void editorDeactivated(final IEditorReference reference) {}

  public void editorVisible(final IEditorReference reference) {}

  public void editorHidden(final IEditorReference reference) {}

  public void editorBroughtToTop(final IEditorReference reference) {}

  public void editorInputChanged(final IEditorReference reference) {}
}
