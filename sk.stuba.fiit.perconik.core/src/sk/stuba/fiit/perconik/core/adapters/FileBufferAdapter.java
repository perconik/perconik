package sk.stuba.fiit.perconik.core.adapters;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;

import sk.stuba.fiit.perconik.core.Adapter;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;

/**
 * An abstract adapter class for a {@code FileBufferListener}.
 * The methods in this class are empty. This class exists
 * as convenience for creating listener objects.
 * 
 * <p>Extend this class to create a {@code FileBufferListener}
 * and override the methods for the events of interest.
 * 
 * @see Adapter
 * @see FileBufferListener
 * 
 * @author Pavol Zbell
 * @since 1.0
 */
public class FileBufferAdapter extends Adapter implements FileBufferListener
{
	/**
	 * Constructor for use by subclasses.
	 */
	protected FileBufferAdapter()
	{
	}

	public void bufferCreated(IFileBuffer buffer)
	{
	}

	public void bufferDisposed(IFileBuffer buffer)
	{
	}

	public void bufferContentAboutToBeReplaced(IFileBuffer buffer)
	{
	}

	public void bufferContentReplaced(IFileBuffer buffer)
	{
	}

	public void stateChanging(IFileBuffer buffer)
	{
	}

	public void stateChangeFailed(IFileBuffer buffer)
	{
	}

	public void stateValidationChanged(IFileBuffer buffer, boolean stateValidated)
	{
	}

	public void dirtyStateChanged(IFileBuffer buffer, boolean dirty)
	{
	}

	public void underlyingFileMoved(IFileBuffer buffer, IPath path)
	{
	}

	public void underlyingFileDeleted(IFileBuffer buffer)
	{
	}
}
