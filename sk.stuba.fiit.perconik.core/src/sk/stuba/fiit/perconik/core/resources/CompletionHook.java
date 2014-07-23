package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.source.ContentAssistantFacade;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension3;
import org.eclipse.jface.text.source.ISourceViewerExtension4;
import org.eclipse.ui.IEditorReference;

import sk.stuba.fiit.perconik.core.listeners.CompletionListener;
import sk.stuba.fiit.perconik.core.listeners.EditorListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class CompletionHook extends InternalHook<ISourceViewer, CompletionListener> implements EditorListener {
  CompletionHook(final CompletionListener listener) {
    super(new WindowHandler(listener));
  }

  static final class Support extends AbstractHookSupport<CompletionHook, ISourceViewer, CompletionListener> {
    public final Hook<ISourceViewer, CompletionListener> create(final CompletionListener listener) {
      return new CompletionHook(listener);
    }
  }

  private static final class WindowHandler extends InternalHandler<ISourceViewer, CompletionListener> {
    WindowHandler(final CompletionListener listener) {
      super(ISourceViewer.class, listener);
    }

    private final static IQuickAssistAssistant assistant(final ISourceViewer viewer) {
      if (viewer instanceof ISourceViewerExtension3) {
        return ((ISourceViewerExtension3) viewer).getQuickAssistAssistant();
      }

      return null;
    }

    private final static ContentAssistantFacade facade(final ISourceViewer viewer) {
      if (viewer instanceof ISourceViewerExtension4) {
        return ((ISourceViewerExtension4) viewer).getContentAssistantFacade();
      }

      return null;
    }

    public final void register(final ISourceViewer viewer) {
      IQuickAssistAssistant assistant = assistant(viewer);
      ContentAssistantFacade facade = facade(viewer);

      if (assistant != null) {
        assistant.addCompletionListener(this.listener);
      }

      if (facade != null) {
        facade.addCompletionListener(this.listener);
      }
    }

    public final void unregister(final ISourceViewer viewer) {
      IQuickAssistAssistant assistant = assistant(viewer);
      ContentAssistantFacade facade = facade(viewer);

      if (assistant != null) {
        assistant.removeCompletionListener(this.listener);
      }

      if (facade != null) {
        facade.removeCompletionListener(this.listener);
      }
    }
  }

  @Override
  final void preRegisterInternal() {
    Hooks.addSourceViewersAsynchronouslyTo(this);
  }

  private static final ISourceViewer filter(final ISourceViewer viewer) {
    if (viewer instanceof ISourceViewerExtension3) {
      return viewer;
    }

    if (viewer instanceof ISourceViewerExtension4) {
      return viewer;
    }

    return null;
  }

  public final void editorOpened(final IEditorReference reference) {
    Hooks.addNonNull(this, filter(Editors.getSourceViewer(Hooks.dereferenceEditor(reference))));
  }

  public final void editorClosed(final IEditorReference reference) {
    Hooks.removeNonNull(this, filter(Editors.getSourceViewer(Hooks.dereferenceEditor(reference))));
  }

  public final void editorActivated(final IEditorReference reference) {}

  public final void editorDeactivated(final IEditorReference reference) {}

  public final void editorVisible(final IEditorReference reference) {}

  public final void editorHidden(final IEditorReference reference) {}

  public final void editorBroughtToTop(final IEditorReference reference) {}

  public final void editorInputChanged(final IEditorReference reference) {}
}
