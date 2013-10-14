package com.gratex.perconik.activity.listeners;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.ui.IEditorPart;
import sk.stuba.fiit.perconik.core.java.ClassFiles;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;
import com.gratex.perconik.activity.DataTransferObjects;
import com.gratex.perconik.services.activity.IdeCodeOperationDto;
import com.gratex.perconik.services.activity.IdeDocumentOperationDto;
import com.gratex.perconik.services.activity.IdeSlnPrjEventDto;

abstract class UnderlyingDocument<T>
{
	final T resource;
	
	UnderlyingDocument(final T resource)
	{
		assert resource != null;
		
		this.resource = resource;
	}
	
	static final UnderlyingDocument<?> of(final IEditorPart editor)
	{
		IFile file = Editors.getFile(editor);
		
		if (file != null)
		{
			return valueOf(file);
		}
		
		IClassFile classFile = (IClassFile) editor.getEditorInput().getAdapter(IClassFile.class);
		
		if (classFile != null)
		{
			return valueOf(classFile);
		}
		
		return null;
	}
	
	static final UnderlyingDocument<IClassFile> valueOf(final IClassFile file)
	{
		return new ClassFile(file);
	}

	static final UnderlyingDocument<IFile> valueOf(final IFile file)
	{
		return new File(file);
	}

	private static final class ClassFile extends UnderlyingDocument<IClassFile>
	{
		ClassFile(final IClassFile resource)
		{
			super(resource);
		}

		@Override
		final void setDocumentData(final IdeCodeOperationDto data)
		{
			data.setDocument(DataTransferObjects.newDocumentData(this.resource));
		}

		@Override
		final void setDocumentData(final IdeDocumentOperationDto data)
		{
			data.setDocument(DataTransferObjects.newDocumentData(this.resource));
		}

		@Override
		final void setProjectData(final IdeSlnPrjEventDto data)
		{
			DataTransferObjects.setProjectData(data, this.resource);
		}

		@Override
		final String getPath()
		{
			return ClassFiles.path(this.resource);
		}
	}

	private static final class File extends UnderlyingDocument<IFile>
	{
		File(final IFile resource)
		{
			super(resource);
		}

		@Override
		final void setDocumentData(final IdeCodeOperationDto data)
		{
			data.setDocument(DataTransferObjects.newDocumentData(this.resource));
		}

		@Override
		final void setDocumentData(final IdeDocumentOperationDto data)
		{
			data.setDocument(DataTransferObjects.newDocumentData(this.resource));
		}

		@Override
		final void setProjectData(final IdeSlnPrjEventDto data)
		{
			DataTransferObjects.setProjectData(data, this.resource);
		}

		@Override
		final String getPath()
		{
			return this.resource.getFullPath().toString();
		}
	}

	
	@Override
	public final boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}

		if (!(o instanceof UnderlyingDocument))
		{
			return false;
		}
		
		return this.getPath().equals(((UnderlyingDocument<?>) o).getPath());
	}

	@Override
	public final int hashCode()
	{
		return this.getPath().hashCode();
	}

	abstract void setDocumentData(IdeCodeOperationDto data);
	
	abstract void setDocumentData(IdeDocumentOperationDto data);
	
	abstract void setProjectData(IdeSlnPrjEventDto data);
	
	abstract String getPath();
}
