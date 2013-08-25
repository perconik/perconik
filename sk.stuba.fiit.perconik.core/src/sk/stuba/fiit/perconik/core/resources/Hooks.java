package sk.stuba.fiit.perconik.core.resources;

import java.util.Arrays;
import javax.annotation.Nullable;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import sk.stuba.fiit.perconik.eclipse.ui.Workbenches;

final class Hooks
{
	private Hooks()
	{
		throw new AssertionError();
	}
	
	static final <T> void addNonNull(final Hook<T, ?> hook, @Nullable final T object)
	{
		if (object != null)
		{
			hook.add(object);
		}
	}
	
	static final <T> void removeNonNull(final Hook<T, ?> hook, @Nullable final T object)
	{
		if (object != null)
		{
			hook.remove(object);
		}
	}
	
	static final void addWindowsSynchronouslyTo(final InternalHook<IWorkbenchWindow, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				hook.addAll(Arrays.asList(Workbenches.waitForWorkbench().getWorkbenchWindows()));
			}
		};
		
		Display.getDefault().syncExec(initializer);
	}
	
	static final void addPagesSynchronouslyTo(final InternalHook<IWorkbenchPage, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				hook.addAll(Arrays.asList(Workbenches.waitForActiveWindow().getPages()));
			}
		};
		
		Display.getDefault().syncExec(initializer);
	}

	static final void addPartsSynchronouslyTo(final InternalHook<IWorkbenchPart, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				for (IViewReference reference: Workbenches.waitForActivePage().getViewReferences())
				{
					addNonNull(hook, reference.getPart(false));
				}
			}
		};
		
		Display.getDefault().syncExec(initializer);
	}

	static final void addEditorsSynchronouslyTo(final InternalHook<IEditorPart, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				for (IEditorReference reference: Workbenches.waitForActivePage().getEditorReferences())
				{
					addNonNull(hook, reference.getEditor(false));
				}
			}
		};
		
		Display.getDefault().syncExec(initializer);
	}
	
	static final void addSourceViewersSynchronouslyTo(final InternalHook<ISourceViewer, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				for (IEditorReference reference: Workbenches.waitForActivePage().getEditorReferences())
				{
					addNonNull(hook, Editors.getSourceViewer(reference.getEditor(false)));
				}
			}
		};
		
		Display.getDefault().syncExec(initializer);
	}
	
	static final void addDocumentsSynchronouslyTo(final InternalHook<IDocument, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				for (IEditorReference reference: Workbenches.waitForActivePage().getEditorReferences())
				{
					addNonNull(hook, Editors.getDocument(reference.getEditor(false)));
				}
			}
		};
		
		Display.getDefault().syncExec(initializer);
	}
}
