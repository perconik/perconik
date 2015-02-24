package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.core.listeners.TextListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static java.util.Objects.requireNonNull;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferenceEditor;

final class TextHook extends InternalHook<ITextViewer, TextListener> implements EditorListener {
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
