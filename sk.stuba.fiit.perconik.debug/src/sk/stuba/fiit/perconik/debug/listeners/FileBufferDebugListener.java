package sk.stuba.fiit.perconik.debug.listeners;

import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.runtime.IPath;
import sk.stuba.fiit.perconik.core.listeners.FileBufferListener;
import sk.stuba.fiit.perconik.debug.Debug;
import sk.stuba.fiit.perconik.debug.DebugConsole;

public final class FileBufferDebugListener extends AbstractDebugListener implements FileBufferListener
{
	public FileBufferDebugListener()
	{
	}
	
	public FileBufferDebugListener(final DebugConsole console)
	{
		super(console);
	}
	
	public final void bufferCreated(final IFileBuffer buffer)
	{
		this.printHeader("File buffer created");
		this.printFileBuffer(buffer);
	}

	public final void bufferDisposed(final IFileBuffer buffer)
	{
		this.printHeader("File buffer disposed");
		this.printFileBuffer(buffer);
	}

	public final void bufferContentAboutToBeReplaced(final IFileBuffer buffer)
	{
		this.printHeader("File buffer content about to be replaced");
		this.printFileBuffer(buffer);
	}

	public final void bufferContentReplaced(final IFileBuffer buffer)
	{
		this.printHeader("File buffer content replaced");
		this.printFileBuffer(buffer);
	}

	public final void stateChanging(final IFileBuffer buffer)
	{
		this.printHeader("State changing");
		this.printFileBuffer(buffer);
	}

	public final void stateChangeFailed(final IFileBuffer buffer)
	{
		this.printHeader("State change failed");
		this.printFileBuffer(buffer);
	}

	public final void stateValidationChanged(final IFileBuffer buffer, final boolean stateValidated)
	{
		this.printHeader("State validation changed");
		this.printFileBuffer(buffer);
	}

	public final void dirtyStateChanged(final IFileBuffer buffer, final boolean dirty)
	{
		this.printHeader("Dirty state changed");
		this.printFileBuffer(buffer);
	}

	public final void underlyingFileMoved(final IFileBuffer buffer, final IPath path)
	{
		this.printHeader("Underlying file moved");
		this.printFileBuffer(buffer);
	}

	public final void underlyingFileDeleted(final IFileBuffer buffer)
	{
		this.printHeader("Underlying file deleted");
		this.printFileBuffer(buffer);
	}
	
	private final void printFileBuffer(final IFileBuffer buffer)
	{
		this.put(Debug.dumpFileBuffer(buffer));
	}
}
