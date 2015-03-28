package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.source.ContentAssistantFacade;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension3;
import org.eclipse.jface.text.source.ISourceViewerExtension4;
import org.eclipse.ui.IWorkbenchPartReference;

import sk.stuba.fiit.perconik.core.listeners.CompletionListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.eclipse.ui.Parts;

import static sk.stuba.fiit.perconik.core.resources.Ui.dereferencePart;

final class CompletionHook extends InternalHook<ISourceViewer, CompletionListener> implements PartListener {
  CompletionHook(final CompletionListener listener) {
    super(new SourceViewerHandler(listener));
  }

  static final class Support extends AbstractHookSupport<CompletionHook, ISourceViewer, CompletionListener> {
    public Hook<ISourceViewer, CompletionListener> create(final CompletionListener listener) {
      return new CompletionHook(listener);
    }
  }

  private static final class SourceViewerHandler extends InternalHandler<ISourceViewer, CompletionListener> {
    SourceViewerHandler(final CompletionListener listener) {
      super(ISourceViewer.class, listener);
    }

    private static IQuickAssistAssistant assistant(final ISourceViewer viewer) {
      if (viewer instanceof ISourceViewerExtension3) {
        return ((ISourceViewerExtension3) viewer).getQuickAssistAssistant();
      }

      return null;
    }

    private static ContentAssistantFacade facade(final ISourceViewer viewer) {
      if (viewer instanceof ISourceViewerExtension4) {
        return ((ISourceViewerExtension4) viewer).getContentAssistantFacade();
      }

      return null;
    }

    public void register(final ISourceViewer viewer) {
      IQuickAssistAssistant assistant = assistant(viewer);
      ContentAssistantFacade facade = facade(viewer);

      if (assistant != null) {
        assistant.addCompletionListener(this.listener);
      }

      if (facade != null) {
        facade.addCompletionListener(this.listener);
      }
    }

    public void unregister(final ISourceViewer viewer) {
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
  void preRegisterInternal() {
    Hooks.addSourceViewersAsynchronouslyTo(this);
  }

  private static ISourceViewer filter(final ISourceViewer viewer) {
    if (viewer instanceof ISourceViewerExtension3) {
      return viewer;
    }

    if (viewer instanceof ISourceViewerExtension4) {
      return viewer;
    }

    return null;
  }

  public void partOpened(final IWorkbenchPartReference reference) {
    Hooks.addNonNull(this, filter(Parts.getSourceViewer(dereferencePart(reference))));
  }

  public final void partClosed(final IWorkbenchPartReference reference) {
    Hooks.removeNonNull(this, filter(Parts.getSourceViewer(dereferencePart(reference))));
  }

  public void partActivated(final IWorkbenchPartReference reference) {}

  public void partDeactivated(final IWorkbenchPartReference reference) {}

  public void partVisible(final IWorkbenchPartReference reference) {}

  public void partHidden(final IWorkbenchPartReference reference) {}

  public void partBroughtToTop(final IWorkbenchPartReference reference) {}

  public void partInputChanged(final IWorkbenchPartReference reference) {}
}
