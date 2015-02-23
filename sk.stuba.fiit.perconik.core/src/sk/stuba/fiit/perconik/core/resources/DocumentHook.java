package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferenceEditor;

final class DocumentHook extends InternalHook<IDocument, DocumentListener> implements EditorListener {
  DocumentHook(final DocumentListener listener) {
    super(new DocumentHandler(listener));
  }

  static final class Support extends AbstractHookSupport<DocumentHook, IDocument, DocumentListener> {
    public Hook<IDocument, DocumentListener> create(final DocumentListener listener) {
      return new DocumentHook(listener);
    }
  }

  private static final class DocumentHandler extends InternalHandler<IDocument, DocumentListener> {
    DocumentHandler(final DocumentListener listener) {
      super(IDocument.class, listener);
    }

    public void register(final IDocument document) {
      document.addDocumentListener(this.listener);
    }

    public void unregister(final IDocument document) {
      document.removeDocumentListener(this.listener);
    }
  }

  @Override
  void preRegisterInternal() {
    Hooks.addDocumentsAsynchronouslyTo(this);
  }

  public void editorOpened(final IEditorReference reference) {
    Hooks.addNonNull(this, Editors.getDocument(dereferenceEditor(reference)));
  }

  public void editorClosed(final IEditorReference reference) {
    Hooks.removeNonNull(this, Editors.getDocument(dereferenceEditor(reference)));
  }

  public void editorActivated(final IEditorReference reference) {}

  public void editorDeactivated(final IEditorReference reference) {}

  public void editorVisible(final IEditorReference reference) {}

  public void editorHidden(final IEditorReference reference) {}

  public void editorBroughtToTop(final IEditorReference reference) {}

  public void editorInputChanged(final IEditorReference reference) {}
}
