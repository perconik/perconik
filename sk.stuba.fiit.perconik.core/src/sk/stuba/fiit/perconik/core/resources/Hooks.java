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

	static final <T> void addAll(final Hook<T, ?> hook, final Iterable<T> objects)
	{
		for (T object: objects)
		{
			hook.add(object);
		}
	}

	static final <T> void addNonNull(final Hook<T, ?> hook, @Nullable final T object)
	{
		if (object != null)
		{
			hook.add(object);
		}
	}

	static final <T> void removeAll(final Hook<T, ?> hook, final Iterable<T> objects)
	{
		for (T object: objects)
		{
			hook.remove(object);
		}
	}

	static final <T> void removeNonNull(final Hook<T, ?> hook, @Nullable final T object)
	{
		if (object != null)
		{
			hook.remove(object);
		}
	}

	static final void addWindowsAsynchronouslyTo(final Hook<IWorkbenchWindow, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				addAll(hook, Arrays.asList(Workbenches.waitForWorkbench().getWorkbenchWindows()));
			}
		};

		Display.getDefault().asyncExec(initializer);
	}

	static final void addPagesAsynchronouslyTo(final Hook<IWorkbenchPage, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				addAll(hook, Arrays.asList(Workbenches.waitForActiveWindow().getPages()));
			}
		};

		Display.getDefault().asyncExec(initializer);
	}

	static final void addPartsAsynchronouslyTo(final Hook<IWorkbenchPart, ?> hook)
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

		Display.getDefault().asyncExec(initializer);
	}

	static final void addEditorsAsynchronouslyTo(final Hook<IEditorPart, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				for (IEditorReference reference: Workbenches.waitForActivePage().getEditorReferences())
				{
					addNonNull(hook, dereferenceEditor(reference));
				}
			}
		};

		Display.getDefault().asyncExec(initializer);
	}

	static final void addSourceViewersAsynchronouslyTo(final Hook<ISourceViewer, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				for (IEditorReference reference: Workbenches.waitForActivePage().getEditorReferences())
				{
					addNonNull(hook, Editors.getSourceViewer(dereferenceEditor(reference)));
				}
			}
		};

		Display.getDefault().asyncExec(initializer);
	}

	static final void addDocumentsAsynchronouslyTo(final Hook<IDocument, ?> hook)
	{
		final Runnable initializer = new Runnable()
		{
			@Override
			public final void run()
			{
				for (IEditorReference reference: Workbenches.waitForActivePage().getEditorReferences())
				{
					addNonNull(hook, Editors.getDocument(dereferenceEditor(reference)));
				}
			}
		};

		Display.getDefault().asyncExec(initializer);
	}

	static final IEditorPart dereferenceEditor(final IEditorReference reference)
	{
		return reference.getEditor(true);
	}
}
