package sk.stuba.fiit.perconik.eclipse.jface.text;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.IDocument;

import sk.stuba.fiit.perconik.eclipse.core.runtime.CoreExceptions;

/**
 * Static utility methods pertaining to Eclipse documents.
 *
 * @author Pavol Zbell
 * @since 1.0
 */
public final class Documents
{
	private Documents()
	{
		throw new AssertionError();
	}

	public static final IDocument fromFile(IFile file)
	{
		return fromPath(file.getFullPath(), LocationKind.IFILE);
	}
	
	public static final IDocument fromPath(IPath path, LocationKind kind)
	{
		ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
		
		try
		{
			manager.connect(path, kind, null);
		}
		catch (CoreException e)
		{
			CoreExceptions.propagate(e);
		}
		
		ITextFileBuffer buffer = manager.getTextFileBuffer(path, kind);
		
		return buffer.getDocument();
	}
}
