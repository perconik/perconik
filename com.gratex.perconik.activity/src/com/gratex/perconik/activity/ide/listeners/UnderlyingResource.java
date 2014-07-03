package com.gratex.perconik.activity.ide.listeners;

import javax.annotation.Nullable;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IEditorPart;
import sk.stuba.fiit.perconik.core.java.ClassFiles;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import com.gratex.perconik.activity.ide.IdeData;
import com.gratex.perconik.services.uaca.ide.BaseIdeEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeCodeEventRequest;
import com.gratex.perconik.services.uaca.ide.IdeDocumentEventRequest;

abstract class UnderlyingResource<F>
{
	final F file;
	
	UnderlyingResource(final F file)
	{
		assert file != null;
		
		this.file = file;
	}
	
	static final UnderlyingResource<?> from(@Nullable final IEditorPart editor)
	{
		if (editor == null)
		{
			return null;
		}
		
		IFile file = Editors.getFile(editor);
		
		if (file != null)
		{
			return of(file);
		}
		
		IClassFile classFile = (IClassFile) editor.getEditorInput().getAdapter(IClassFile.class);
		
		if (classFile != null)
		{
			return of(classFile);
		}
		
		return null;
	}
	
	static final UnderlyingResource<?> resolve(Object element)
	{
		if (element instanceof IFile)
		{
			return of((IFile) element);
		}
		
		if (element instanceof IClassFile)
		{
			return of((IClassFile) element);
		}
		
		return null;
	}

	static final UnderlyingResource<IClassFile> of(final IClassFile file)
	{
		return new ClassFile(file);
	}

	static final UnderlyingResource<IFile> of(final IFile file)
	{
		return new DataFile(file);
	}
	
	private static final class ClassFile extends UnderlyingResource<IClassFile>
	{
		ClassFile(final IClassFile resource)
		{
			super(resource);
		}

		@Override
		final void setDocumentData(final IdeCodeEventRequest data)
		{
			data.setDocument(IdeData.newDocumentData(this.file));
		}

		@Override
		final void setDocumentData(final IdeDocumentEventRequest data)
		{
			data.setDocument(IdeData.newDocumentData(this.file));
		}

		@Override
		final void setProjectData(final BaseIdeEventRequest data)
		{
			IdeData.setProjectData(data, this.file);
		}

		@Override
		final IFile getFile()
		{
			try
			{
				// TODO rm
//				System.out.println(this.file);
//				System.out.println(this.file.getPath());
//				System.out.println(this.getPath());
//				System.out.println(this.getPathAsString());
//				System.out.println(this.file.getUnderlyingResource());
				
				
				return (IFile) this.file.getUnderlyingResource();
			}
			catch (ClassCastException | JavaModelException e)
			{
				return null;
			}
		}

		@Override
		final IPath getPath()
		{
			return new Path(this.getPathAsString());
		}

		@Override
		final String getPathAsString()
		{
			return ClassFiles.path(this.file);
		}
	}

	private static final class DataFile extends UnderlyingResource<IFile>
	{
		DataFile(final IFile resource)
		{
			super(resource);
		}

		@Override
		final void setDocumentData(final IdeCodeEventRequest data)
		{
			data.setDocument(IdeData.newDocumentData(this.file));
		}

		@Override
		final void setDocumentData(final IdeDocumentEventRequest data)
		{
			data.setDocument(IdeData.newDocumentData(this.file));
		}

		@Override
		final void setProjectData(final BaseIdeEventRequest data)
		{
			IdeData.setProjectData(data, this.file);
		}

		@Override
		final IFile getFile()
		{
			return this.file;
		}

		@Override
		final IPath getPath()
		{
			return this.file.getFullPath();
		}
		
		@Override
		final String getPathAsString()
		{
			return this.getPath().toString();
		}
	}
	
	@Override
	public final boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof UnderlyingResource))
		{
			return false;
		}
		
		return this.getPathAsString().equals(((UnderlyingResource<?>) o).getPathAsString());
	}

	@Override
	public final int hashCode()
	{
		return this.getPathAsString().hashCode();
	}

	abstract void setDocumentData(IdeCodeEventRequest data);

	abstract void setDocumentData(IdeDocumentEventRequest data);
	
	abstract void setProjectData(BaseIdeEventRequest data);

	abstract IFile getFile();
	
	abstract IPath getPath();

	// TODO make private / rm
	// TODO cache in: private final path variable in abstract root
	abstract String getPathAsString();
}
