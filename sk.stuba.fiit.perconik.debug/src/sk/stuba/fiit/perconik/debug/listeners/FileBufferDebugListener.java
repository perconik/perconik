package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.core.utilities.PluginConsole;

public final class FileBufferDebugListener extends AbstractDebugListener implements FileBufferListener
{
	public FileBufferDebugListener()
	{
	}
	
	public FileBufferDebugListener(final PluginConsole console)
	{
		super(console);
	}
	
	public final void bufferCreated(final IFileBuffer buffer)
	{
	}

	public final void bufferDisposed(final IFileBuffer buffer)
	{
	}

	public final void bufferContentAboutToBeReplaced(final IFileBuffer buffer)
	{
	}

	public final void bufferContentReplaced(final IFileBuffer buffer)
	{
	}

	public final void stateChanging(final IFileBuffer buffer)
	{
	}

	public final void stateChangeFailed(final IFileBuffer buffer)
	{
	}

	public final void stateValidationChanged(final IFileBuffer buffer, final boolean isStateValidated)
	{
	}

	public final void dirtyStateChanged(final IFileBuffer buffer, final boolean isDirty)
	{
	}

	public final void underlyingFileMoved(final IFileBuffer buffer, final IPath path)
	{
	}

	public final void underlyingFileDeleted(final IFileBuffer buffer)
	{
	}
}
