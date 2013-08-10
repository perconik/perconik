package sk.stuba.fiit.perconik.core.resources;

import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension3;
import org.eclipse.jface.text.source.ISourceViewerExtension4;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import sk.stuba.fiit.perconik.core.listeners.CompletionListener;
import sk.stuba.fiit.perconik.core.listeners.PartListener;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

final class CompletionHook extends InternalHook<IEditorPart, CompletionListener> implements PartListener
{
	CompletionHook(final CompletionListener listener)
	{
		super(new InternalWindowHandler(listener));
	}
	
	static final class Support extends AbstractHookSupport<CompletionHook, IEditorPart, CompletionListener>
	{
		public final Hook<IEditorPart, CompletionListener> create(final CompletionListener listener)
		{
			return new CompletionHook(listener);
		}
	}

	private static final class InternalWindowHandler extends InternalHandler<IEditorPart, CompletionListener>
	{
		InternalWindowHandler(final CompletionListener listener)
		{
			super(listener);
		}

		public final void register(final IEditorPart editor)
		{
			// TODO refactor, viewer can be null, pool should be of viewers not editors
			
			ISourceViewer viewer = Editors.getSourceViewer(editor);
			
			if (viewer instanceof ISourceViewerExtension3)
			{
				((ISourceViewerExtension3) viewer).getQuickAssistAssistant().addCompletionListener(this.listener);
			}
			
			if (viewer instanceof ISourceViewerExtension4)
			{
				((ISourceViewerExtension4) viewer).getContentAssistantFacade().addCompletionListener(this.listener);
			}
			
			// TODO ?
			//throw new UnsupportedOperationException();
		}

		public final void unregister(final IEditorPart editor)
		{
			ISourceViewer viewer = Editors.getSourceViewer(editor);
			
			if (viewer instanceof ISourceViewerExtension3)
			{
				((ISourceViewerExtension3) viewer).getQuickAssistAssistant().removeCompletionListener(this.listener);
			}
			
			if (viewer instanceof ISourceViewerExtension4)
			{
				((ISourceViewerExtension4) viewer).getContentAssistantFacade().removeCompletionListener(this.listener);
			}
		}
	}
	
	private static final IEditorPart fetch(final IWorkbenchPartReference reference)
	{
		IWorkbenchPart part = reference.getPart(false);
		
		return part instanceof IEditorPart ? (IEditorPart) part : null; 
	}

	@Override
	final void preRegisterInternal()
	{
		Hooks.addEditorsAsynchronouslyTo(this);
	}

	public final void partOpened(final IWorkbenchPartReference reference)
	{
		IEditorPart editor = fetch(reference);
		
		if (editor != null)
		{
			this.add(editor);
		}
	}

	public final void partClosed(final IWorkbenchPartReference reference)
	{
		IEditorPart editor = fetch(reference);
		
		if (editor != null)
		{
			this.remove(editor);
		}
	}

	public final void partActivated(final IWorkbenchPartReference reference)
	{
	}

	public final void partDeactivated(final IWorkbenchPartReference reference)
	{
	}

	public final void partVisible(final IWorkbenchPartReference reference)
	{
	}

	public final void partHidden(final IWorkbenchPartReference reference)
	{
	}

	public final void partBroughtToTop(final IWorkbenchPartReference reference)
	{
	}

	public final void partInputChanged(final IWorkbenchPartReference reference)
	{
	}
}