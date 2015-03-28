package sk.stuba.fiit.perconik.core.resources;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.listeners.DocumentListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;

import static com.google.common.collect.Maps.newHashMap;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferencePart;

final class DocumentHook extends InternalHook<IDocument, DocumentListener> implements PartListener {
  private final Map<ITextViewer, TextInputChangeFix> fixes;

  DocumentHook(final DocumentListener listener) {
    super(new DocumentHandler(listener));

    this.fixes = newHashMap();
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

  private final class TextInputChangeFix implements ITextInputListener {
    TextInputChangeFix() {}

    public void inputDocumentAboutToBeChanged(final IDocument before, final IDocument after) {}

    public void inputDocumentChanged(final IDocument before, final IDocument after) {
      Hooks.removeNonNull(DocumentHook.this, before);
      Hooks.addNonNull(DocumentHook.this, after);
    }
  }

  @Override
  void preRegisterInternal() {
    Hooks.addDocumentsAsynchronouslyTo(this);
  }

  @Override
  void postUnregisterInternal() {
    synchronized (this.fixes) {
      for (Entry<ITextViewer, TextInputChangeFix> entry: this.fixes.entrySet()) {
        entry.getKey().removeTextInputListener(entry.getValue());
      }
    }
  }

  public void partOpened(final IWorkbenchPartReference reference) {
    IWorkbenchPart part = dereferencePart(reference);
    ITextViewer viewer = Parts.getTextViewer(part);
    IDocument document = Parts.getDocument(viewer);

    if (viewer != null) {
      synchronized (this.fixes) {
        if (!this.fixes.containsKey(viewer)) {
          TextInputChangeFix fix = new TextInputChangeFix();

          this.fixes.put(viewer, fix);

          viewer.addTextInputListener(fix);
        }
      }

      Hooks.addNonNull(DocumentHook.this, document);
    }
  }

  public void partClosed(final IWorkbenchPartReference reference) {
    IWorkbenchPart part = dereferencePart(reference);
    ITextViewer viewer = Parts.getTextViewer(part);
    IDocument document = Parts.getDocument(viewer);

    if (viewer != null) {
      synchronized (this.fixes) {
        TextInputChangeFix fix = this.fixes.remove(viewer);

        if (fix != null) {
          viewer.removeTextInputListener(fix);
        }
      }
    }

    Hooks.removeNonNull(DocumentHook.this, document);
  }

  public void partActivated(final IWorkbenchPartReference reference) {}

  public void partDeactivated(final IWorkbenchPartReference reference) {}

  public void partVisible(final IWorkbenchPartReference reference) {}

  public void partHidden(final IWorkbenchPartReference reference) {}

  public void partBroughtToTop(final IWorkbenchPartReference reference) {}

  public void partInputChanged(final IWorkbenchPartReference reference) {}
}
