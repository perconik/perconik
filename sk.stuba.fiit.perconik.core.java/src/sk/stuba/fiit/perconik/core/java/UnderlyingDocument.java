package sk.stuba.fiit.perconik.core.java;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.annotation.Nullable;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.ui.IEditorPart;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

public abstract class UnderlyingDocument<T>
{
	final T resource;
	
	UnderlyingDocument(final T resource)
	{
		this.resource = checkNotNull(resource);
	}
	
	public static final UnderlyingDocument<?> from(@Nullable final IEditorPart editor)
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
	
	public static final UnderlyingDocument<IClassFile> of(final IClassFile file)
	{
		return new ClassFile(file);
	}

	public static final UnderlyingDocument<IFile> of(final IFile file)
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
		public final String getPath()
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
		public final String getPath()
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
	
	public abstract String getPath();
}
