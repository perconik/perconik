package sk.stuba.fiit.perconik.core.java;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.annotation.Nullable;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.ui.IEditorPart;
import sk.stuba.fiit.perconik.eclipse.ui.Editors;

public abstract class UnderlyingResource<F>
{
	final F file;
	
	UnderlyingResource(final F file)
	{
		this.file = checkNotNull(file);
	}
	
	public static final UnderlyingResource<?> from(@Nullable final IEditorPart editor)
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
	
	public static final UnderlyingResource<IClassFile> of(final IClassFile file)
	{
		return new ClassFile(file);
	}

	public static final UnderlyingResource<IFile> of(final IFile file)
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
		public final String getPath()
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
		public final String getPath()
		{
			return this.file.getFullPath().toString();
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
		
		return this.getPath().equals(((UnderlyingResource<?>) o).getPath());
	}

	@Override
	public final int hashCode()
	{
		return this.getPath().hashCode();
	}
	
	public abstract String getPath();
}
